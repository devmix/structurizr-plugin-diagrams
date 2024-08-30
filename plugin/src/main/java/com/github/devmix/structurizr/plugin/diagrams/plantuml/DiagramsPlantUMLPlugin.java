package com.github.devmix.structurizr.plugin.diagrams.plantuml;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.stream.Collectors;

import com.structurizr.documentation.Documentable;
import com.structurizr.documentation.DocumentationContent;
import com.structurizr.documentation.Format;
import com.structurizr.dsl.StructurizrDslPlugin;
import com.structurizr.dsl.StructurizrDslPluginContext;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagramsPlantUMLPlugin implements StructurizrDslPlugin {

    private static final Logger LOG = LoggerFactory.getLogger(DiagramsPlantUMLPlugin.class);

    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String PLANTUML_FORMAT = "svg";
    private static final String IMAGE_FORMAT = "svg+xml";
    public static final String CACHE_DIR = ".cache/plantuml";

    private Path rootDir;
    private Path imagesCacheDir;
    private boolean imageAsUrl;
    private boolean renderPumlUsingServer;
    private String renderPumlServerUrl;

    @Override
    public void run(final StructurizrDslPluginContext context) {
        try {
            rootDir = Paths.get(context.getDslFile().getAbsolutePath()).getParent();
            imageAsUrl = Boolean.parseBoolean(context.getParameter("plantuml.image.asUrl", "false"));
            imagesCacheDir = rootDir.resolve(CACHE_DIR);
            renderPumlUsingServer = Boolean.parseBoolean(context.getParameter("plantuml.render.usingServer", "false"));
            renderPumlServerUrl = context.getParameter("plantuml.url", "http://localhost:8081");

            if (Files.notExists(imagesCacheDir)) {
                LOG.info("Create PlantUML cache directory {}", imagesCacheDir);
                Files.createDirectories(imagesCacheDir);
            }

            final var workspace = context.getWorkspace();

            final var documentables = workspace.getModel().getElements().stream()
                    .filter(e -> e instanceof Documentable)
                    .map(e -> (Documentable) e)
                    .collect(Collectors.toSet());

            documentables.add(workspace);

            for (final var documentable : documentables) {
                encodePlantUML(documentable);
            }
        } catch (final Exception e) {
            LOG.info("Cannot render PlantUML diagrams", e);
        }
    }

    private void encodePlantUML(final Documentable documentable) throws Exception {
        for (final var section : documentable.getDocumentation().getSections()) {
            section.setContent(encodePlantUML(section));
        }

        for (final var decision : documentable.getDocumentation().getDecisions()) {
            decision.setContent(encodePlantUML(decision));
        }
    }

    private String encodePlantUML(final DocumentationContent documentationContent) throws Exception {
        //TODO SG optimize document parser
        final var content = documentationContent.getContent();
        if (!content.contains("```plantuml")) {
            return content;
        }

        final var format = documentationContent.getFormat();

        final var buf = new StringBuilder();
        final var lines = content.split("\\r?\\n");
        StringBuilder rawPlantUML = null;
        for (var line : lines) {
            line = line.trim();

            if (line.equals("```plantuml")) {
                rawPlantUML = new StringBuilder();
            } else if (rawPlantUML != null && line.equals("```")) {
                final var strPlantUML = rawPlantUML.toString();

                if (imageAsUrl) {
                    replaceByImageUrl(strPlantUML, format, buf);
                } else {
                    replaceByEmbeddedImage(strPlantUML, format, buf);
                }
                buf.append(System.lineSeparator());

                rawPlantUML = null;
            } else if (rawPlantUML != null) {
                rawPlantUML.append(line);
                rawPlantUML.append(System.lineSeparator());
            } else {
                buf.append(line);
                buf.append(System.lineSeparator());
            }
        }

        return buf.toString();
    }

    private void replaceByImageUrl(final String strPlantUML, final Format format, final StringBuilder buf) throws Exception {
        final var encoded = PlantUMLEncoder.encode(strPlantUML);
        if (format == Format.AsciiDoc) {
            buf.append(String.format("image::%s/%s/%s[]", renderPumlServerUrl, PLANTUML_FORMAT, encoded));
//            buf.append("image::").append(rootDir.relativize(filePath)).append("[]");
        } else {
            buf.append(String.format("![](%s/%s/%s)", renderPumlServerUrl, PLANTUML_FORMAT, encoded));
//            buf.append("![](").append(rootDir.relativize(filePath)).append(")");
        }
    }

    private void replaceByEmbeddedImage(final String strPlantUML, final Format format, final StringBuilder buf) throws Exception {
        final byte[] image;
        final var filePath = toCacheFile(strPlantUML);
        if (!Files.exists(filePath)) {
            image = renderDiagram(strPlantUML);
            try (final var os = Files.newOutputStream(filePath)) {
                os.write(image);
            }
        } else {
            LOG.trace("Use cached diagram '{}'", filePath.toAbsolutePath());
            image = Files.readAllBytes(filePath);
        }
        if (format == Format.AsciiDoc) {
            buf.append("image::").append(toEmbedImage(image)).append("[]");
        } else {
            buf.append("<img src=\"").append(toEmbedImage(image)).append("\">");
        }
    }

    private byte[] renderDiagram(final String plantUML) throws Exception {
        if (renderPumlUsingServer) {
            final var encodedPUML = PlantUMLEncoder.encode(plantUML);
            final var renderUrl = new URL(String.format("%s/%s/%s", renderPumlServerUrl, PLANTUML_FORMAT, encodedPUML));

            LOG.info("Render PlantUML using server '{}'", renderUrl);

            try (final var is = renderUrl.openStream()) {
                return is.readAllBytes();
            }
        } else {
            final var reader = new SourceStringReader(!plantUML.startsWith("@startuml")
                    ? "@startuml\n" + plantUML + "\n@enduml" : plantUML);
            final var os = new ByteArrayOutputStream();
            final var option = new FileFormatOption(FileFormat.SVG);
            reader.outputImage(os, option);
            os.close();
            return os.toByteArray();
        }
    }

    private Path toCacheFile(final String plantUML) throws NoSuchAlgorithmException {
        final var hash = MessageDigest.getInstance("MD5");
        hash.update(plantUML.getBytes(StandardCharsets.UTF_8));
        return imagesCacheDir.resolve(encodeToString(hash.digest()) + "." + PLANTUML_FORMAT);
    }

    private String encodeToString(final byte[] bytes) {
        final var sb = new StringBuilder();
        for (final var b : bytes) {
            sb.append(CHARS[(b & 0xF0) >> 4]).append(CHARS[b & 0x0F]);
        }
        return sb.toString();
    }

    private String toEmbedImage(final byte[] image) {
        if (image.length == 0) {
            return null;
        }
        return "data:image/" + IMAGE_FORMAT + ";base64," + Base64.getEncoder().encodeToString(image);
    }
}