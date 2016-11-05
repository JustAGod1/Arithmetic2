package Deciding.Deciders;

import Deciding.Elements.*;
import Deciding.Equation;
import Deciding.Readers.LinearReader;
import Enums.Mark;
import Utilities.Stepper;

import java.util.ArrayList;

/**
 * Created by Yuri on 26.10.16.
 */
public class DecideUtil {


    public Stepper stepper;

    public DecideUtil(Stepper stepper) {
        this.stepper = stepper;
    }

    public static IElement multElements(IElement first, IElement second) {

        return null;
    }

    public static Mark multiplyMarks(Mark first, Mark second) {
        if ((first == Mark.Minus) && (second == Mark.Minus)) {
            return Mark.Plus;
        } else if ((first == Mark.Minus) && (second == Mark.Plus)) {
            return Mark.Minus;
        } else if ((first == Mark.Plus) && (second == Mark.Minus)) {
            return Mark.Minus;
        } else {
            return Mark.Plus;
        }
    }

    public static void multiplyReader(LinearReader lr, IElement multiplier) {
        while (lr.hasNext()) {
            IElement tmp = lr.getNext().multiplyBy(multiplier);
            lr.set(lr.getIndex(), tmp);
        }
    }

    public static void divideReader(LinearReader lr, IElement divider) {
        while (lr.hasNext()) {
            IElement tmp = lr.getNext().divideBy(divider);
            lr.set(lr.getIndex(), tmp);
        }
    }

    public static boolean isNiceNumber(float number) {
        String tmp = String.valueOf(number % 1.0);
        boolean res = tmp.length() <= 4;
        return res;
    }

    public boolean isCompletedEquation(Equation e) {
        return (e.getLeftSide().size() == 1) && (e.getLeftSide().get(0) instanceof MonomialElement) && (e.getRightSide().size() == 1) && (e.getRightSide().get(0) instanceof FloatElement);
    }

    public void simplify(LinearReader e) {
        e.reset();
        while (e.hasNextExponentable()) {
            Exponentable ee = e.getNextExponentable();
            if (ee.getExponent() instanceof BracketsElement) {
                LinearReader lr = openBrackets((BracketsElement) ee.getExponent());
                if (lr.size() != 1) {
                    BracketsElement be = new BracketsElement(lr.getElements(), Mark.Plus);
                    ee.setExponent(be);
                } else {
                    ee.setExponent(lr.get(0));
                }
            }
            e.set(e.getIndex(), ee.raiseToExponent());
            stepper.step();
        }
        while (e.hasNextMultiplying()) {
            MultElement element = e.getNextMultElement();
            e.set(e.getIndex(), element.call(stepper).get(0));
            stepper.step();
        }
        while (e.hasNextBrackets()) {
            BracketsElement be = e.getNextBrackets();
            LinearReader lr = openBrackets(be);
            e.addAll(e.indexOf(be), lr);
            e.remove(e.getIndex() + lr.size());
            stepper.step();
        }



        while (findAndSummarizeTwoElements(e)){}
    }

    public void sortElements(Equation e) {
        LinearReader l = e.getLeftSide();
        l.reset();
        LinearReader r = e.getRightSide();
        r.reset();

        while(r.hasNextMonomial()) {
            MonomialElement me = r.getNextMonomial();
            me.changeMark();
            r.remove(r.getIndex());
            l.add(me);
            stepper.step();
        }

        while (l.hasNextFloat()) {
            FloatElement fe = l.getNextFloatElement();
            fe.changeMark();
            l.remove(l.getIndex());
            r.add(fe);
            stepper.step();

        }
    }

    private boolean findAndSummarizeTwoElements(LinearReader e) {
        e.reset();
        boolean result = false;
        while (e.hasNextFloat()) {
            FloatElement fe1 = e.getNextFloatElement();
            if (!e.hasNextFloat()) break;
            FloatElement fe2 = e.getNextFloatElement();

            FloatElement res = summarizeFloats(fe1, fe2);
            e.remove(fe1);
            e.remove(fe2);
            e.add(res);
            result |= true;
            stepper.step();
        }

        while (e.hasNextMonomial()) {


            MonomialElement me1 = e.getNextMonomial();
            if (!e.hasNextSimiliarMonomial(me1))
                continue;
            MonomialElement me2 = e.getNextSimiliarMonomial(me1);

            MonomialElement res = new MonomialElement(summarizeFloats(me1.getIndex(), me2.getIndex()), me1.getElements());
            e.remove(me1);
            e.remove(me2);
            e.add(res);
            result |= true;
            stepper.step();

        }

        return result;
    }

    private LinearReader openBrackets(BracketsElement be) {
        LinearReader reader = be.getReader();
        simplify(reader);
        while (reader.hasNextBrackets()) {
            BracketsElement element = reader.getNextBrackets();

            simplify(element.getReader());
            reader.reset();
        }
        ArrayList<IElement> result = new ArrayList<>();

        if (be.getMark() == Mark.Minus) {
            be = (BracketsElement) be.multiplyBy(new FloatElement(-1));
            reader = be.getReader();
        }

        for (IElement element : reader) {
            result.add(element);
        }

        reader.reset();

        return new LinearReader(result);

    }

    private FloatElement summarizeFloats(FloatElement first, FloatElement second) {
        return new FloatElement(first.toFloat() + second.toFloat());
    }


}
