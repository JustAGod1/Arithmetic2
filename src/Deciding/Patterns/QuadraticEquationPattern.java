package Deciding.Patterns;

import Deciding.Elements.FloatElement;
import Deciding.Elements.MonomialElement;
import Deciding.Elements.UnknownElement;
import Deciding.Equation;
import Deciding.Patterns.Abstraction.EquationPattern;
import Enums.Mark;

/**
 * Created by Yuri on 05.11.16.
 */
public class QuadraticEquationPattern extends EquationPattern {

    MonomialElement a;
    MonomialElement b = new MonomialElement(new UnknownElement(Mark.Plus, 'x'));


    public QuadraticEquationPattern(Equation equation) {
        super(equation);

        UnknownElement ue = new UnknownElement(Mark.Plus, 'x');
        ue.setExponent(new FloatElement(2));
        a = new MonomialElement(ue);


    }

    @Override
    public boolean find() {
        Equation e = getEquation();
        e.reset();

        return e.hasNext(new QuadraticUnknownPattern(new FloatElement(1)));

    }

    @Override
    public boolean match() {
        int size = 3;

        Equation e = getEquation();


        if (!find()) return false;
        e.reset();
        if (!e.haveNextMonomialElement()) size--;

        if (!e.haveNextFloatElement()) size--;

        return e.size() == size;

    }
}
