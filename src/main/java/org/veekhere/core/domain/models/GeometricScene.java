package org.veekhere.core.domain.models;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.interfaces.base.StringfiableSceneElement;
import org.veekhere.core.domain.interfaces.services.SceneElementParser;
import org.veekhere.core.domain.models.base.SceneElement;
import org.veekhere.core.domain.services.FileService;
import org.veekhere.core.domain.services.SceneElementSumLengthVisitor;

import java.util.ArrayList;
import java.util.UUID;

public class GeometricScene implements StringfiableSceneElement {
    public UUID uuid = UUID.randomUUID();
    private final ArrayList<SceneElement> scene = new ArrayList<>();
    private final SceneElementParser sceneElementParser;
    private final SceneElementSumLengthVisitor sceneElementSumLengthVisitor;
    private String fileName;

    public GeometricScene(
        SceneElementParser sceneElementParser,
        SceneElementSumLengthVisitor sceneElementSumLengthVisitor
    ) {
        this.sceneElementParser = sceneElementParser;
        this.sceneElementSumLengthVisitor = sceneElementSumLengthVisitor;
    }

    public ArrayList<SceneElement> getScene() {
        return this.scene;
    }

    public GeometricScene add(SceneElement element) {
        this.scene.add(element);

        return this;
    }

    public void display() {
        System.out.printf(this.stringify(false));
    }

    public void display(Boolean skipUuids) {
        System.out.printf(this.stringify(skipUuids));
    }

    public void displayLength() {
        for(SceneElement element: this.scene) {
            element.visit(this.sceneElementSumLengthVisitor);
        }
        System.out.print(this.sceneElementSumLengthVisitor.length);
    }

    public void saveScene() {
        String data = this.stringify(false);
        this.fileName = FileService.generateName(this.uuid, ElementType.Scene);
        FileService.createFile(data, this.fileName);
    }

    public void parseData() {
        ArrayList<ArrayList<String>> data = FileService.readFile(this.fileName);
        for (ArrayList<String> list : data) {
            this.add(this.sceneElementParser.parse(list));
        }
    }

    public String stringify(Boolean skipUuids) {
        String buffer = "";

        for (SceneElement element : this.getScene()) {
            buffer = buffer.concat(element.stringify(skipUuids));
        }

        return buffer.trim();
    }
}
