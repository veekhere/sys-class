package org.veekhere.core.domain.services;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.enums.SceneElementParserSourceIndexer;
import org.veekhere.core.domain.interfaces.services.SceneElementParser;
import org.veekhere.core.domain.models.base.SceneElement;
import org.veekhere.core.domain.models.common.*;
import org.veekhere.core.domain.utils.ParseUtils;

import java.util.ArrayList;
import java.util.UUID;

public class SceneElementParserService implements SceneElementParser {

    private ArrayList<String> source;

    public SceneElement parse(ArrayList<String> source) {
        this.source = source;

        int elementTypeIndex = ParseUtils.parserKeyMap.get(SceneElementParserSourceIndexer.ELEMENT_TYPE);
        int uuidIndex = ParseUtils.parserKeyMap.get(SceneElementParserSourceIndexer.UUID);

        ElementType type = ElementType.valueOf(this.source.get(elementTypeIndex));
        UUID uuid = UUID.fromString(this.source.get(uuidIndex));

        return this.getElement(type, uuid);
    }

    private SceneElement getElement(ElementType type, UUID uuid) {
        return switch (type) {
            case Point -> new Point(uuid, this.source);
            case Line -> new Line(uuid, this.source);
            case Polyline -> new Polyline(uuid, this.source);
            case Ellipse -> new Ellipse(uuid, this.source);
            case Rectangle -> new Rectangle(uuid, this.source);
            default -> throw new RuntimeException("Unknown scene element type");
        };
    }
}
