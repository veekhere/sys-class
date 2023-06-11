package org.veekhere.core.domain.interfaces.services;

import org.veekhere.core.domain.models.base.SceneElement;

import java.util.ArrayList;

public interface SceneElementParser {
    SceneElement parse(ArrayList<String> source);
}
