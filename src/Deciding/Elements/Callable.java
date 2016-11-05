package Deciding.Elements;

import Utilities.Stepper;

import java.util.ArrayList;

/**
 * Created by Yuri on 26.10.16.
 */
public interface Callable {

    ArrayList<IElement> call(Stepper stepper);
}
