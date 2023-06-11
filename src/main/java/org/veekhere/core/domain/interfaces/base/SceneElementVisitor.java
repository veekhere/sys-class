package org.veekhere.core.domain.interfaces.base;

import org.veekhere.core.domain.models.common.*;

public interface SceneElementVisitor {
    void visit(Point point);
    void visit(Line line);
    void visit(Polyline polyline);
    void visit(Rectangle rectangle);
    void visit(Ellipse ellipse);
}
