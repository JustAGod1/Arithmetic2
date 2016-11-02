package Deciding.Elements;

import Deciding.Deciders.DecideUtil;
import Enums.Mark;
import Utilities.CloneMachine;

import java.util.ArrayList;

/**
 * Created by Yuri on 30.10.16.
 */
public class FractionElement implements IElement {
    BracketsElement up;
    BracketsElement down;
    Mark mark;

    public FractionElement(IElement up, IElement down) {


        ArrayList<IElement> uup = new ArrayList<>();
        ArrayList<IElement> ddown = new ArrayList<>();

        uup.add(up);
        ddown.add(down);

        this.up = new BracketsElement(uup, Mark.Plus);
        this.down = new BracketsElement(ddown, Mark.Plus);

        mark = DecideUtil.multiplyMarks(up.getMark(), down.getMark());

        if (up.getMark() == Mark.Minus) up.changeMark();
        if (down.getMark() == Mark.Minus) down.changeMark();
    }

    public BracketsElement getUp() {
        return up;
    }

    public BracketsElement getDown() {
        return down;
    }

    public void setUp(BracketsElement up) {
        this.up = up;
    }

    public void setDown(BracketsElement down) {
        this.down = down;
    }

    public void invert() {
        BracketsElement tmp = up;
        up = down;
        down = tmp;
    }

    @Override
    public void changeMark() {

    }

    @Override
    public IElement multiplyBy(IElement m) {
        up = (BracketsElement) up.multiplyBy(m);
        return (IElement) CloneMachine.cloneObject(this);
    }

    @Override
    public IElement divideBy(IElement divider) {
        return null;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return String.format("%s(%s:%s)", mark, up, down);
    }

    @Override
    public String renderToString(boolean needBrackets, boolean needMark) {
        String res = "";

        if (mark == Mark.Minus) {
            res += '-';
            if (needMark) {
                res += ' ';
            }
        } else if (needMark) {
            res += '+';
            res += ' ';
        }

        res += '(';

        res += up.renderToString(false, false);

        res += " : ";

        res += down.renderToString(false, false);

        res += ')';

        return res;
    }
}
