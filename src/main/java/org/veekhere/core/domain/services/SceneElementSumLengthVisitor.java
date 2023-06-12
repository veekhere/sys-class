package org.veekhere.core.domain.services;

import org.veekhere.core.domain.interfaces.base.SceneElementVisitor;
import org.veekhere.core.domain.models.common.*;

import java.util.ArrayList;

public class SceneElementSumLengthVisitor implements SceneElementVisitor {
    public double length = 0;

    @Override
    public void visit(Point point) { /* nothing */ }

    @Override
    public void visit(Line line) {
        this.length += Math.sqrt(
                Math.pow((line.getEnd().getX() - line.getStart().getX()), 2)
                        + Math.pow((line.getEnd().getY() - line.getStart().getY()), 2)
        );
    }

    @Override
    public void visit(Polyline polyline) {
        ArrayList<Point> points = polyline.getPoints();

        for (int i = 0; i < points.size() - 1; i++) {
            length += Math.sqrt(
                    Math.pow((points.get(i + 1).getX() - points.get(i).getX()), 2)
                            + Math.pow((points.get(i + 1).getY() - points.get(i).getY()), 2)
            );
        }
    }

    @Override
    public void visit(Rectangle rectangle) {
        Point a = rectangle.getA();
        Point b = rectangle.getB();
        Point c = new Point(a.getX(), b.getY());

        double sideA = Math.sqrt(Math.pow((c.getX() - a.getX()), 2) + Math.pow((c.getY() - a.getY()), 2));
        double sideB = Math.sqrt(Math.pow((c.getX() - b.getX()), 2) + Math.pow((c.getY() - b.getY()), 2));

        this.length += 2 * (sideA + sideB);
    }

    @Override
    public void visit(Ellipse ellipse) {
        Point a;
        Point b;
        final double dT = (ellipse.getTMax() - ellipse.getTMin()) / 100;

        for (double t = ellipse.getTMin(); t < ellipse.getTMax(); t += dT) {
            a = ellipse.calculate(t);
            b = ellipse.calculate(t + dT);
            this.length += Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
