package org.veekhere;

import org.veekhere.core.domain.models.GeometricScene;
import org.veekhere.core.domain.models.common.*;
import org.veekhere.core.domain.services.SceneElementOperatorInjector;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Point point = new Point(3.2, 5.2);
        Point point2 = new Point(4.8, 1.2);
        Line line = new Line(new Point(0, 0), new Point(1, 1));
        Polyline polyline = new Polyline(new ArrayList<>() {
            {
                add(new Point(0, 0));
                add(new Point(1, 0.5));
                add(new Point(2, 1));
            }
        });
        Ellipse ellipse = new Ellipse(new Point(0, 0), 1, 0.5);
        Rectangle rectangle = new Rectangle(
                new Point(3, 8),
                new Point(6, 10)
        );

        SceneElementOperatorInjector injector = new SceneElementOperatorInjector();
        GeometricScene scene = new GeometricScene(
                injector.getSceneElementParser(),
                injector.getSceneElementSumLengthVisitor()
        );

        System.out.print("\n");

        scene.add(point)
                .add(point2)
                .add(line)
                .add(polyline)
                .add(ellipse)
                .add(rectangle);

        scene.display(true);
        System.out.print("\n");

        scene.saveScene();
        scene.parseData();

        scene.display(false);

        System.out.print("\n\n");

        scene.displayLength();

        System.out.print("\n");
    }
}
