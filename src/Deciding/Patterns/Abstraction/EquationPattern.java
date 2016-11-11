package Deciding.Patterns.Abstraction;

import Deciding.Equation;


/**
 * Created by Yuri on 05.11.16.
 */
public abstract class EquationPattern {

    private Equation equation;

    public EquationPattern(Equation equation) {
        this.equation = equation;
    }

    public Equation getEquation() {
        return equation;
    }


    public abstract boolean find();

    public abstract boolean match();
}
