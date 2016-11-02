package Deciding;

import Deciding.Elements.IElement;
import Deciding.Readers.LinearReader;
import Enums.EquationMark;

import java.util.ArrayList;

/**
 * Created by Yuri on 21.10.16.
 */
public class Equation {

    LinearReader leftSide;
    EquationMark mark;
    LinearReader rightSide;

    public Equation(ArrayList<IElement> leftSide, EquationMark mark, ArrayList<IElement> rightSide) {

        this.leftSide = new LinearReader(leftSide);
        this.mark = mark;
        this.rightSide = new LinearReader(rightSide);
    }

    public LinearReader getLeftSide() {
        return leftSide;
    }

    public EquationMark getMark() {
        return mark;
    }

    public LinearReader getRightSide() {
        return rightSide;
    }

    @Override
    public String toString() {
        String leftSide = this.leftSide.get(0).renderToString(false, false) + " ";
        String rightSide = this.rightSide.get(0).renderToString(false, false) + " ";

        for (int i = 1; i < this.leftSide.size(); i++) {
            leftSide += this.leftSide.get(i).renderToString(false, true) + ' ';
        }

        for (int i = 1; i < this.rightSide.size(); i++) {
            rightSide += this.rightSide.get(i).renderToString(false, true) + ' ';
        }
        return leftSide + mark.toString() + ' ' + rightSide;
    }
}
