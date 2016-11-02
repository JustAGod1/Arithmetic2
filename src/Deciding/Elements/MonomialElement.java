package Deciding.Elements;

import Enums.Mark;
import Utilities.CloneMachine;

import java.util.ArrayList;

/**
 * Created by Yuri on 27.10.16.
 */
public class MonomialElement implements IElement {

    private FloatElement index = new FloatElement(1, Mark.Plus);
    private ArrayList<UnknownElement> elements = new ArrayList<>();

    public MonomialElement(FloatElement index, UnknownElement element) {
        this.index = index;
        elements.add(element);
        check();
    }

    public MonomialElement(UnknownElement element) {
        elements.add(element);
        check();
    }

    public MonomialElement(UnknownElement first, UnknownElement second) {
        elements.add(first);
        elements.add(second);
        check();
    }

    public MonomialElement(FloatElement index, ArrayList<UnknownElement> elements) {

        this.index = index;
        this.elements = elements;
    }

    public void setIndex(FloatElement index) {
        this.index = index;
    }

    private void check() {

    }

    public FloatElement getIndex() {
        return index;
    }

    public ArrayList<UnknownElement> getElements() {
        return elements;
    }

    public boolean isSimilarMonomial(MonomialElement me) {
        return elements.equals(me.elements);
    }

    @Override
    public void changeMark() {
        index.changeMark();
    }

    @Override
    public IElement multiplyBy(IElement m) {
        if (m instanceof FloatElement) {
            index = (FloatElement) index.multiplyBy(m);
        }

        return this;
    }

    @Override
    public IElement divideBy(IElement d) {

        return (IElement) CloneMachine.cloneObject(new FractionElement(this, d));


    }

    @Override
    public String renderToString(boolean needBrackets, boolean needMark) {
        String res = "";

        if (!((index.equals(new FloatElement(1))) || (index.equals(new FloatElement(-1))))) {
            res += index.renderToString(false, needMark);
        } else if (needMark) {
            res += index.getMark();
            res += ' ';
        } else if (index.getMark() == Mark.Minus) {
            res += index.getMark();
        }

        for (int i = 0; i < elements.size(); i++) {
            res += elements.get(i).renderToString(true, false);
        }

        if (needBrackets && (index.getMark() == Mark.Minus)) {
            res = String.format("-(%s)", res);
        }

        return res;
    }

    @Override
    public Mark getMark() {
        return index.getMark();
    }

    @Override
    public String toString() {
        String res = "|";
        res += index;
        for (UnknownElement element : elements) {
            res += element;
        }
        res += "|";
        return res;
    }
}
