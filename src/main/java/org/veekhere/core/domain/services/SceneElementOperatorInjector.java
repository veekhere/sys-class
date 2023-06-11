package org.veekhere.core.domain.services;

import org.veekhere.core.domain.interfaces.injectors.InjectorSceneElementOperator;
import org.veekhere.core.domain.interfaces.services.SceneElementParser;

public class SceneElementOperatorInjector implements InjectorSceneElementOperator {
    private SceneElementParser sceneElementParser;
    private SceneElementSumLengthVisitor sceneElementSumLengthVisitor;

    public SceneElementParser getSceneElementParser() {
        if (this.sceneElementParser != null) {
            return this.sceneElementParser;
        }
        this.sceneElementParser = new SceneElementParserService();
        return this.sceneElementParser;
    }

    public SceneElementSumLengthVisitor getSceneElementSumLengthVisitor() {
        if (this.sceneElementSumLengthVisitor != null) {
            return this.sceneElementSumLengthVisitor;
        }
        this.sceneElementSumLengthVisitor = new SceneElementSumLengthVisitor();
        return this.sceneElementSumLengthVisitor;
    }
}
