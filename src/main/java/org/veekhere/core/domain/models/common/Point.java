package org.veekhere.core.domain.models.common;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.enums.SceneElementParserSourceIndexer;
import org.veekhere.core.domain.interfaces.base.ComparableSceneElement;
import org.veekhere.core.domain.interfaces.base.ComputationalSceneElement;
import org.veekhere.core.domain.interfaces.base.ScalableSceneElement;
import org.veekhere.core.domain.interfaces.base.SceneElementVisitor;
import org.veekhere.core.domain.models.base.SceneElement;
import org.veekhere.core.domain.utils.ParseUtils;

import java.util.ArrayList;
import java.util.UUID;

public class Point extends SceneElement implements
        ComparableSceneElement<Point>,
        ScalableSceneElement<Point>,
        ComputationalSceneElement<Point> {

    private double x;
    private double y;

    public Point() {
        super(ElementType.Point);
    }
    public Point(double x) {
        super(ElementType.Point);

        this.x = x;
    }
    public Point(double x, double y) {
        super(ElementType.Point);

        this.x = x;
        this.y = y;
    }
    public Point(UUID uuid, ArrayList<String> source) {
        super(ElementType.Point, uuid);

        int xIndex = parserKeyMap.get(SceneElementParserSourceIndexer.XA_COORDINATE);
        int yIndex = parserKeyMap.get(SceneElementParserSourceIndexer.YA_COORDINATE);

        this.x = ParseUtils.parseDouble(source.get(xIndex));
        this.y = ParseUtils.parseDouble(source.get(yIndex));
    }
    public Point(Point point) {
        super(ElementType.Point);

        this.x = point.x;
        this.y = point.y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point scaleBy(double times) {
        this.x *= times;
        this.y *= times;

        return new Point(this);
    }

    public boolean areEqualWith(Point point) {
        return ((this.x == point.x) && (this.y == point.y));
    }

    public Point addWith(Point point) {
        this.x += point.x;
        this.y += point.y;
        return new Point(this);
    }

    public Point subtractWith(Point point) {
        this.x -= point.x;
        this.y -= point.y;
        return new Point(this);
    }

    public String stringify(Boolean skipUuids) {
        String buffer = String.format("%s %f %f;\n",
                this.getType(),
                this.getX(),
                this.getY()
        );

        return skipUuids
                ? buffer
                : String.format("%s %s", this.uuid, buffer);
    }

    @Override
    public void visit(SceneElementVisitor sceneElementVisitor) {
        sceneElementVisitor.visit(this);
    }
}
