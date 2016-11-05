package Deciding.Elements;

import java.io.Serializable;

/**
 * Created by Yuri on 23.10.16.
 */
public abstract class Exponentable implements Serializable{

    private IElement exponent;

    public void setExponent(IElement exponent) {
        this.exponent = exponent;
    }

    public IElement getExponent() {
        return exponent;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Exponentable)) {
            return false;
        }

        return ((Exponentable) obj).exponent == null ? exponent == null : exponent.equals(((Exponentable) obj).exponent);
    }

    public abstract IElement raiseToExponent();
}
