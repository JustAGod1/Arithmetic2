package Gui;

import Deciding.Deciders.LinearDecider;
import Deciding.Readers.LinearReader;
import Exceptions.ParsingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.IntPredicate;

/**
 * Created by Yuri on 07.11.16.
 */
public class Test extends JFrame{

    private JPanel root;
    private JButton changeExampleButton;
    private JTextField textField1;
    private JButton startDecisionButton;
    private JLabel exampleLabel;
    private JLabel result;
    private JLabel upLabel;


    private ArrayList<String> examples = new ArrayList<>();
    private Dimension size = new Dimension(510, 300);

    public Test() {
        super("Test");
        getContentPane().add(root);
        upLabel.setText("<html><center><strong>34x<sup>2</sup></strong></center></html>");
        setSize(size);
        this.setMinimumSize(size);
        setVisible(true);




        changeExampleButton.addActionListener(e -> setRandExample());
        startDecisionButton.addActionListener(e -> {
            LinearDecider ld = null;
            try {
                ld = new LinearDecider(textField1.getText());
            } catch (ParsingException e1) {
                e1.printStackTrace();
            }

            ArrayList<String> strings = ld.decide();
            result.setText("<html>");

            for (String s : strings) {
                result.setText(result.getText() + s + "<br>");
            }
            result.setText(result.getText() + "</html>");
        });


        fillExamples();
        setRandExample();
    }

    private void setRandExample() {
        exampleLabel.setText(String.format("%s", examples.get(randInt(0, examples.size() - 1))));
    }

    private void fillExamples() {
        examples.add("<html>(5 - 7<sup>(2 - 78)</sup>) * (34 - 5) + x = 90 - 11</html>");
        examples.add("54 - 8 * (99 + 6x) = 90 - 76 * x^2");
        examples.add("24 - 7 * x * (23) - 78 = 90 - 86");
    }

    private static int randInt(int min, int max) {


        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
