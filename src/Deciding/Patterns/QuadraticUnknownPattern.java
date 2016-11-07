package Deciding.Patterns;

import Deciding.Elements.FloatElement;
import Deciding.Elements.IElement;
import Deciding.Elements.MonomialElement;
import Deciding.Elements.UnknownElement;
import Deciding.Patterns.Abstraction.ElementPattern;

/**
 * Created by Yuri on 07.11.16.
 */
public class QuadraticUnknownPattern extends ElementPattern {

    protected QuadraticUnknownPattern(IElement element) {
        super(element);
    }

    @Override
    public boolean find() {
        return false;
    }

    @Override
    public boolean match() {
        if (!(element instanceof MonomialElement)) {
            return false;
        }

        MonomialElement target = (MonomialElement) element;
        if (!(target.getElements().size() != 1)) {
            return false;
        }

        UnknownElement ue = target.getElements().get(0);

        if (ue.getExponent().equals(new FloatElement(2))) {
            return true;
        } else {
            return false;
        }

    }
}
