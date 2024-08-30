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

                <img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiBjb250ZW50U3R5bGVUeXBlPSJ0ZXh0L2NzcyIgaGVpZ2h0PSIxMjBweCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSIgc3R5bGU9IndpZHRoOjExMHB4O2hlaWdodDoxMjBweDtiYWNrZ3JvdW5kOiNGRkZGRkY7IiB2ZXJzaW9uPSIxLjEiIHZpZXdCb3g9IjAgMCAxMTAgMTIwIiB3aWR0aD0iMTEwcHgiIHpvb21BbmRQYW49Im1hZ25pZnkiPjxkZWZzLz48Zz48bGluZSBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjAuNTtzdHJva2UtZGFzaGFycmF5OjUuMCw1LjA7IiB4MT0iMjUiIHgyPSIyNSIgeTE9IjM2LjI5NjkiIHkyPSI4NS40Mjk3Ii8+PGxpbmUgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7c3Ryb2tlLWRhc2hhcnJheTo1LjAsNS4wOyIgeDE9IjgwLjEwOTQiIHgyPSI4MC4xMDk0IiB5MT0iMzYuMjk2OSIgeTI9Ijg1LjQyOTciLz48cmVjdCBmaWxsPSIjRTJFMkYwIiBoZWlnaHQ9IjMwLjI5NjkiIHJ4PSIyLjUiIHJ5PSIyLjUiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41OyIgd2lkdGg9IjQxLjA1NjYiIHg9IjUiIHk9IjUiLz48dGV4dCBmaWxsPSIjMDAwMDAwIiBmb250LWZhbWlseT0ic2Fucy1zZXJpZiIgZm9udC1zaXplPSIxNCIgbGVuZ3RoQWRqdXN0PSJzcGFjaW5nIiB0ZXh0TGVuZ3RoPSIyNy4wNTY2IiB4PSIxMiIgeT0iMjQuOTk1MSI+Qm9iPC90ZXh0PjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzAuMjk2OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDEuMDU2NiIgeD0iNSIgeT0iODQuNDI5NyIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI3LjA1NjYiIHg9IjEyIiB5PSIxMDQuNDI0OCI+Qm9iPC90ZXh0PjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzAuMjk2OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDcuNjY3IiB4PSI1Ny4xMDk0IiB5PSI1Ii8+PHRleHQgZmlsbD0iIzAwMDAwMCIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMTQiIGxlbmd0aEFkanVzdD0ic3BhY2luZyIgdGV4dExlbmd0aD0iMzMuNjY3IiB4PSI2NC4xMDk0IiB5PSIyNC45OTUxIj5BbGljZTwvdGV4dD48cmVjdCBmaWxsPSIjRTJFMkYwIiBoZWlnaHQ9IjMwLjI5NjkiIHJ4PSIyLjUiIHJ5PSIyLjUiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41OyIgd2lkdGg9IjQ3LjY2NyIgeD0iNTcuMTA5NCIgeT0iODQuNDI5NyIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjMzLjY2NyIgeD0iNjQuMTA5NCIgeT0iMTA0LjQyNDgiPkFsaWNlPC90ZXh0Pjxwb2x5Z29uIGZpbGw9IiMxODE4MTgiIHBvaW50cz0iNjguOTQyOSw2My40Mjk3LDc4Ljk0MjksNjcuNDI5Nyw2OC45NDI5LDcxLjQyOTcsNzIuOTQyOSw2Ny40Mjk3IiBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjEuMDsiLz48bGluZSBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjEuMDsiIHgxPSIyNS41MjgzIiB4Mj0iNzQuOTQyOSIgeTE9IjY3LjQyOTciIHkyPSI2Ny40Mjk3Ii8+PHRleHQgZmlsbD0iIzAwMDAwMCIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMTMiIGxlbmd0aEFkanVzdD0ic3BhY2luZyIgdGV4dExlbmd0aD0iMzEuNDE0NiIgeD0iMzIuNTI4MyIgeT0iNjIuMzYzOCI+aGVsbG88L3RleHQ+PCEtLVNSQz1bU3lmRktqMnJLdDNDb0tuRUxSMUlvNFpEb1NhNzAwMDBdLS0+PC9nPjwvc3ZnPg==">
                """, markdown.getContent());

        assertEquals("""
                == Context

                image::data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiBjb250ZW50U3R5bGVUeXBlPSJ0ZXh0L2NzcyIgaGVpZ2h0PSIxMjBweCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSIgc3R5bGU9IndpZHRoOjExMHB4O2hlaWdodDoxMjBweDtiYWNrZ3JvdW5kOiNGRkZGRkY7IiB2ZXJzaW9uPSIxLjEiIHZpZXdCb3g9IjAgMCAxMTAgMTIwIiB3aWR0aD0iMTEwcHgiIHpvb21BbmRQYW49Im1hZ25pZnkiPjxkZWZzLz48Zz48bGluZSBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjAuNTtzdHJva2UtZGFzaGFycmF5OjUuMCw1LjA7IiB4MT0iMjUiIHgyPSIyNSIgeTE9IjM2LjI5NjkiIHkyPSI4NS40Mjk3Ii8+PGxpbmUgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7c3Ryb2tlLWRhc2hhcnJheTo1LjAsNS4wOyIgeDE9IjgwLjEwOTQiIHgyPSI4MC4xMDk0IiB5MT0iMzYuMjk2OSIgeTI9Ijg1LjQyOTciLz48cmVjdCBmaWxsPSIjRTJFMkYwIiBoZWlnaHQ9IjMwLjI5NjkiIHJ4PSIyLjUiIHJ5PSIyLjUiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41OyIgd2lkdGg9IjQxLjA1NjYiIHg9IjUiIHk9IjUiLz48dGV4dCBmaWxsPSIjMDAwMDAwIiBmb250LWZhbWlseT0ic2Fucy1zZXJpZiIgZm9udC1zaXplPSIxNCIgbGVuZ3RoQWRqdXN0PSJzcGFjaW5nIiB0ZXh0TGVuZ3RoPSIyNy4wNTY2IiB4PSIxMiIgeT0iMjQuOTk1MSI+Qm9iPC90ZXh0PjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzAuMjk2OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDEuMDU2NiIgeD0iNSIgeT0iODQuNDI5NyIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjI3LjA1NjYiIHg9IjEyIiB5PSIxMDQuNDI0OCI+Qm9iPC90ZXh0PjxyZWN0IGZpbGw9IiNFMkUyRjAiIGhlaWdodD0iMzAuMjk2OSIgcng9IjIuNSIgcnk9IjIuNSIgc3R5bGU9InN0cm9rZTojMTgxODE4O3N0cm9rZS13aWR0aDowLjU7IiB3aWR0aD0iNDcuNjY3IiB4PSI1Ny4xMDk0IiB5PSI1Ii8+PHRleHQgZmlsbD0iIzAwMDAwMCIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMTQiIGxlbmd0aEFkanVzdD0ic3BhY2luZyIgdGV4dExlbmd0aD0iMzMuNjY3IiB4PSI2NC4xMDk0IiB5PSIyNC45OTUxIj5BbGljZTwvdGV4dD48cmVjdCBmaWxsPSIjRTJFMkYwIiBoZWlnaHQ9IjMwLjI5NjkiIHJ4PSIyLjUiIHJ5PSIyLjUiIHN0eWxlPSJzdHJva2U6IzE4MTgxODtzdHJva2Utd2lkdGg6MC41OyIgd2lkdGg9IjQ3LjY2NyIgeD0iNTcuMTA5NCIgeT0iODQuNDI5NyIvPjx0ZXh0IGZpbGw9IiMwMDAwMDAiIGZvbnQtZmFtaWx5PSJzYW5zLXNlcmlmIiBmb250LXNpemU9IjE0IiBsZW5ndGhBZGp1c3Q9InNwYWNpbmciIHRleHRMZW5ndGg9IjMzLjY2NyIgeD0iNjQuMTA5NCIgeT0iMTA0LjQyNDgiPkFsaWNlPC90ZXh0Pjxwb2x5Z29uIGZpbGw9IiMxODE4MTgiIHBvaW50cz0iNjguOTQyOSw2My40Mjk3LDc4Ljk0MjksNjcuNDI5Nyw2OC45NDI5LDcxLjQyOTcsNzIuOTQyOSw2Ny40Mjk3IiBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjEuMDsiLz48bGluZSBzdHlsZT0ic3Ryb2tlOiMxODE4MTg7c3Ryb2tlLXdpZHRoOjEuMDsiIHgxPSIyNS41MjgzIiB4Mj0iNzQuOTQyOSIgeTE9IjY3LjQyOTciIHkyPSI2Ny40Mjk3Ii8+PHRleHQgZmlsbD0iIzAwMDAwMCIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMTMiIGxlbmd0aEFkanVzdD0ic3BhY2luZyIgdGV4dExlbmd0aD0iMzEuNDE0NiIgeD0iMzIuNTI4MyIgeT0iNjIuMzYzOCI+aGVsbG88L3RleHQ+PCEtLVNSQz1bU3lmRktqMnJLdDNDb0tuRUxSMUlvNFpEb1NhNzAwMDBdLS0+PC9nPjwvc3ZnPg==[]
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
}
