package Deciding.Elements;

import Enums.Mark;

/**
 * Created by Yuri on 23.10.16.
 */
public class UnknownElement extends Exponentable implements IElement {

    private char letter;
    private Mark mark;

    public UnknownElement(Mark mark, char letter) {

        this.mark = mark;
        this.letter = letter;
    }

    @Override
    public String toString() {
        String res = "";
        res += mark.toString();
        res += letter;
        if (!(getExponent() == null)) res += '^' + getExponent().toString();
        return res;
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public void changeMark() {

    }

    @Override
    public IElement multiplyBy(IElement multiplier) {
        return null;
    }

    @Override
    public IElement divideBy(IElement divider) {
        return null;
    }

    @Override
    public String renderToString(boolean needBrackets, boolean needMark) {
        String res = "";
        if (needMark) {
            res += mark.toString();
        }
        else if (mark == Mark.Minus) {
            res += mark.toString();
        }

        res += letter;

        if (needBrackets && (mark == Mark.Minus)) {
            res += String.format("(%s)", res);
        }

        return res;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnknownElement)) {
            return false;
        }

        UnknownElement ue = (UnknownElement) obj;

        boolean res = ue.letter == letter;

        res &= super.equals(obj);

        res &= ue.mark == mark;

        return res;
    }

    @Override
    public void raiseToExponent() {

    }
}
