package Deciding.Patterns.Abstraction;

import Deciding.Equation;
import Deciding.Patterns.Abstraction.Pattern;

/**
 * Created by Yuri on 05.11.16.
 */
public abstract class EquationPattern implements Pattern {

    Equation equation;

    public EquationPattern(Equation equation) {
        this.equation = equation;
    }
}
