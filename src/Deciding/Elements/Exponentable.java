package Deciding.Elements;

/**
 * Created by Yuri on 23.10.16.
 */
public abstract class Exponentable {

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

    public abstract void raiseToExponent();
}
