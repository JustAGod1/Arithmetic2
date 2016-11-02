package Deciding.Elements;

import Enums.Mark;
import Enums.MultMark;
import Utilities.CloneMachine;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Yuri on 23.10.16.
 */
public class MultElement implements IElement, Serializable, Callable {

    private ArrayList<IElement> mults = new ArrayList<>();
    private ArrayList<MultMark> marks = new ArrayList<>();

    public MultElement(ArrayList<IElement> mults, ArrayList<MultMark> marks) {

        this.mults = mults;
        this.marks = marks;
    }

    public MultElement(IElement first, MultMark mark, IElement second) {
        mults.add(first);
        marks.add(mark);
        mults.add(second);
    }

    @Override
    public String toString() {
        String res = "";

        res += mults.get(0);

        for (int i = 0; i < marks.size(); i++) {
            res += " ";
            res += marks.get(i);
            res += " ";
            res += mults.get(i + 1);
        }

        res = res.trim();
        return res;
    }

    @Override
    public void changeMark() {

    }

    @Override
    public IElement multiplyBy(IElement multiplier) {
        mults.add(multiplier);
        marks.add(MultMark.Mult);
        return this;
    }

    @Override
    public IElement divideBy(IElement divider) {
        return null;
    }

    @Override
    public String renderToString(boolean needBrackets, boolean needMark) {
        String res = "";

        res += mults.get(0).renderToString(false, needMark);
        res += ' ';

        for (int i = 0; i < marks.size(); i++) {
            res += marks.get(i).toString();
            res += ' ';
            res += mults.get(i + 1).renderToString(true, false);
            res += ' ';
        }
        res = res.trim();

        return res;
    }

    @Override
    public Mark getMark() {
        return null;
    }

    @Override
    public ArrayList<IElement> call() {
        IElement element = mults.get(0);

        for (int i = 1; i < mults.size(); i++) {
            if (marks.get(i - 1) == MultMark.Mult) {
                element = (IElement) CloneMachine.cloneObject( element.multiplyBy(mults.get(i)));
            }
            if (marks.get(i - 1) == MultMark.Div) {
                element = (IElement) CloneMachine.cloneObject( element.divideBy(mults.get(i)));
            }
        }

        ArrayList<IElement> res = new ArrayList<>();
        res.add(element);
        return res;
    }
}
