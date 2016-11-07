package Deciding.Deciders;

import Deciding.Equation;
import Utilities.Stepper;

import java.util.ArrayList;

/**
 * Created by Yuri on 05.11.16.
 */
public abstract class Decider {

    protected Stepper stepper;
    protected Equation equation;

    public Decider(Stepper stepper, Equation equation) {
        this.stepper = stepper;
        this.equation = equation;
    }

    public Decider(Equation equation) {
        this.equation = equation;
    }

    protected Decider() {
    }

    public abstract ArrayList<String> decide();
}
