package org.veekhere.core.domain.models.common;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.enums.SceneElementParserSourceIndexer;
import org.veekhere.core.domain.interfaces.base.SceneElementVisitor;
import org.veekhere.core.domain.models.base.Curve;
import org.veekhere.core.domain.utils.ParseUtils;

import java.util.ArrayList;
import java.util.UUID;

public class Ellipse extends Curve {
    private Point center;
    private double xRadius;
    private double yRadius;

    public Ellipse() {
        super(ElementType.Ellipse);
    }
    public Ellipse(Point center, double xRadius, double yRadius) {
        super(ElementType.Ellipse);

        this.center = new Point(center);
        this.xRadius = xRadius;
        this.yRadius = yRadius;
    }
    public Ellipse(UUID uuid, ArrayList<String> source) {
        super(ElementType.Ellipse, uuid);

        this.center = this.getCenter(source);
        this.xRadius = this.getXRadius(source);
        this.yRadius = this.getYRadius(source);
    }
    public Ellipse(Ellipse ellipse) {
        super(ElementType.Ellipse);

        this.center = new Point(ellipse.center);
        this.xRadius = ellipse.xRadius;
        this.yRadius = ellipse.yRadius;
    }

    @Override
    public double getTMin() {
        return 0;
    }

    @Override
    public double getTMax() {
        return 2 * Math.PI;
    }

    public Point getCenter() {
        return this.center;
    }

    public double getXRadius() {
        return this.xRadius;
    }

    public double getYRadius() {
        return this.yRadius;
    }

    @Override
    public Point calculate(double t) {
        final double x = Math.cos(t) * this.xRadius + center.getX();
        final double y = Math.sin(t) * this.yRadius + center.getY();
        return new Point(x, y);
    }

    public String stringify(Boolean skipUuids) {
        final Point center = this.getCenter();
        final String buffer = String.format("%s %f %f %f %f;\n",
                this.getType(),
                center.getX(),
                center.getY(),
                this.getXRadius(),
                this.getYRadius()
        );

        return skipUuids
                ? buffer
                : String.format("%s %s", this.uuid, buffer);
    }

    private Point getCenter(ArrayList<String> source) {
        final int xIndex = parserKeyMap.get(SceneElementParserSourceIndexer.XA_COORDINATE);
        final int yIndex = parserKeyMap.get(SceneElementParserSourceIndexer.YA_COORDINATE);
        final double x = ParseUtils.parseDouble(source.get(xIndex));
        final double y = ParseUtils.parseDouble(source.get(yIndex));

        return new Point(x, y);
    }

    private double getXRadius(ArrayList<String> source) {
        final int xIndex = parserKeyMap.get(SceneElementParserSourceIndexer.XB_COORDINATE);
        return ParseUtils.parseDouble(source.get(xIndex));
    }

    private double getYRadius(ArrayList<String> source) {
        final int yIndex = parserKeyMap.get(SceneElementParserSourceIndexer.YB_COORDINATE);
        return ParseUtils.parseDouble(source.get(yIndex));
    }

    @Override
    public void accept(SceneElementVisitor sceneElementVisitor) {
        sceneElementVisitor.visit(this);
    }
}
