package Deciding.Deciders;

import Deciding.Elements.DecideUtil;
import Deciding.Elements.FloatElement;
import Deciding.Elements.MonomialElement;
import Exceptions.ParsingException;
import Parsing.Parser;
import Utilities.Stepper;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class LinearDecider extends Decider {





    public LinearDecider(@NotNull String equation) throws ParsingException {

        this.equation = (new Parser(equation)).parse();
        this.stepper = new Stepper(this.equation);
        DecideUtil.create(stepper);
        util = DecideUtil.getInstance();
    }

    public ArrayList<String> decide() {
        while ((util.simplify(equation.getRightSide())) | (util.simplify(equation.getLeftSide())) | (util.sortElements(equation))){}
        try {
            MonomialElement left = (MonomialElement) equation.getLeftSide().get(0);
            if (!(left.getIndex().equals(new FloatElement(1)))) {
                DecideUtil.divideReader(equation.getRightSide(), left.getIndex());
                left.setIndex(new FloatElement(1));
            }
            stepper.step();
        } catch (Throwable e) {

        }
        return stepper.getSteps();
    }

    public DecideUtil getUtil() {
        return util;
    }
}
