package Deciding.Elements;

import Deciding.Equation;
import Deciding.Patterns.QuadraticEquationPattern;
import Deciding.Readers.LinearReader;
import Enums.Mark;
import Utilities.Stepper;

import java.util.ArrayList;

/**
 * Created by Yuri on 26.10.16.
 */
public class DecideUtil {


    public Stepper stepper;
    private static DecideUtil instance;

    private DecideUtil(Stepper stepper) {
        this.stepper = stepper;
    }

    public static void create(Stepper stepper) {
        instance = new DecideUtil(stepper);
    }

    public static DecideUtil getInstance() {
        return instance;
    }

    public static IElement multElements(IElement first, IElement second) {

        return null;
    }

    public void rightToLeft(Equation equation) {
        LinearReader right = equation.getRightSide();
        LinearReader left = equation.getLeftSide();
        while (right.hasNext()) {
            IElement element = right.getNext();
            element.changeMark();
            right.remove(element);
            left.add(element);
        }
    }

    /**
     *
     * @param lr Linear reader, that contains elements that you need to multiply
     * @param multiplier
     */

    public static void multReader(LinearReader lr, IElement multiplier) {
        lr.reset();
        while (lr.hasNext()) {
            IElement element = lr.getNext().multiplyBy(multiplier);
            lr.set(lr.getIndex(), element);
        }
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
        return ((e.getLeftSide().size() == 1) && (e.getLeftSide().get(0) instanceof MonomialElement) && (e.getRightSide().size() == 1) && (e.getRightSide().get(0) instanceof FloatElement)) || (new QuadraticEquationPattern(e)).match();
    }

    public boolean simplify(LinearReader e) {
        e.reset();
        boolean res = false;
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
            res = true;
        }
        while (e.hasNextMultiplying()) {
            MultElement element = e.getNextMultElement();
            e.set(e.getIndex(), element.call(stepper).get(0));
            res = true;
        }
        while (e.hasNextBrackets()) {
            BracketsElement be = e.getNextBrackets();
            LinearReader lr = openBrackets(be);
            e.addAll(e.indexOf(be), lr);
            e.remove(e.getIndex() + lr.size());
            stepper.step();
            res = true;
        }



        while (findAndSummarizeTwoElements(e)){}
        return res;
    }

    public boolean sortElements(Equation e) {
        LinearReader l = e.getLeftSide();
        l.reset();
        LinearReader r = e.getRightSide();
        r.reset();
        boolean res = false;

        while(r.hasNextMonomial()) {
            MonomialElement me = r.getNextMonomial();
            me.changeMark();
            r.remove(r.getIndex());
            l.add(me);
            stepper.step();
            res = true;
        }

        while (l.hasNextIndex()) {
            Index fe = l.getNextIndex();
            fe.changeMark();
            l.remove(l.getIndex());
            r.add(fe);
            stepper.step();
            res = true;
        }
        return res;
    }

    private boolean findAndSummarizeTwoElements(LinearReader e) {
        e.reset();
        boolean result = false;
        while (e.hasNextFloat()) {
            Index fe1 = e.getNextIndex();
            if (!e.hasNextIndex()) break;
            Index fe2 = e.getNextIndex();

            Index res = summarizeIndexes(fe1, fe2);
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

            MonomialElement res = new MonomialElement(summarizeIndexes(me1.getIndex(), me2.getIndex()), me1.getElements());
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

    public Index summarizeIndexes(Index first, Index second) {
        if ((first instanceof FloatElement) && (second instanceof FloatElement)) return new FloatElement(((FloatElement) first).toFloat() + ((FloatElement) second).toFloat());
        FractionElement f;
        FractionElement s;
        if (first instanceof  FloatElement) {
            f = new FractionElement(first, new FloatElement(1));
        } else {
            f = (FractionElement) first;
        }

        if (second instanceof FloatElement) {
            s = new FractionElement(second, new FloatElement(1));
        } else {
            s = (FractionElement) second;
        }

        return summarizeFractions(f, s);

    }

    private FractionElement summarizeFractions(FractionElement first, FractionElement second) {
        /*simplify(first.getDown().getReader());
        simplify(second.getDown().getReader());

        //FractionElement first = (FractionElement) CloneMachine.cloneObject(first);
        first.setDown((BracketsElement) first.getDown().multiplyBy(second.getDown()));
        stepper.step();
        first.setUp((BracketsElement) first.getUp().multiplyBy(second.getDown()));
        BracketsElement be = first.getUp();
        be.setMark(Mark.Plus);
        LinearReader reader = be.getReader();
        reader.add(be.multiplyBy(first.getDown()));*/

        // FIXME: 09.11.16 Раскоментить

        return first;
    }


}
