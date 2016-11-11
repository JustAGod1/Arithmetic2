package Deciding.Patterns;


import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Yuri on 07.11.16.
 */
public class PatternFactory {

    /**
     * Compiles the given regular expression into a pattern.
     *
     * @param  regex
     *         The expression to be compiled
     * @return the given regular expression compiled into a element pattern
     * @throws PatternSyntaxException
     *          If the expression's syntax is invalid
     */

    public static ElementPattern createPattern(String regex) throws PatternSyntaxException {
        Pattern p = Pattern.compile(regex);

        return new ElementPattern(p);
    }
}
