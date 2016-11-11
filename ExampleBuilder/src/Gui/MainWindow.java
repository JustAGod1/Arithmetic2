package Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Yuri on 10.11.16.
 */
public class MainWindow extends JFrame{
    private JPanel root;
    private JSlider slider1;
    private JButton createExampleButton;
    private JLabel ExampleField;
    private JTextField textField1;
    private JButton проверитьButton;

    public MainWindow() {
        super("Example Factory");

        getContentPane().add(root);
        setVisible(true);

        createExampleButton.addActionListener(e -> ExampleField.setText(ExampleFactory.build(slider1.getValue()).toString()));
    }
}
