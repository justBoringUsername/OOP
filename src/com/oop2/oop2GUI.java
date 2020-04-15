package com.oop2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class oop2GUI extends JFrame {
    private JPanel oop2;
    private JTextField textField1;
    private JButton createButton;
    private JLabel label1;
    private JComboBox comboBox1;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField textField2;
    private JTextArea textArea1;
    private JTextField textField3;
    private JButton serializeButton;
    private JButton deserializeButton;
    private JTextField textField4;
    private JTextArea createdInstancesTextArea;
    private String path, idInfo;
    public static int globalId;

    public oop2GUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(oop2);
        this.pack();

        textField1.setToolTipText("Write here a type of object you wish to create");
        textField2.setToolTipText("Write here a value");
        textField3.setToolTipText("Write here a field name");
        textField4.setToolTipText("Write here De/Serialization type");

        globalId = 0;

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                path = textField1.getText();

                try {
                    Class carClass = Class.forName("com.oop2." + path);
                    Object carObject = carClass.newInstance();

                    comboBox1.addItem(carObject);
                    idInfo = idInfo + carClass.getName() + "|globalId: " +
                            Integer.toString(globalId);
                    createdInstancesTextArea.setText(idInfo);
                    globalId++;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    label1.setText("No such class!");
                }

            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String output = null;
                Object carObject = (Object) comboBox1.getSelectedItem();
                Class carClass = carObject.getClass();

                try {
                    Method method = carClass.getMethod("getValue", String.class, boolean.class);
                    output = (String)method.invoke(carObject, "", true);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                textArea1.setText(output);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textField3.getText();
                String value = textField2.getText();
                boolean output = false;

                Object carObject = (Object) comboBox1.getSelectedItem();
                Class carClass = carObject.getClass();

                try {
                    Method method = carClass.getMethod("setValue", String.class, String.class);
                    output = (boolean) method.invoke(carObject, name, value);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (!(output)) {
                    label1.setText("No such field!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object carObject = (Object) comboBox1.getSelectedItem();
                comboBox1.removeItem(carObject);
                carObject = null;
            }
        });

        serializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object carObject = (Object) comboBox1.getSelectedItem();
                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("carObject.dat")))
                {
                    oos.writeObject(carObject);
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        deserializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileOpen = new JFileChooser();
                int ret = fileOpen.showDialog(null, "Open File");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileOpen.getSelectedFile();
                    /*
                     * Какие-то действия.
                     */
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new oop2GUI("oop2");
        frame.setVisible(true);
    }

}
