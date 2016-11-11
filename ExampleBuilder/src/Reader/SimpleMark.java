package Reader;

/**
 * Created by Yuri on 09.11.16.
 */
public enum SimpleMark {Plus, Minus, Mult, Divide;

    @Override
    public String toString() {
        switch (name()) {
            case "Plus": return "+";
            case "Minus": return "-";
            case "Mult": return "*";
            case "Divide": return ":";
            default: return "";
        }
    }
}
