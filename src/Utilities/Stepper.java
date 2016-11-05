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
        add(equation.toString());
        System.out.println(equation.toString());
    }

    public void step(String comment) {
        add(equation.toString() + '|' + comment);
        System.out.println(equation.toString());
    }

    public void step(Equation equation) {
        steps.add(equation.toString());
        System.out.println(equation.toString());
    }

    private void add(String s) {
        if ((steps.size() == 0) || !(steps.get(steps.size() - 1).equals(s))) {
            steps.add(s);
        }
    }

    public void newLine() {
        steps.add("");
        System.out.println();
    }

    public ArrayList<String> getSteps() {
        return steps;
    }
}
