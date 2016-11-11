package Deciding.Deciders;

import Deciding.Elements.Index;
import Deciding.Elements.MonomialElement;
import Deciding.Patterns.ElementPattern;
import Deciding.Patterns.PatternFactory;

import java.util.ArrayList;

/**
 * Created by Yuri on 05.11.16.
 */
public class QuadraticDecider extends Decider {
    private Index a;
    private Index b;
    private Index c;


    @Override
    public ArrayList<String> decide() {
        util.rightToLeft(equation);

        ElementPattern pattern;
        pattern = PatternFactory.createPattern("[0-9]*[w-z]^2");
        MonomialElement me = (MonomialElement) equation.getNext(pattern);

        a = (Index) me.getIndex();
        return null;
    }
}
