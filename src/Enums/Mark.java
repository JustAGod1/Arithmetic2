package Enums;

/**
 * Created by Yuri on 22.10.16.
 */
public enum  Mark {Plus, Minus;

    public static Mark parse(char mark) {
        if (mark == '-') return Minus;
        if (mark == '+') return Plus;
        return null;
    }

    @Override
    public String toString() {
        if (name().equals("Minus")) return "-";
        if (name().equals("Plus")) return "+";
        return null;
    }
}
