package Deciding.Elements;

import Deciding.Readers.LinearReader;
import Enums.Mark;
import Enums.MultMark;
import Utilities.CloneMachine;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Yuri on 22.10.16.
 */
public class BracketsElement extends Exponentable implements IElement, Serializable {
    private ArrayList<IElement> elements;
    private Mark mark;

    public BracketsElement(ArrayList<IElement> elements, Mark mark) {

        this.elements = elements;
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }

    @Override
    public String toString() {
        String res = "";

        for (IElement element : elements) {
            res += element.toString() + " ";
        }
        res = res.trim();
        return String.format("%s(%s)", mark.toString(), res);
    }

    @Override
    public void changeMark() {
        if (mark == Mark.Plus) mark = Mark.Minus;
        if (mark == Mark.Minus) mark = Mark.Plus;
    }

    public LinearReader getReader() {
        return new LinearReader(elements);
    }

    @Override
    public IElement multiplyBy(IElement multiplier) {
        BracketsElement res = (BracketsElement) CloneMachine.cloneObject(this);

        LinearReader reader = res.getReader();

        for (int i = 0; i < reader.size(); i++) {
            reader.set(i, reader.get(i).multiplyBy(multiplier));
        }

        return res;
    }



    @Override
    public IElement divideBy(IElement divider) {
        return null;
    }

    @Override
    public String renderToString(boolean needBrackets, boolean needMark) {
        String res = "";

        if (needMark || (mark == Mark.Minus)) {
            res += mark.toString();
        }

        res += '(';

        res += elements.get(0).renderToString(false, false);
        res += ' ';

        for (int i = 1; i < elements.size(); i++) {
            res += elements.get(i).renderToString(false, true);
            res += ' ';
        }

        res = res.trim();

        res += ')';

        return res;
    }

    @Override
    public IElement raiseToExponent() {
        if (!(getExponent() == null))
        {
            if ((getExponent() instanceof FloatElement) || (getExponent() instanceof BracketsElement)) {
                if (getExponent() instanceof FloatElement) {
                    if (((FloatElement) getExponent()).toFloat() >= 2) {
                        BracketsElement be = (BracketsElement) CloneMachine.cloneObject(this);
                        MultElement me = new MultElement(be, MultMark.Mult, be);
                        for (int i = 2; i < ((FloatElement) getExponent()).value; i++) {
                            me = (MultElement) me.multiplyBy(be);
                        }
                        return me;
                    }

                    if (((FloatElement) getExponent()).toFloat() < 1) {
                        if (((FloatElement) getExponent()).toFloat() == 0) {
                            elements.clear();
                            elements.add(new FloatElement(1));
                        } else {
                            BracketsElement be = (BracketsElement) CloneMachine.cloneObject(this);
                            MultElement me = new MultElement(be, MultMark.Mult, be);
                            for (int i = 2; i < ((FloatElement) getExponent()).value; i++) {
                                me = (MultElement) me.multiplyBy(be);
                            }
                            FractionElement fe = new FractionElement(new FloatElement(1), me);
                            return fe;
                        }
                    }
                }
                else {
                    System.out.println(String.format("Я пока не умею возводится в такую степень - \"%s\". Я %s", getExponent(), getClass().toString()));
                }
            }
            return this;
        }
        return this;
    }
}
