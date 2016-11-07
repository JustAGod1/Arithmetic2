package Deciding.Deciders;

import Deciding.Elements.FloatElement;
import Deciding.Elements.MonomialElement;
import Deciding.Equation;
import Deciding.Readers.LinearReader;
import Exceptions.ParsingException;
import Parsing.Parser;
import Utilities.Stepper;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class LinearDecider extends Decider {

    private DecideUtil util;


    public LinearDecider(Equation equation) {
        super(equation);
        util = new DecideUtil(stepper);
    }

    public LinearDecider(Equation equation, Stepper stepper) {
        super(stepper, equation);
    }

    public LinearDecider(@NotNull String equation) throws ParsingException {

        this.equation = (new Parser(equation)).parse();
        this.stepper = new Stepper(this.equation);
        util = new DecideUtil(stepper);
    }

    public ArrayList<String> decide() {
        while (!util.isCompletedEquation(equation)) {
            util.simplify(equation.getLeftSide());
            util.simplify(equation.getRightSide());
            util.sortElements(equation);
        }
        MonomialElement left = (MonomialElement) equation.getLeftSide().get(0);
        if (!(left.getIndex().equals(new FloatElement(1)))) {
            DecideUtil.divideReader(equation.getRightSide(), left.getIndex());
            left.setIndex(new FloatElement(1));
        }
        stepper.step();
        return stepper.getSteps();
    }

    public DecideUtil getUtil() {
        return util;
    }
}
