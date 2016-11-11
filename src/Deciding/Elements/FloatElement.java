package Deciding.Elements;


import Enums.Mark;
import Utilities.CloneMachine;

import java.io.Serializable;

/**
 * Created by Yuri on 22.10.16.
 */
public class FloatElement extends Exponentable implements Index, Serializable {

    public float value;
    private Mark mark;

    public FloatElement(float value, Mark mark) {

        this.value = value;
        this.mark = mark;
    }

    public FloatElement(float value) {
        if (value < 0) {
            mark = Mark.Minus;
            this.value = (value * -1);

        } else {
            mark = Mark.Plus;
            this.value = value;
        }

    }

    public Mark getMark() {
        return mark;
    }

    @Override
    public void setMark(Mark mark) {

        this.mark = mark;
    }

    public float toFloat() {
        float res = value;
        if (mark == Mark.Minus) res *= -1;

        return res;
    }

    @Override
    public String toString() {
        String res = String.valueOf(toFloat());

        if (toFloat() % 1.0 == 0) res = String.valueOf((int) toFloat());
        if (toFloat() > 0) res = '+' + res;
        if (!(getExponent() == null)) res += '^' + getExponent().toString();
        return res;
    }

    @Override
    public void changeMark() {
        if (mark == Mark.Plus)
            mark = Mark.Minus;
        else
            mark = Mark.Plus;
    }

    @Override
    public IElement multiplyBy(IElement m) {
        if (m instanceof FloatElement) {
            FloatElement res = new FloatElement(toFloat() * ((FloatElement) m).toFloat());
            return res;
        }

        if (m instanceof BracketsElement) {
            return (IElement) CloneMachine.cloneObject(m.multiplyBy(this));
        }

        if (m instanceof MonomialElement) {
            return (IElement) CloneMachine.cloneObject(m.multiplyBy(this));
        }
        return m;
    }

    @Override
    public IElement divideBy(IElement d) {
        if (d instanceof FloatElement) {
            if (DecideUtil.isNiceNumber(toFloat() / ((FloatElement) d).toFloat())) {
                return new FloatElement(toFloat() / ((FloatElement) d).toFloat());
            } else {
                return (IElement) CloneMachine.cloneObject(new FractionElement(this, d));
            }
        }
        if (d instanceof MonomialElement) {
            return new FractionElement(this, d);
        }
        if ("class Deciding.Elements.FractionElement".equals(d.getClass().toString())) {
            ((FractionElement) d).invert();
            return (IElement) CloneMachine.cloneObject(d.multiplyBy(this));
        }

        return null;
    }

    @Override
    public String renderToString(boolean needBrackets, boolean needMark) {
        String res = "";
        if (needMark) {
            res += mark.toString();
            res += ' ';
        }
        else if (mark == Mark.Minus) {
            res += mark.toString();
        }

        if (value % 1.0 == 0) {
            res += (int) value;
        } else {
            res += value;
        }

        if (needBrackets && (mark == Mark.Minus)) {
            res = String.format("(%s)", res);
        }

        if (!(getExponent()  == null)) {
            res += '^' + getExponent().renderToString(true, false);
        }

        return res;
    }

    @Override
    public boolean equals(Object obj) {
        FloatElement fe = null;
        try {

            fe = (FloatElement) obj;
        } catch (ClassCastException e) {
            return false;
        }

        return (fe.value == value) && (fe.mark == mark);
    }

    @Override
    public IElement raiseToExponent() {
        if (getExponent() != null) {
            if (!(getExponent() instanceof FloatElement)) {
                System.out.println(String.format("Я пока не умею решать такие степени \"%s\". Я - %s", getExponent(), getClass().toString()));
                setExponent(null);
                return (IElement) CloneMachine.cloneObject(this);
            } else {
                setExponent(((FloatElement) getExponent()).raiseToExponent());
                this.value = (float) Math.pow(this.value, ((FloatElement) getExponent()).value);
                setExponent(null);
            }
        }
        return (IElement) CloneMachine.cloneObject(this);
    }
}
