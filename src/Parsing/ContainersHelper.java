package Parsing;

import Deciding.Elements.IElement;
import Enums.Mark;
import Enums.MultMark;
import Enums.RootIndex;
import Parsing.Containers.ArrayContainer;
import Parsing.Containers.BracketsContainer;
import Parsing.Containers.ComplicatedContainer;
import Parsing.Containers.Container;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

/**
 * Created by Yuri on 21.10.16.
 */
public class ContainersHelper {
    ComplicatedContainer elements = new ArrayContainer();

    public void addContainer(Container container) {
        elements.addContainer(container);
    }

    public ArrayList<IElement> toArray() {
        return ((ArrayContainer)elements).toArray();
    }

    public void openBrackets(Mark mark) {
        System.out.println("We're opening brackets");
        BracketsContainer bc = new BracketsContainer(elements, mark);
        elements.addContainer(bc);
        elements = bc;
    }

    public void closeBrackets() {
        System.out.println("We're closing brackets");
        elements = elements.getPreviousContainer();
    }

    public void addMulting(MultMark mark) {

        elements.addMult(mark);
    }

    public void addExponent() {
        System.out.println("We're trying to add exponent");
        elements.addExponent();
    }

    public void addRoot(RootIndex index) {
        elements.addRoot(index);
    }


}
