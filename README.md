# Structurizr · Diagrams Plugin

Plugin for [Structurizr](https://structurizr.com) that allows to generate various types of diagrams.

## Download

Artifact can be downloaded
from [Maven Central](https://central.sonatype.com/artifact/com.github.devmix.process.acto/devmix-process-acto-core).

```xml

<dependency>
    <groupId>com.github.devmix.process.acto</groupId>
    <artifactId>devmix-process-acto-core</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Usage

Currently only one type of diagram is supported - [PlantUML](https://plantuml.com).

Place all plugin JARs files ([plugins](dsl/plugins)) into the `plugins` directory of your Structurizr workspace.

```text
───lite─┬─docs
        ├─adrs
        ├─images
        ├─plugins─┬─plantuml-mit-1.2025.2.jar
        │         └─structurizr-plugin-diagrams-0.1.0-SNAPSHOT.jar
        ├─workspace.dsl
        └─workspace.json

```

### PlantUML

Based on the plugin [plantuml-and-mermaid](https://github.com/structurizr/examples/tree/main/dsl/plantuml-and-mermaid)
from examples.

**Additional functionality**:

- Render PlantUML diagrams without PlantUML Server.
- Allowed to use embedded images instead URL's (`data:image/*;base64``).
- Images cache for rendering witout PlantUML Server.

Default values for plugin properties:

- `plantuml.render.usingServer` = `false`
- `plantuml.image.asUrl` = `false`
- `plantuml.url` = `http://localhost:8081`

#### Offline rendering

Put [plantuml-mit](https://mvnrepository.com/artifact/net.sourceforge.plantuml/plantuml-mit) JAR into your `plugins`
folder.

Then you need to add the plugin to the workspace:

```
!plugin com.github.devmix.structurizr.plugin.diagrams.plantuml.DiagramsPlantUMLPlugin {
    "plantuml.render.usingServer" "false"
}
```

#### Embedded images

To enable embedded images:

```
!plugin com.github.devmix.structurizr.plugin.diagrams.plantuml.DiagramsPlantUMLPlugin {
    "plantuml.image.asUrl" "false"
}
```

#### Online rendering

To enable rendering using PlantUML Server:

```
!plugin com.github.devmix.structurizr.plugin.diagrams.plantuml.DiagramsPlantUMLPlugin {
    "plantuml.image.asUrl" "true"
    "plantuml.render.usingServer" "true"
    "plantuml.url" "https://www.plantuml.com/plantuml"
}
```
