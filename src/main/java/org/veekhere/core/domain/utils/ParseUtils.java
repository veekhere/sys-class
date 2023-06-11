package org.veekhere.core.domain.utils;

import org.veekhere.core.domain.enums.SceneElementParserSourceIndexer;

import java.util.HashMap;
import java.util.Map;

public class ParseUtils {
    public static final Map<SceneElementParserSourceIndexer, Integer> parserKeyMap = new HashMap<>() {{
        put(SceneElementParserSourceIndexer.UUID, 0);
        put(SceneElementParserSourceIndexer.ELEMENT_TYPE, 1);
        put(SceneElementParserSourceIndexer.POLYLINE_SIZE, 2);
        put(SceneElementParserSourceIndexer.POLYLINE_DESCRIPTION_START, 3);
        put(SceneElementParserSourceIndexer.XA_COORDINATE, 2);
        put(SceneElementParserSourceIndexer.YA_COORDINATE, 3);
        put(SceneElementParserSourceIndexer.XB_COORDINATE, 4);
        put(SceneElementParserSourceIndexer.YB_COORDINATE, 5);
        put(SceneElementParserSourceIndexer.XC_COORDINATE, 6);
        put(SceneElementParserSourceIndexer.YC_COORDINATE, 7);
    }};
    public static double parseDouble(String str) {
        return Double.parseDouble(str.replace(",","."));
    }
}
