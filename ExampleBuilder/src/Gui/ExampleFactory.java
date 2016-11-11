package Gui;

import Gui.MainWindow;
import Reader.SimpleMark;
import Reader.SimpleReader;
import Utilities.CloneMachine;

import java.util.Random;

/**
 * Created by Yuri on 09.11.16.
 */
public final class ExampleFactory {

    public static SimpleReader build(int stepsCount) {
        if (stepsCount == 0) return new SimpleReader(randInt(1, 99));

        return expand(build(stepsCount - 1));
    }

    public static void openGUI() {
        MainWindow mw = new MainWindow();
    }

    private static SimpleReader expand(SimpleReader reader) {
        SimpleReader backup = (SimpleReader) CloneMachine.cloneObject(reader);
        do
        {
            reader = (SimpleReader) CloneMachine.cloneObject(backup);
            try {
                for (int i = 0; i < reader.size(); i += 2) {
                    reader.replace(i, complicateElement(reader.getInteger(i)));
                }
            } catch (Throwable e){
                continue;
            }
        } while (false);
        return reader;
    }

    private static SimpleReader complicateElement(Integer integer) {
        SimpleMark mark = randAction();

        int multier = 0;

        switch (mark) {
            case Plus: {
                multier = randInt(1, integer - 1);
                integer = integer - multier;
                break;
            }
            case Minus: {
                multier = randInt(0, 99 - integer);
                integer = integer + multier;
                break;
            }
            case Mult: {

            }
            case Divide: {
                multier = randInt(1, 7);
                integer = integer * multier;
                break;
            }
        }

        SimpleReader sr = new SimpleReader(integer);
        sr.add(mark, multier);
        return sr;
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     *
     * @return Random action, but it looks like SimpleMark
     */

    private static SimpleMark randAction() {
        switch (randInt(1, 4)) {
            case 1:
                return SimpleMark.Plus;
            case 2:
                return SimpleMark.Minus;
            case 3:
                //return SimpleMark.Mult;
            case 4:
                return SimpleMark.Divide;
            default:
                return null;
        }
    }
}
