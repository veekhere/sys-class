package org.veekhere.core.domain.interfaces.base;

import org.veekhere.core.domain.models.base.SceneElement;

public interface ComputationalSceneElement<T extends SceneElement> {
    T addWith(T target);
    T subtractWith(T target);
}
