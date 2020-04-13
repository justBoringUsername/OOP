package com.oop2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

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
    private String path;

    public oop2GUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(oop2);
        this.pack();

        textField1.setToolTipText("Write here a type of object you wish to create");
        textField2.setToolTipText("Write here a value");
        textField3.setToolTipText("Write here a field name");
        textField4.setToolTipText("Write here De/Serialization type");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                path = textField1.getText();
                label1.setText(path);

                try {
                    Class carClass = Class.forName("com.oop2." + path);
                    Object carObject = carClass.newInstance();

                    comboBox1.addItem(carObject);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    label1.setText("No such class!");
                }

            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String value = "";
                Object carObject = (Object) comboBox1.getSelectedItem();
                Class carClass = carObject.getClass();

                Field[] declaredFields = carClass.getDeclaredFields();
                for (Field field :declaredFields) {
                    value = value + field;
                    String buf = "";
                    buf = buf + field;
                    int pos = buf.lastIndexOf(".");
                    buf = buf.substring(pos+1);
                    Field fieldA = null;
                    try {
                        fieldA = carClass.getDeclaredField(buf);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    String nameValue = null;
                    fieldA.setAccessible(true);
                    Class fieldType = fieldA.getType();
                    String fieldTypeStr = fieldType.getName();
                    switch(fieldTypeStr) {
                        case "int":
                            try {
                                int intValue = fieldA.getInt(carObject);
                                nameValue = "    " + Integer.toString(intValue);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "boolean":
                            try {
                                boolean boolValue = fieldA.getBoolean(carObject);
                                if (boolValue) {
                                    nameValue = "    true";
                                }
                                else {
                                    nameValue = "    false";
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    value = value + nameValue + "\n";
                }
                textArea1.setText(value);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textField3.getText();
                String value = textField2.getText();

                Object carObject = (Object) comboBox1.getSelectedItem();
                Class carClass = carObject.getClass();

                Field newField = null;
                try {
                    newField = carClass.getDeclaredField(name);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                newField.setAccessible(true);
                Class fieldType = newField.getType();
                String fieldTypeStr = fieldType.getName();
                switch(fieldTypeStr) {
                    case "int":
                        try {
                            newField.setInt(carObject, Integer.parseInt(value));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            label1.setText("No such field!");
                        }
                        break;
                    case "boolean":
                        if (value.equalsIgnoreCase("true")) {
                            try {
                                newField.setBoolean(carObject, true);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                                label1.setText("No such field!");
                            }
                        }
                        else if (value.equalsIgnoreCase("false")) {
                            try {
                                newField.setBoolean(carObject, false);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                                label1.setText("No such field!");
                            }
                        }
                        break;
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
