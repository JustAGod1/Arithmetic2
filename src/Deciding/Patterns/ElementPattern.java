package Deciding.Patterns;

import Deciding.Elements.IElement;
import Deciding.Patterns.Matcher;

import java.util.regex.Pattern;

/**
 * Created by Yuri on 07.11.16.
 */
public class ElementPattern {


    private Pattern pattern;

    public ElementPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(IElement element) {
        return new Matcher(element, pattern);
    }
}
