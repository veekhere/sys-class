package org.veekhere.core.domain.interfaces.base;

import org.veekhere.core.domain.models.base.SceneElement;

public interface ComparableSceneElement<T extends SceneElement> {
    boolean areEqualWith(T target);
}
