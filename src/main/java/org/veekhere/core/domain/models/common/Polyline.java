package org.veekhere.core.domain.models.common;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.enums.SceneElementParserSourceIndexer;
import org.veekhere.core.domain.interfaces.base.ScalableSceneElement;
import org.veekhere.core.domain.interfaces.base.SceneElementVisitor;
import org.veekhere.core.domain.models.base.Curve;
import org.veekhere.core.domain.utils.ParseUtils;

import java.util.ArrayList;
import java.util.UUID;

public class Polyline extends Curve implements ScalableSceneElement<Polyline> {
    private ArrayList<Point> points = new ArrayList<>();
    
    public Polyline() {
        super(ElementType.Polyline);
    }
    public Polyline(ArrayList<Point> points) {
        super(ElementType.Polyline);
        this.points = new ArrayList<>(points);
    }
    public Polyline(UUID uuid, ArrayList<String> source) {
        super(ElementType.Polyline, uuid);
        this.points = new ArrayList<>(this.parsePoints(source));
    }
    public Polyline(Polyline polyline) {
        super(ElementType.Polyline);
        this.points = polyline.points;
    }

    @Override
    public double getTMin() {
        return 0.0;
    }

    @Override
    public double getTMax() {
        return 0.0;
    }

    public ArrayList<Point> getPoints() {
        return new ArrayList<>(this.points);
    }

    public Polyline scaleBy(double times) {
        for (Point point : this.points) {
            point.scaleBy(times);
        }
        return this;
    }

    public String stringify(Boolean skipUuids) {
        final ArrayList<Point> points = new ArrayList<>(this.getPoints());
        String tmp = String.format("%s %d ", this.getType(), points.size());

        for (Point point : points) {
            final String atom = String.format("%f %f ", point.getX(), point.getY());
            tmp = tmp.concat(atom);
        }

        final String buffer = tmp.trim().concat(";\n");

        return skipUuids
                ? buffer
                : String.format("%s %s", this.uuid, buffer);
    }

    @Override
    public void accept(SceneElementVisitor sceneElementVisitor) {
        sceneElementVisitor.visit(this);
    }

    private ArrayList<Point> parsePoints(ArrayList<String> source) {
        final int size = Integer.decode(source.get(parserKeyMap.get(SceneElementParserSourceIndexer.POLYLINE_SIZE)));
        final int countOfPoints = size * 2;

        final ArrayList<Point> points = new ArrayList<>(countOfPoints);

        final int start = parserKeyMap.get(SceneElementParserSourceIndexer.POLYLINE_DESCRIPTION_START);
        final int end = countOfPoints + 2;

        for (int i = start; i < end; i += 2) {
            double x = ParseUtils.parseDouble(source.get(i));
            double y = ParseUtils.parseDouble(source.get(i + 1));
            points.add(new Point(x, y));
        }

        return points;
    }
}
