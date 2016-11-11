package Deciding.Elements;

import Enums.Mark;

import java.io.Serializable;

/**
 * Created by Yuri on 21.10.16.
 */
public interface IElement extends Serializable {

    void changeMark();

    IElement multiplyBy(IElement multiplier);

    IElement divideBy(IElement divider);

    String renderToString(boolean needBrackets, boolean needMark);

    Mark getMark();

    void setMark(Mark mark);
}
