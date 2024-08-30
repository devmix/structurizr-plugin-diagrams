# DiagramsPlantUMLPlugin

Based on the plugin [plantuml-and-mermaid](https://github.com/structurizr/examples/tree/main/dsl/plantuml-and-mermaid) from examples. 

Additional functionality:
- Render PlantUML diagrams without PlantUML Server.
- Allowed to use embedded images instead URL's (`data:image/*;base64``).
- Images cache for rendering witout PlantUML Server. 

Default value for the plugin properties:
- `plantuml.render.usingServer` = `false`
- `plantuml.image.asUrl` = `false`
- `plantuml.url` = `http://localhost:8081`

## Offline rendering

Put [plantuml-mit](https://mvnrepository.com/artifact/net.sourceforge.plantuml/plantuml-mit) JAR into your `plugins` folder.

Then you need to add the plugin to the workspace:

```
!plugin com.github.devmix.structurizr.plugin.diagrams.plantuml.DiagramsPlantUMLPlugin {
    "plantuml.render.usingServer" "false"
}
```

## Embedded images

To enable embedded images:

```
!plugin com.github.devmix.structurizr.plugin.diagrams.plantuml.DiagramsPlantUMLPlugin {
    "plantuml.image.asUrl" "false"
}
```

## Online rendering

To enable rendering using PlantUML Server:

```
!plugin com.github.devmix.structurizr.plugin.diagrams.plantuml.DiagramsPlantUMLPlugin {
    "plantuml.image.asUrl" "true"
    "plantuml.render.usingServer" "true"
    "plantuml.url" "https://www.plantuml.com/plantuml"
}
```
