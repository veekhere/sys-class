package org.veekhere.core.domain.models.base;

import org.veekhere.core.domain.enums.ElementType;
import org.veekhere.core.domain.enums.SceneElementParserSourceIndexer;
import org.veekhere.core.domain.interfaces.base.SceneElementVisitor;
import org.veekhere.core.domain.interfaces.base.StringfiableSceneElement;
import org.veekhere.core.domain.utils.ParseUtils;

import java.util.Map;
import java.util.UUID;

public abstract class SceneElement implements StringfiableSceneElement {
    public UUID uuid = UUID.randomUUID();
    protected final ElementType type;
    protected static final Map<SceneElementParserSourceIndexer, Integer> parserKeyMap = ParseUtils.parserKeyMap;

    public SceneElement(ElementType type) {
        this.type = type;
    }
    public SceneElement(ElementType type, UUID uuid) {
        this.type = type;
        this.uuid = uuid;
    }

    public final ElementType getType() {
        return this.type;
    }

    public abstract String stringify(Boolean skipUuids);

    public abstract void accept(SceneElementVisitor sceneElementVisitor);
}
