package Utilities;

import Deciding.Equation;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Yuri on 27.10.16.
 */
public class Stepper {

    private Equation equation;
    private ArrayList<String> steps = new ArrayList<>();
    private Stack<String> comments = new Stack<>();

    public Stepper(Equation equation) {
        this.equation = equation;
        step();
    }

    public void step() {
        steps.add(equation.toString());
        System.out.println(equation.toString());
    }

    public void step(String comment) {
        steps.add(equation.toString() + '|' + comment);
        System.out.println(equation.toString());
    }

    public ArrayList<String> getSteps() {
        return steps;
    }
}
