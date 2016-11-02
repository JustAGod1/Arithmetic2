import Deciding.Deciders.LinearDecider;
import Deciding.Elements.FloatElement;
import Deciding.Elements.FractionElement;
import Deciding.Equation;
import Exceptions.ParsingException;

import Gui.MainWindow;
import Parsing.Parser;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Yuri on 22.10.16.
 */
public class Main {
    public static final String EQUATION = "(x - 0.5) * 2 - (45 * x - 2) + 1 = 9^2";
    public static void main(String[] args) throws ParsingException, InterruptedException {
        Scanner sc = new Scanner(System.in);


        System.out.println((new FractionElement(new FloatElement(1), new FloatElement(1))).getClass().toString());

        //--------------------------------------------------------------------------------------------------------------

        //MainWindow mw = new MainWindow();

        //--------------------------------------------------------------------------------------------------------------

        System.out.printf("Введите уравнение" +
                "\n" +
                "Пример: %s" +
                "\n", EQUATION);
        Equation equation = (new Parser(EQUATION)).parse();
        LinearDecider decider = new LinearDecider(equation);
        ArrayList<String> strings = decider.decide();



        //System.out.println(equation);
    }
}
