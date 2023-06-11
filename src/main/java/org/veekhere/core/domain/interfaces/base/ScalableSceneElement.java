package org.veekhere.core.domain.interfaces.base;

import org.veekhere.core.domain.models.base.SceneElement;

public interface ScalableSceneElement<T extends SceneElement> {
    T scaleBy(double times);
}
