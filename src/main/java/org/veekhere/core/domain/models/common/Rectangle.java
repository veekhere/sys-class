package org.veekhere.core.domain.models.common;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.enums.SceneElementParserSourceIndexer;
import org.veekhere.core.domain.interfaces.base.ComparableSceneElement;
import org.veekhere.core.domain.interfaces.base.ScalableSceneElement;
import org.veekhere.core.domain.interfaces.base.SceneElementVisitor;
import org.veekhere.core.domain.models.base.SceneElement;
import org.veekhere.core.domain.utils.ParseUtils;

import java.util.ArrayList;
import java.util.UUID;

public class Rectangle extends SceneElement implements
        ComparableSceneElement<Rectangle>,
        ScalableSceneElement<Rectangle> {
    private Point a;
    private Point b;

    public Rectangle() {
        super(ElementType.Rectangle);
    }
    public Rectangle(Point a, Point b) {
        super(ElementType.Rectangle);

        this.a = a;
        this.b = b;
    }
    public Rectangle(UUID uuid, ArrayList<String> source) {
        super(ElementType.Rectangle, uuid);

        this.a = this.getA(source);
        this.b = this.getB(source);
    }
    public Rectangle(Rectangle rectangle) {
        super(ElementType.Rectangle);

        this.a = rectangle.a;
        this.b = rectangle.b;
    }

    public Point getA() {
        return this.a;
    }

    public Point getB() {
        return this.b;
    }

    public Rectangle scaleBy(double times) {
        this.a.scaleBy(times);
        this.b.scaleBy(times);
        return this;
    }

    public boolean areEqualWith(Rectangle rectangle) {
        return (this.a.areEqualWith(rectangle.a) && this.b.areEqualWith(rectangle.b));
    }

    public String stringify(Boolean skipUuids) {
        final Point a = this.getA();
        final Point b = this.getB();
        final String buffer = String.format("%s %f %f %f %f;\n",
                this.getType(),
                a.getX(),
                a.getY(),
                b.getX(),
                b.getY()
        );

        return skipUuids
                ? buffer
                : String.format("%s %s", this.uuid, buffer);
    }

    @Override
    public void accept(SceneElementVisitor sceneElementVisitor) {
        sceneElementVisitor.visit(this);
    }

    private Point getA(ArrayList<String> source) {
        final double xa = ParseUtils.parseDouble(source.get(parserKeyMap.get(SceneElementParserSourceIndexer.XA_COORDINATE)));
        final double ya = ParseUtils.parseDouble(source.get(parserKeyMap.get(SceneElementParserSourceIndexer.YA_COORDINATE)));
        return new Point(xa, ya);
    }

    private Point getB(ArrayList<String> source) {
        final double xb = ParseUtils.parseDouble(source.get(parserKeyMap.get(SceneElementParserSourceIndexer.XB_COORDINATE)));
        final double yb = ParseUtils.parseDouble(source.get(parserKeyMap.get(SceneElementParserSourceIndexer.YB_COORDINATE)));
        return new Point(xb, yb);
    }
}
