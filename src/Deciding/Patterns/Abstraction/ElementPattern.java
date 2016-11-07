package Deciding.Patterns.Abstraction;

import Deciding.Elements.IElement;

/**
 * Created by Yuri on 07.11.16.
 */
public abstract class ElementPattern implements Pattern {

    protected IElement element;

    protected ElementPattern(IElement element) {
        this.element = element;
    }

    public abstract boolean find();

    public abstract boolean match();
}
