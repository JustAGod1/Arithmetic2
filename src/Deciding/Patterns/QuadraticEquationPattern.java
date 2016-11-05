package Deciding.Patterns;

import Deciding.Equation;
import Deciding.Patterns.Abstraction.EquationPattern;

/**
 * Created by Yuri on 05.11.16.
 */
public class QuadraticEquationPattern extends EquationPattern {

    public QuadraticEquationPattern(Equation equation) {
        super(equation);
    }

    @Override
    public boolean find() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }
}
