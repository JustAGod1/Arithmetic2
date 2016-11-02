package Gui;

import Deciding.Deciders.LinearDecider;
import Exceptions.ParsingException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Created by Yuri on 28.10.16.
 */
public class MainWindow extends JFrame {

    private JTextArea label = new JTextArea(String.format("Введите уравнение" +
            "\n" +
            "Пример: %s", "(x - 0.5) * 2 - (45 * x - 2) + 1 = 9"));
    private JTextField text = new JTextField();
    private JButton button = new JButton("Начать решение");
    private JTextArea textArea = new JTextArea();
    private JPanel panel = new JPanel();
    private Rectangle size = new Rectangle(500, 500);

    public MainWindow() {
        super("Arithmetic 2");


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        textArea.setEditable(false);
        setBounds(size);
        //setResizable(false);
        panel.setLayout(new GridLayout(3, 1));
        button.addActionListener(new buttonListener());
        label.setEditable(false);

        panel.setBorder(BorderFactory.createEtchedBorder());
        getContentPane().add(panel, BorderLayout.PAGE_START);
        //panel.setLayout(new GridBagLayout());
        panel.add(label);
        panel.add(text);
        panel.add(button);
        getContentPane().add(textArea, BorderLayout.CENTER);

    }

    private class buttonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
            try {
                ArrayList<String> strings = (new LinearDecider(text.getText())).decide();
                for (String s : strings) {
                    textArea.append(s + '\n');
                }
            } catch (ParsingException e1) {
                e1.printStackTrace();
                textArea.append("Возникла проблема");
            }
        }
    }
}
