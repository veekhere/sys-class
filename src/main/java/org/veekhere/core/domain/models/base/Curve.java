package org.veekhere.core.domain.models.base;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.models.common.Point;

import java.util.UUID;

public abstract class Curve extends SceneElement {
    public Curve(ElementType type) {
        super(type);
    }
    public Curve(ElementType type, UUID uuid) {
        super(type, uuid);
    }

    public double getTMin() {
        throw new RuntimeException("Not implemented.");
    }
    public double getTMax() {
        throw new RuntimeException("Not implemented.");
    }
    public Point calculate(double t) {
        throw new RuntimeException("Not implemented.");
    }
}
