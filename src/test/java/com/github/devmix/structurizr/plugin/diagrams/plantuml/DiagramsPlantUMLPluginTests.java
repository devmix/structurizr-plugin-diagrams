package com.github.devmix.structurizr.plugin.diagrams.plantuml;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import com.structurizr.Workspace;
import com.structurizr.documentation.Format;
import com.structurizr.documentation.Section;
import com.structurizr.dsl.StructurizrDslPluginContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiagramsPlantUMLPluginTests {

    @Test
    public void testRenderPlantUmlEmbedded() {
        final var workspace = new Workspace("Name", "Description");

        final var markdown = new Section(Format.Markdown, """
                ## Context

                ```plantuml
                @startuml
                Bob -> Alice : hello
                @enduml
                ```""");
        workspace.getDocumentation().addSection(markdown);

        final var asciidoc = new Section(Format.AsciiDoc, """
                == Context

                ```plantuml
                @startuml
                Bob -> Alice : hello
                @enduml
                ```""");
        workspace.getDocumentation().addSection(asciidoc);

        final var context = new StructurizrDslPluginContext(null, new File("."), workspace, Collections.emptyMap());
        new DiagramsPlantUMLPlugin().run(context);

        assertEquals("""
                ## Context

                <img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiBjb250ZW50U3R5bGVUeXBlPSJ0ZXh0L2NzcyIgZGF0YS1kaWFncmFtLXR5cGU9IlNFUVVFTkNFIiBoZWlnaHQ9IjEyOHB4IiBwcmVzZXJ2ZUFzcGVjdFJhdGlvPSJub25lIiBzdHlsZT0id2lkdGg6MTA3cHg7aGVpZ2h0OjEyOHB4O2JhY2tncm91bmQ6I0ZGRkZGRjsiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDEwNyAxMjgiIHdpZHRoPSIxMDdweCIgem9vbUFuZFBhbj0ibWFnbmlmeSI+PGRlZnMvPjxnPjxnPjx0aXRsZT5Cb2I8L3RpdGxlPjxyZWN0IGZpbGw9IiMwMDAwMDAiIGZpbGwtb3BhY2l0eT0iMC4wMDAwMCIgaGVpZ2h0PSI1MS43MDYiIHdpZHRoPSI4IiB4PSIyMS4wOSIgeT0iMzkuMDY3OSIvPjxsaW5lIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41O3N0cm9rZS1kYXNoYXJyYXk6NS4wLDUuMDsiIHgxPSIyNSIgeDI9IjI1IiB5MT0iMzkuMDY3OSIgeTI9IjkwLjc3MzkiLz48L2c+PGc+PHRpdGxlPkFsaWNlPC90aXRsZT48cmVjdCBmaWxsPSIjMDAwMDAwIiBmaWxsLW9wYWNpdHk9IjAuMDAwMDAiIGhlaWdodD0iNTEuNzA2IiB3aWR0aD0iOCIgeD0iNzUuMDI5IiB5PSIzOS4wNjc5Ii8+PGxpbmUgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7c3Ryb2tlLWRhc2hhcnJheTo1LjAsNS4wOyIgeDE9Ijc4LjYzNiIgeDI9Ijc4LjYzNiIgeTE9IjM5LjA2NzkiIHkyPSI5MC43NzM5Ii8+PC9nPjxnIGNsYXNzPSJwYXJ0aWNpcGFudCBwYXJ0aWNpcGFudC1oZWFkIiBkYXRhLXBhcnRpY2lwYW50PSJCb2IiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDAuMTc5OSIgeD0iNSIgeT0iNSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI2LjE3OTkiIHg9IjEyIiB5PSIyNi45NjU5Ij5Cb2I8L3RleHQ+PC9nPjxnIGNsYXNzPSJwYXJ0aWNpcGFudCBwYXJ0aWNpcGFudC10YWlsIiBkYXRhLXBhcnRpY2lwYW50PSJCb2IiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDAuMTc5OSIgeD0iNSIgeT0iODkuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI2LjE3OTkiIHg9IjEyIiB5PSIxMTEuNzM5OSI+Qm9iPC90ZXh0PjwvZz48ZyBjbGFzcz0icGFydGljaXBhbnQgcGFydGljaXBhbnQtaGVhZCIgZGF0YS1wYXJ0aWNpcGFudD0iQWxpY2UiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDQuNzg1OSIgeD0iNTYuNjM2IiB5PSI1Ii8+PHRleHQgZmlsbD0iIzAwMDAwMCIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMTQiIGxlbmd0aEFkanVzdD0ic3BhY2luZyIgdGV4dExlbmd0aD0iMzAuNzg1OSIgeD0iNjMuNjM2IiB5PSIyNi45NjU5Ij5BbGljZTwvdGV4dD48L2c+PGcgY2xhc3M9InBhcnRpY2lwYW50IHBhcnRpY2lwYW50LXRhaWwiIGRhdGEtcGFydGljaXBhbnQ9IkFsaWNlIj48cmVjdCBmaWxsPSIjRTJFMkYwIiBoZWlnaHQ9IjMzLjA2NzkiIHJ4PSIyLjUiIHJ5PSIyLjUiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41OyIgd2lkdGg9IjQ0Ljc4NTkiIHg9IjU2LjYzNiIgeT0iODkuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjMwLjc4NTkiIHg9IjYzLjYzNiIgeT0iMTExLjczOTkiPkFsaWNlPC90ZXh0PjwvZz48ZyBjbGFzcz0ibWVzc2FnZSIgZGF0YS1wYXJ0aWNpcGFudC0xPSJCb2IiIGRhdGEtcGFydGljaXBhbnQtMj0iQWxpY2UiPjxwb2x5Z29uIGZpbGw9IiMxODE4MTgiIHBvaW50cz0iNjcuMDI5LDY4Ljc3MzksNzcuMDI5LDcyLjc3MzksNjcuMDI5LDc2Ljc3MzksNzEuMDI5LDcyLjc3MzkiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MTsiLz48bGluZSBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjE7IiB4MT0iMjUuMDkiIHgyPSI3My4wMjkiIHkxPSI3Mi43NzM5IiB5Mj0iNzIuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjEzIiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI5LjkzOSIgeD0iMzIuMDkiIHk9IjY2Ljk2NDkiPmhlbGxvPC90ZXh0PjwvZz48IS0tU1JDPVtTeWZGS2oyckt0M0NvS25FTFIxSW80WkRvU2E3MDAwMF0tLT48L2c+PC9zdmc+">
                """, markdown.getContent());

        assertEquals("""
                == Context

                image::data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiBjb250ZW50U3R5bGVUeXBlPSJ0ZXh0L2NzcyIgZGF0YS1kaWFncmFtLXR5cGU9IlNFUVVFTkNFIiBoZWlnaHQ9IjEyOHB4IiBwcmVzZXJ2ZUFzcGVjdFJhdGlvPSJub25lIiBzdHlsZT0id2lkdGg6MTA3cHg7aGVpZ2h0OjEyOHB4O2JhY2tncm91bmQ6I0ZGRkZGRjsiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDEwNyAxMjgiIHdpZHRoPSIxMDdweCIgem9vbUFuZFBhbj0ibWFnbmlmeSI+PGRlZnMvPjxnPjxnPjx0aXRsZT5Cb2I8L3RpdGxlPjxyZWN0IGZpbGw9IiMwMDAwMDAiIGZpbGwtb3BhY2l0eT0iMC4wMDAwMCIgaGVpZ2h0PSI1MS43MDYiIHdpZHRoPSI4IiB4PSIyMS4wOSIgeT0iMzkuMDY3OSIvPjxsaW5lIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41O3N0cm9rZS1kYXNoYXJyYXk6NS4wLDUuMDsiIHgxPSIyNSIgeDI9IjI1IiB5MT0iMzkuMDY3OSIgeTI9IjkwLjc3MzkiLz48L2c+PGc+PHRpdGxlPkFsaWNlPC90aXRsZT48cmVjdCBmaWxsPSIjMDAwMDAwIiBmaWxsLW9wYWNpdHk9IjAuMDAwMDAiIGhlaWdodD0iNTEuNzA2IiB3aWR0aD0iOCIgeD0iNzUuMDI5IiB5PSIzOS4wNjc5Ii8+PGxpbmUgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7c3Ryb2tlLWRhc2hhcnJheTo1LjAsNS4wOyIgeDE9Ijc4LjYzNiIgeDI9Ijc4LjYzNiIgeTE9IjM5LjA2NzkiIHkyPSI5MC43NzM5Ii8+PC9nPjxnIGNsYXNzPSJwYXJ0aWNpcGFudCBwYXJ0aWNpcGFudC1oZWFkIiBkYXRhLXBhcnRpY2lwYW50PSJCb2IiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDAuMTc5OSIgeD0iNSIgeT0iNSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI2LjE3OTkiIHg9IjEyIiB5PSIyNi45NjU5Ij5Cb2I8L3RleHQ+PC9nPjxnIGNsYXNzPSJwYXJ0aWNpcGFudCBwYXJ0aWNpcGFudC10YWlsIiBkYXRhLXBhcnRpY2lwYW50PSJCb2IiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDAuMTc5OSIgeD0iNSIgeT0iODkuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI2LjE3OTkiIHg9IjEyIiB5PSIxMTEuNzM5OSI+Qm9iPC90ZXh0PjwvZz48ZyBjbGFzcz0icGFydGljaXBhbnQgcGFydGljaXBhbnQtaGVhZCIgZGF0YS1wYXJ0aWNpcGFudD0iQWxpY2UiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDQuNzg1OSIgeD0iNTYuNjM2IiB5PSI1Ii8+PHRleHQgZmlsbD0iIzAwMDAwMCIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMTQiIGxlbmd0aEFkanVzdD0ic3BhY2luZyIgdGV4dExlbmd0aD0iMzAuNzg1OSIgeD0iNjMuNjM2IiB5PSIyNi45NjU5Ij5BbGljZTwvdGV4dD48L2c+PGcgY2xhc3M9InBhcnRpY2lwYW50IHBhcnRpY2lwYW50LXRhaWwiIGRhdGEtcGFydGljaXBhbnQ9IkFsaWNlIj48cmVjdCBmaWxsPSIjRTJFMkYwIiBoZWlnaHQ9IjMzLjA2NzkiIHJ4PSIyLjUiIHJ5PSIyLjUiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41OyIgd2lkdGg9IjQ0Ljc4NTkiIHg9IjU2LjYzNiIgeT0iODkuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjMwLjc4NTkiIHg9IjYzLjYzNiIgeT0iMTExLjczOTkiPkFsaWNlPC90ZXh0PjwvZz48ZyBjbGFzcz0ibWVzc2FnZSIgZGF0YS1wYXJ0aWNpcGFudC0xPSJCb2IiIGRhdGEtcGFydGljaXBhbnQtMj0iQWxpY2UiPjxwb2x5Z29uIGZpbGw9IiMxODE4MTgiIHBvaW50cz0iNjcuMDI5LDY4Ljc3MzksNzcuMDI5LDcyLjc3MzksNjcuMDI5LDc2Ljc3MzksNzEuMDI5LDcyLjc3MzkiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MTsiLz48bGluZSBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjE7IiB4MT0iMjUuMDkiIHgyPSI3My4wMjkiIHkxPSI3Mi43NzM5IiB5Mj0iNzIuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjEzIiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI5LjkzOSIgeD0iMzIuMDkiIHk9IjY2Ljk2NDkiPmhlbGxvPC90ZXh0PjwvZz48IS0tU1JDPVtTeWZGS2oyckt0M0NvS25FTFIxSW80WkRvU2E3MDAwMF0tLT48L2c+PC9zdmc+[]
                """, asciidoc.getContent());
    }

    @Test
    public void testRenderPlantUmlUrl() {
        final var workspace = new Workspace("Name", "Description");

        final var markdown = new Section(Format.Markdown, """
                ## Context

                ```plantuml
                @startuml
                Bob -> Alice : hello
                @enduml
                ```""");
        workspace.getDocumentation().addSection(markdown);

        final var asciidoc = new Section(Format.AsciiDoc, """
                == Context

                ```plantuml
                @startuml
                Bob -> Alice : hello
                @enduml
                ```""");
        workspace.getDocumentation().addSection(asciidoc);

        final var context = new StructurizrDslPluginContext(null, new File("."), workspace, Map.of(
                "plantuml.image.asUrl", "true"
        ));
        new DiagramsPlantUMLPlugin().run(context);

        assertEquals("""
                ## Context

                ![](http://localhost:8081/svg/SoWkIImgAStDuNBAJrBGjLDmpCbCJbMmKiX8pSd9vt98pKi1IG80)
                """, markdown.getContent());

        assertEquals("""
                == Context

                image::http://localhost:8081/svg/SoWkIImgAStDuNBAJrBGjLDmpCbCJbMmKiX8pSd9vt98pKi1IG80[]
                """, asciidoc.getContent());

    }

    @Test
    public void testRenderPlantUmlAsciidocBlocks() {
        final var workspace = new Workspace("Name", "Description");

        final var ascidocDash = new Section(Format.AsciiDoc, """
                ## Context

                [plantuml]
                ----
                Bob -> Alice : hello
                ----""");
        workspace.getDocumentation().addSection(ascidocDash);

        final var asciidocDot = new Section(Format.AsciiDoc, """
                == Context

                [plantuml]
                ....
                Bob -> Alice : hello
                ....""");
        workspace.getDocumentation().addSection(asciidocDot);

        final var context = new StructurizrDslPluginContext(null, new File("."), workspace, Collections.emptyMap());
        new DiagramsPlantUMLPlugin().run(context);

        assertEquals("""
                ## Context

                image::data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiBjb250ZW50U3R5bGVUeXBlPSJ0ZXh0L2NzcyIgZGF0YS1kaWFncmFtLXR5cGU9IlNFUVVFTkNFIiBoZWlnaHQ9IjEyOHB4IiBwcmVzZXJ2ZUFzcGVjdFJhdGlvPSJub25lIiBzdHlsZT0id2lkdGg6MTA3cHg7aGVpZ2h0OjEyOHB4O2JhY2tncm91bmQ6I0ZGRkZGRjsiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDEwNyAxMjgiIHdpZHRoPSIxMDdweCIgem9vbUFuZFBhbj0ibWFnbmlmeSI+PGRlZnMvPjxnPjxnPjx0aXRsZT5Cb2I8L3RpdGxlPjxyZWN0IGZpbGw9IiMwMDAwMDAiIGZpbGwtb3BhY2l0eT0iMC4wMDAwMCIgaGVpZ2h0PSI1MS43MDYiIHdpZHRoPSI4IiB4PSIyMS4wOSIgeT0iMzkuMDY3OSIvPjxsaW5lIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41O3N0cm9rZS1kYXNoYXJyYXk6NS4wLDUuMDsiIHgxPSIyNSIgeDI9IjI1IiB5MT0iMzkuMDY3OSIgeTI9IjkwLjc3MzkiLz48L2c+PGc+PHRpdGxlPkFsaWNlPC90aXRsZT48cmVjdCBmaWxsPSIjMDAwMDAwIiBmaWxsLW9wYWNpdHk9IjAuMDAwMDAiIGhlaWdodD0iNTEuNzA2IiB3aWR0aD0iOCIgeD0iNzUuMDI5IiB5PSIzOS4wNjc5Ii8+PGxpbmUgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7c3Ryb2tlLWRhc2hhcnJheTo1LjAsNS4wOyIgeDE9Ijc4LjYzNiIgeDI9Ijc4LjYzNiIgeTE9IjM5LjA2NzkiIHkyPSI5MC43NzM5Ii8+PC9nPjxnIGNsYXNzPSJwYXJ0aWNpcGFudCBwYXJ0aWNpcGFudC1oZWFkIiBkYXRhLXBhcnRpY2lwYW50PSJCb2IiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDAuMTc5OSIgeD0iNSIgeT0iNSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI2LjE3OTkiIHg9IjEyIiB5PSIyNi45NjU5Ij5Cb2I8L3RleHQ+PC9nPjxnIGNsYXNzPSJwYXJ0aWNpcGFudCBwYXJ0aWNpcGFudC10YWlsIiBkYXRhLXBhcnRpY2lwYW50PSJCb2IiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDAuMTc5OSIgeD0iNSIgeT0iODkuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI2LjE3OTkiIHg9IjEyIiB5PSIxMTEuNzM5OSI+Qm9iPC90ZXh0PjwvZz48ZyBjbGFzcz0icGFydGljaXBhbnQgcGFydGljaXBhbnQtaGVhZCIgZGF0YS1wYXJ0aWNpcGFudD0iQWxpY2UiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDQuNzg1OSIgeD0iNTYuNjM2IiB5PSI1Ii8+PHRleHQgZmlsbD0iIzAwMDAwMCIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMTQiIGxlbmd0aEFkanVzdD0ic3BhY2luZyIgdGV4dExlbmd0aD0iMzAuNzg1OSIgeD0iNjMuNjM2IiB5PSIyNi45NjU5Ij5BbGljZTwvdGV4dD48L2c+PGcgY2xhc3M9InBhcnRpY2lwYW50IHBhcnRpY2lwYW50LXRhaWwiIGRhdGEtcGFydGljaXBhbnQ9IkFsaWNlIj48cmVjdCBmaWxsPSIjRTJFMkYwIiBoZWlnaHQ9IjMzLjA2NzkiIHJ4PSIyLjUiIHJ5PSIyLjUiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41OyIgd2lkdGg9IjQ0Ljc4NTkiIHg9IjU2LjYzNiIgeT0iODkuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjMwLjc4NTkiIHg9IjYzLjYzNiIgeT0iMTExLjczOTkiPkFsaWNlPC90ZXh0PjwvZz48ZyBjbGFzcz0ibWVzc2FnZSIgZGF0YS1wYXJ0aWNpcGFudC0xPSJCb2IiIGRhdGEtcGFydGljaXBhbnQtMj0iQWxpY2UiPjxwb2x5Z29uIGZpbGw9IiMxODE4MTgiIHBvaW50cz0iNjcuMDI5LDY4Ljc3MzksNzcuMDI5LDcyLjc3MzksNjcuMDI5LDc2Ljc3MzksNzEuMDI5LDcyLjc3MzkiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MTsiLz48bGluZSBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjE7IiB4MT0iMjUuMDkiIHgyPSI3My4wMjkiIHkxPSI3Mi43NzM5IiB5Mj0iNzIuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjEzIiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI5LjkzOSIgeD0iMzIuMDkiIHk9IjY2Ljk2NDkiPmhlbGxvPC90ZXh0PjwvZz48IS0tU1JDPVtTeWZGS2oyckt0M0NvS25FTFIxSW80WkRvU2RkMFcwMF0tLT48L2c+PC9zdmc+[]
                """, ascidocDash.getContent());

        assertEquals("""
                == Context

                image::data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiBjb250ZW50U3R5bGVUeXBlPSJ0ZXh0L2NzcyIgZGF0YS1kaWFncmFtLXR5cGU9IlNFUVVFTkNFIiBoZWlnaHQ9IjEyOHB4IiBwcmVzZXJ2ZUFzcGVjdFJhdGlvPSJub25lIiBzdHlsZT0id2lkdGg6MTA3cHg7aGVpZ2h0OjEyOHB4O2JhY2tncm91bmQ6I0ZGRkZGRjsiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDEwNyAxMjgiIHdpZHRoPSIxMDdweCIgem9vbUFuZFBhbj0ibWFnbmlmeSI+PGRlZnMvPjxnPjxnPjx0aXRsZT5Cb2I8L3RpdGxlPjxyZWN0IGZpbGw9IiMwMDAwMDAiIGZpbGwtb3BhY2l0eT0iMC4wMDAwMCIgaGVpZ2h0PSI1MS43MDYiIHdpZHRoPSI4IiB4PSIyMS4wOSIgeT0iMzkuMDY3OSIvPjxsaW5lIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41O3N0cm9rZS1kYXNoYXJyYXk6NS4wLDUuMDsiIHgxPSIyNSIgeDI9IjI1IiB5MT0iMzkuMDY3OSIgeTI9IjkwLjc3MzkiLz48L2c+PGc+PHRpdGxlPkFsaWNlPC90aXRsZT48cmVjdCBmaWxsPSIjMDAwMDAwIiBmaWxsLW9wYWNpdHk9IjAuMDAwMDAiIGhlaWdodD0iNTEuNzA2IiB3aWR0aD0iOCIgeD0iNzUuMDI5IiB5PSIzOS4wNjc5Ii8+PGxpbmUgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7c3Ryb2tlLWRhc2hhcnJheTo1LjAsNS4wOyIgeDE9Ijc4LjYzNiIgeDI9Ijc4LjYzNiIgeTE9IjM5LjA2NzkiIHkyPSI5MC43NzM5Ii8+PC9nPjxnIGNsYXNzPSJwYXJ0aWNpcGFudCBwYXJ0aWNpcGFudC1oZWFkIiBkYXRhLXBhcnRpY2lwYW50PSJCb2IiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDAuMTc5OSIgeD0iNSIgeT0iNSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI2LjE3OTkiIHg9IjEyIiB5PSIyNi45NjU5Ij5Cb2I8L3RleHQ+PC9nPjxnIGNsYXNzPSJwYXJ0aWNpcGFudCBwYXJ0aWNpcGFudC10YWlsIiBkYXRhLXBhcnRpY2lwYW50PSJCb2IiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDAuMTc5OSIgeD0iNSIgeT0iODkuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI2LjE3OTkiIHg9IjEyIiB5PSIxMTEuNzM5OSI+Qm9iPC90ZXh0PjwvZz48ZyBjbGFzcz0icGFydGljaXBhbnQgcGFydGljaXBhbnQtaGVhZCIgZGF0YS1wYXJ0aWNpcGFudD0iQWxpY2UiPjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzMuMDY3OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDQuNzg1OSIgeD0iNTYuNjM2IiB5PSI1Ii8+PHRleHQgZmlsbD0iIzAwMDAwMCIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMTQiIGxlbmd0aEFkanVzdD0ic3BhY2luZyIgdGV4dExlbmd0aD0iMzAuNzg1OSIgeD0iNjMuNjM2IiB5PSIyNi45NjU5Ij5BbGljZTwvdGV4dD48L2c+PGcgY2xhc3M9InBhcnRpY2lwYW50IHBhcnRpY2lwYW50LXRhaWwiIGRhdGEtcGFydGljaXBhbnQ9IkFsaWNlIj48cmVjdCBmaWxsPSIjRTJFMkYwIiBoZWlnaHQ9IjMzLjA2NzkiIHJ4PSIyLjUiIHJ5PSIyLjUiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41OyIgd2lkdGg9IjQ0Ljc4NTkiIHg9IjU2LjYzNiIgeT0iODkuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjMwLjc4NTkiIHg9IjYzLjYzNiIgeT0iMTExLjczOTkiPkFsaWNlPC90ZXh0PjwvZz48ZyBjbGFzcz0ibWVzc2FnZSIgZGF0YS1wYXJ0aWNpcGFudC0xPSJCb2IiIGRhdGEtcGFydGljaXBhbnQtMj0iQWxpY2UiPjxwb2x5Z29uIGZpbGw9IiMxODE4MTgiIHBvaW50cz0iNjcuMDI5LDY4Ljc3MzksNzcuMDI5LDcyLjc3MzksNjcuMDI5LDc2Ljc3MzksNzEuMDI5LDcyLjc3MzkiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MTsiLz48bGluZSBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjE7IiB4MT0iMjUuMDkiIHgyPSI3My4wMjkiIHkxPSI3Mi43NzM5IiB5Mj0iNzIuNzczOSIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjEzIiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI5LjkzOSIgeD0iMzIuMDkiIHk9IjY2Ljk2NDkiPmhlbGxvPC90ZXh0PjwvZz48IS0tU1JDPVtTeWZGS2oyckt0M0NvS25FTFIxSW80WkRvU2RkMFcwMF0tLT48L2c+PC9zdmc+[]
                """, asciidocDot.getContent());
    }
}
