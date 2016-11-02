package Enums;

/**
 * Created by Yuri on 21.10.16.
 */
public enum  EquationMark {More_Or_Equals, Less_Or_Equals, More, Less, Not_Equals, Equals;

    public static EquationMark parse(char mark) {
        if (mark == '=') return Equals;
        if (mark == '<') return Less;
        if (mark == '>') return More;
        if (mark == '≤') return Less_Or_Equals;
        if (mark == '≥') return More_Or_Equals;
        if (mark == '≠') return Not_Equals;
        return null;
    }

    @Override
    public String toString() {
        switch (name()) {
            case "Equals": return "=";
            case "Less": return "<";
            case "More": return ">";
            case "Less_Or_Equals": return "≤";
            case "More_Or_Equals": return "≥";
            case "Not_Equals": return "≠";
            default: return "";
        }
    }
}
