package org.veekhere.core.domain.models.common;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.enums.SceneElementParserSourceIndexer;
import org.veekhere.core.domain.interfaces.base.ComparableSceneElement;
import org.veekhere.core.domain.interfaces.base.ScalableSceneElement;
import org.veekhere.core.domain.interfaces.base.SceneElementVisitor;
import org.veekhere.core.domain.models.base.Curve;
import org.veekhere.core.domain.utils.ParseUtils;

import java.util.ArrayList;
import java.util.UUID;

public class Line extends Curve implements
        ComparableSceneElement<Line>,
        ScalableSceneElement<Line> {

    private Point start;
    private Point end;

    public Line() {
        super(ElementType.Line);
    }
    public Line(Point start, Point end) {
        super(ElementType.Line);

        this.start = start;
        this.end = end;
    }
    public Line(UUID uuid, ArrayList<String> source) {
        super(ElementType.Line, uuid);

        this.start = this.getStart(source);
        this.end = this.getEnd(source);
    }
    public Line(Line line) {
        super(ElementType.Line);

        this.start = line.start;
        this.end = line.end;
    }

    public Point getStart() {
        return new Point(this.start);
    }

    public Point getEnd() {
        return new Point(this.end);
    }

    @Override
    public double getTMin() {
        return 0;
    }

    @Override
    public double getTMax() {
        return 1;
    }

    @Override
    public Point calculate(double t) {
        this.start.scaleBy(1 - t).addWith(this.end.scaleBy(t));
        return new Point(this.start);
    }

    public Line scaleBy(double times) {
        this.start.scaleBy(times);
        this.end.scaleBy(times);
        return new Line(this);
    }

    public boolean areEqualWith(Line line) {
        return (this.start.areEqualWith(line.start) && this.end.areEqualWith(line.end));
    }

    public double length() {
        final double arg1 = Math.pow((this.end.getX() - this.start.getX()), 2);
        final double arg2 = Math.pow((this.end.getY() - this.start.getY()), 2);
        return Math.sqrt(arg1 + arg2);
    }

    private Point getStart(ArrayList<String> source) {
        final int xIndex = parserKeyMap.get(SceneElementParserSourceIndexer.XA_COORDINATE);
        final int yIndex = parserKeyMap.get(SceneElementParserSourceIndexer.YA_COORDINATE);
        final double xa = ParseUtils.parseDouble(source.get(xIndex));
        final double ya = ParseUtils.parseDouble(source.get(yIndex));

        return new Point(xa, ya);
    }

    private Point getEnd(ArrayList<String> source) {
        final int xIndex = parserKeyMap.get(SceneElementParserSourceIndexer.XB_COORDINATE);
        final int yIndex = parserKeyMap.get(SceneElementParserSourceIndexer.YB_COORDINATE);
        final double xb = ParseUtils.parseDouble(source.get(xIndex));
        final double yb = ParseUtils.parseDouble(source.get(yIndex));

        return new Point(xb, yb);
    }

    public String stringify(Boolean skipUuids) {
        final Point start = this.getStart();
        final Point end = this.getEnd();
        final String buffer = String.format("%s %f %f %f %f;\n",
                this.getType(),
                start.getX(),
                start.getY(),
                end.getY(),
                end.getX()
        );

        return skipUuids
                ? buffer
                : String.format("%s %s", this.uuid, buffer);
    }

    @Override
    public void accept(SceneElementVisitor sceneElementVisitor) {
        sceneElementVisitor.visit(this);
    }
}
