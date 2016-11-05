package Deciding.Patterns.Abstraction;

import Deciding.Readers.LinearReader;

/**
 * Created by Yuri on 05.11.16.
 */
public abstract class LinearReaderPattern {

    LinearReader lr;

    public LinearReaderPattern(LinearReader lr) {
        this.lr = lr;
    }
}
