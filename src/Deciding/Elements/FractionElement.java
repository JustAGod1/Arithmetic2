package Deciding.Elements;

import Deciding.Readers.LinearReader;
import Enums.Mark;
import Utilities.CloneMachine;

/**
 * Created by Yuri on 30.10.16.
 */
public class FractionElement implements Index {
    LinearReader up;
    LinearReader down;
    Mark mark;

    public FractionElement(IElement up, IElement down) {
        mark = DecideUtil.multiplyMarks(up.getMark(), down.getMark());

        this.up = new LinearReader(up);
        this.down = new LinearReader(down);

        if (up.getMark() == Mark.Minus) up.changeMark();
        if (down.getMark() == Mark.Minus) down.changeMark();
    }

    public LinearReader getUp() {
        return up;
    }

    public LinearReader getDown() {
        return down;
    }

    public void setUp(LinearReader up) {
        this.up = up;
    }

    public void setDown(LinearReader down) {
        this.down = down;
    }

    public void invert() {
        LinearReader tmp = up;
        up = down;
        down = tmp;
    }


    @Override
    public void changeMark() {

    }

    @Override
    public IElement multiplyBy(IElement m) {
        if (!(m instanceof FractionElement)) {
            DecideUtil.multiplyReader(up, m);
        } else {
            DecideUtil.multiplyReader(up, new BracketsElement(((FractionElement) m).getUp().getElements(), Mark.Plus));
            DecideUtil.multiplyReader(down, new BracketsElement(((FractionElement) m).getDown().getElements(), Mark.Plus));
        }
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
    public void setMark(Mark mark) {

        this.mark = mark;
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

        //res += up.renderToString(false, false);

        res += " : ";

        //res += down.renderToString(false, false);

        // FIXME: 09.11.16 Раскоментить

        res += ')';

        int k =0;
        for (;k < 0; k++){}

        return res;


    }
}
