package Deciding.Patterns;

import Deciding.Elements.IElement;

import java.util.regex.Pattern;

/**
 * Created by Yuri on 07.11.16.
 */
public class Matcher {

    IElement target;
    Pattern pattern;

    public Matcher(IElement target, Pattern pattern) {
        this.target = target;
        this.pattern = pattern;
    }

    public boolean match() {
        java.util.regex.Matcher matcher = pattern.matcher(target.toString());
        return matcher.matches();
    }
}
