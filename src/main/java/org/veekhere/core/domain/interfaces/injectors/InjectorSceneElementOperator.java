package org.veekhere.core.domain.interfaces.injectors;

import org.veekhere.core.domain.interfaces.services.SceneElementParser;
import org.veekhere.core.domain.services.SceneElementSumLengthVisitor;

public interface InjectorSceneElementOperator {
    SceneElementParser getSceneElementParser();
    SceneElementSumLengthVisitor getSceneElementSumLengthVisitor();
}
