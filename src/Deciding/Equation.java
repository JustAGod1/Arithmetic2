package Deciding;

import Deciding.Elements.FloatElement;
import Deciding.Elements.IElement;
import Deciding.Elements.MonomialElement;
import Deciding.Patterns.ElementPattern;
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

    public int size() {
        return getRightSide().size() + getLeftSide().size();
    }

    public boolean hasNext(ElementPattern pattern) {
        return getLeftSide().hasNext(pattern) || getRightSide().hasNext(pattern);
    }

    public boolean haveNextMonomialElement() {
        return getLeftSide().hasNextMonomial() || getRightSide().hasNextMonomial();
    }

    public boolean haveNextFloatElement() {

        return getLeftSide().hasNextFloat() || getRightSide().hasNextFloat();
    }

    public IElement getNext(ElementPattern pattern) {
        if (leftSide.hasNext(pattern)) {
            return leftSide.getNext(pattern);
        }
        if (rightSide.hasNext(pattern)) {
            return rightSide.getNext(pattern);
        }

        return null;
    }

    public MonomialElement getNextMonomialElement() {
        if (leftSide.hasNextMonomial()) {
            return leftSide.getNextMonomial();
        }
        if (rightSide.hasNextMonomial()) {
            return rightSide.getNextMonomial();
        }

        return null;
    }

    public FloatElement getNextFloatElement() {
        if (leftSide.hasNextFloat()) {
            return leftSide.getNextFloatElement();
        }
        if (rightSide.hasNextFloat()) {
            return rightSide.getNextFloatElement();
        }

        return null;
    }


    public void reset() {
        getLeftSide().reset();
        getRightSide().reset();
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
