import Deciding.Deciders.LinearDecider;
import Deciding.Elements.FloatElement;
import Deciding.Elements.FractionElement;
import Exceptions.ParsingException;

import Gui.Test;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Yuri on 22.10.16.
 */


public class Main {
    public static final String EQUATION = "(5 - 7) / (34 - 5) + x = 90 - 0111";

    static int a =0,b;

    public static void main(String[] args) throws ParsingException, InterruptedException {
        Scanner sc = new Scanner(System.in);


        System.out.println((new FractionElement(new FloatElement(1), new FloatElement(1))).getClass().getName());

        //--------------------------------------------------------------------------------------------------------------
        //Test test = new Test();

        //--------------------------------------------------------------------------------------------------------------

        System.out.printf("Введите уравнение" +
                "\n" +
                "Пример: %s" +
                "\n", EQUATION);

        LinearDecider decider = new LinearDecider(EQUATION);
        ArrayList<String> strings = decider.decide();


        int n = sc.nextInt();

        sc.nextLine();
        String s = sc.nextLine();

        String[] ints = s.split(" ");

        String x = sc.nextLine();

        for (int i = 0; i < ints.length; i++) {
            if (ints[i].equals(x)) System.out.printf("%d ", i + 1);
        }

    }
}
