package Enums;

/**
 * Created by Yuri on 22.10.16.
 */
public enum MultMark {Mult, Div;

    public static MultMark parse(char mark) {
        switch (mark) {
            case ':':
            case '/':
            case '\\':
                return Div;
            case '*':
                return Mult;
        }
        return null;
    }

    @Override
    public String toString() {
        if (name().equals("Div")) return "/";
        if (name().equals("Mult")) return "*";
        return "";
    }
}
