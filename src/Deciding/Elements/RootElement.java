package Deciding.Elements;

import Enums.Mark;

/**
 * Created by Yuri on 23.10.16.
 */
public class RootElement implements IElement {
    IElement base;
    IElement index;

    public RootElement(IElement base, IElement index) {
        this.base = base;
        this.index = index;
    }

    @Override
    public String toString() {
        String res = "";

        if (index.equals(new FloatElement(2, Mark.Plus))) {
            res += index;
        }

        res += '√';

        res += index;

        return res;
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
        return null;
    }

    @Override
    public Mark getMark() {
        return null;
    }
}
