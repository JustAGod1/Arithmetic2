package Deciding.Elements;

import Enums.Mark;
import Utilities.CloneMachine;

import java.util.ArrayList;

/**
 * Created by Yuri on 27.10.16.
 */
public class MonomialElement implements IElement {

    private Index index = new FloatElement(1, Mark.Plus);
    private ArrayList<UnknownElement> elements = new ArrayList<>();

    public MonomialElement(Index index, UnknownElement element) {
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

    public MonomialElement(Index index, ArrayList<UnknownElement> elements) {

        this.index = index;
        this.elements = elements;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    private void check() {

    }

    public Index getIndex() {
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
        if (m instanceof Index) {
            index = (Index) index.multiplyBy(m);
        }
        else if (m instanceof MonomialElement) {
            index = (Index) ((MonomialElement) m).index.multiplyBy(index);
            int ln = elements.size();
            for (int i = 0; i < ln; i++) {
                UnknownElement ue = elements.get(i);
                for (int j = 0; j < ((MonomialElement) m).elements.size(); j++) {
                    UnknownElement tmp = ((MonomialElement) m).elements.get(j);
                    if ((ue.getExponent() instanceof Index) && (tmp.getExponent() instanceof Index)) {
                        ue.setExponent(DecideUtil.getInstance().summarizeIndexes((Index) tmp.getExponent(), (Index) ue.getExponent()));
                    } else if (!((ue.getExponent() instanceof RootElement) || (tmp.getExponent() instanceof RootElement))) {

                    }
                }
            }
        }

        return this;
    }

    @Override
    public IElement divideBy(IElement d) {
        if (equals(d)) return new FloatElement(1);
        if (!(d instanceof Index)) {
            return (IElement) CloneMachine.cloneObject(new FractionElement(this, d));
        } else {
            index = (Index) index.divideBy(d);
            return this;
        }

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
    public void setMark(Mark mark) {
        index.setMark(mark);
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MonomialElement)) return false;
        MonomialElement target = (MonomialElement) obj;
        return (isSimilarMonomial(target) && index.equals(target.index));
    }
}
