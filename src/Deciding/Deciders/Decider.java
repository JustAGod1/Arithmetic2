package Deciding.Deciders;

import Deciding.Elements.DecideUtil;
import Deciding.Equation;
import Utilities.Stepper;

import java.util.ArrayList;

/**
 * Created by Yuri on 05.11.16.
 */
public abstract class Decider {

    protected Stepper stepper;
    protected Equation equation;
    protected DecideUtil util;

    public Decider(Stepper stepper, Equation equation, DecideUtil util) {
        this.stepper = stepper;
        this.equation = equation;
        this.util = util;
    }

    public Decider(Stepper stepper, Equation equation) {
        this.stepper = stepper;
        this.equation = equation;
        DecideUtil.create(stepper);
        this.util = DecideUtil.getInstance();
    }

    public Decider(Equation equation) {
        this.stepper = new Stepper(equation);
        this.equation = equation;
        DecideUtil.create(stepper);
        this.util = DecideUtil.getInstance();
    }

    protected Decider() {
    }

    public abstract ArrayList<String> decide();
}
