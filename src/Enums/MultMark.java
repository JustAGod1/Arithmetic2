package Enums;

/**
 * Created by Yuri on 22.10.16.
 */
public enum MultMark {Mult, Div;

    public static MultMark parse(char mark) {
        if (mark == '/') return Div;
        if (mark == '*') return Mult;
        return null;
    }

    @Override
    public String toString() {
        if (name().equals("Div")) return "/";
        if (name().equals("Mult")) return "*";
        return "";
    }
}
