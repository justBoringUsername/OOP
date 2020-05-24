package com.oop2;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.oop2.Other.*;

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
    private JLabel label2;
    private JTextArea textArea2;
    private String path, idInfo;
    public static int globalId;
    private Map<String, Object> elements = new HashMap<>();
    private ArrayList<Object> elementsNew = new ArrayList<>();
    private ArrayList<String> plugins = new ArrayList<>();
    private ArrayList<Object> elementsClone = new ArrayList<>();

    public oop2GUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(oop2);
        this.pack();

        textField1.setToolTipText("Write here a type of object you wish to create");
        textField2.setToolTipText("Write here a value");
        textField3.setToolTipText("Write here a field name");
        textField4.setToolTipText("Write here De/Serialization type");
        getClasses();

        globalId = 0;
        getPlugins();

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                path = textField1.getText();

                try {
                    Class carClass = Class.forName("com.oop2.content." + path);
                    Object carObject = carClass.newInstance();

                    idInfo = carClass.getName() + "|globalId: " +
                            Integer.toString(globalId);
                    elements.put(idInfo, carObject);
                    elementsClone.add(carObject);
                    comboBox1.addItem(idInfo);

                    globalId++;
                    label1.setText("Notes");
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
                String key = (String) comboBox1.getSelectedItem();
                Object carObject = elements.get(key);
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

                String key = (String) comboBox1.getSelectedItem();
                Object carObject = elements.get(key);
                Class carClass = carObject.getClass();

                try {
                    Method method = carClass.getMethod("setValue", String.class, String.class);
                    output = (boolean) method.invoke(carObject, name, value);
                    label1.setText("Notes");
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
                String key = (String) comboBox1.getSelectedItem();
                Object carObject = elements.get(key);
                comboBox1.removeItem(key);
                elements.remove(key);
                elementsClone.remove(carObject);
                textArea1.setText("fields");
            }
        });

        serializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getPlugins();
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setAcceptAllFileFilterUsed(false);
                for (String filterStr: getSerialize()) {
                    FileFilterExt filterExt = new FileFilterExt("."+filterStr, "."+filterStr);
                    fileOpen.addChoosableFileFilter(filterExt);
                }
                for (String filterStr1: getSerialize()) {
                    for (String filterStr2: plugins) {
                        FileFilterExt filterExt = new FileFilterExt("."+filterStr1+filterStr2, "."+filterStr1+filterStr2);
                        fileOpen.addChoosableFileFilter(filterExt);
                    }
                }
                fileOpen.setDialogTitle("Save File");
                fileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int ret = fileOpen.showDialog(null, "Save File");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    String ex = fileOpen.getFileFilter().getDescription();
                    ex = ex.substring(1);
                    String extensionFrom = fileOpen.getSelectedFile() + "";
                    int pos = extensionFrom.lastIndexOf(".");
                    extensionFrom = extensionFrom.substring(pos+1, extensionFrom.length());
                    boolean plugInOn = false;
                    String exSer = "";
                    String plugName = "";
                    for (String plugIn: plugins) {
                        if (ex.contains(plugIn)) {
                            plugInOn = true;
                            plugName = plugIn;
                            exSer = ex.replace(plugIn, "");
                        }
                    }

                    try {
                        if (plugInOn) {
                            Class serClass = Class.forName("com.oop2.Serialize.Serialize" + exSer.toUpperCase());
                            Object serObject = serClass.newInstance();
                            Method method1 = serClass.getMethod("setFilename", String.class);
                            method1.invoke(serObject, fileOpen.getSelectedFile() + "");
                            Method method2 = serClass.getMethod("putObjects", ArrayList.class);
                            method2.invoke(serObject, elementsClone);
                            Class plugClass = Class.forName("com.oop2.plugins." + plugName);
                            Object plugObject = plugClass.newInstance();
                            Method method3 = plugClass.getMethod("encode", String.class);
                            method3.invoke(plugObject, fileOpen.getSelectedFile() + "");
                        }
                        else {
                            Class serClass = Class.forName("com.oop2.Serialize.Serialize" + ex.toUpperCase());
                            Object serObject = serClass.newInstance();
                            Method method1 = serClass.getMethod("setFilename", String.class);
                            method1.invoke(serObject, fileOpen.getSelectedFile() + "");
                            Method method2 = serClass.getMethod("putObjects", ArrayList.class);
                            method2.invoke(serObject, elementsClone);
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deserializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getPlugins();
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setDialogTitle("Save File");
                fileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int ret = fileOpen.showDialog(null, "Open File");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    String extensionFrom = fileOpen.getSelectedFile() + "";
                    int pos = extensionFrom.lastIndexOf(".");
                    extensionFrom = extensionFrom.substring(pos+1, extensionFrom.length());
                    String ex = extensionFrom;
                    boolean plugInOn = false;
                    boolean plugError = true;
                    String exSer = "";
                    String plugName = "";
                    for (String plugIn: plugins) {
                        if (ex.contains(plugIn)) {
                            plugInOn = true;
                            plugName = plugIn;
                            exSer = ex.replace(plugIn, "");
                            plugError = false;
                        }
                    }
                    if (ex.length() < 4)
                        plugError = false;

                    if (plugError) {
                        JOptionPane.showMessageDialog(oop2GUI.this, "<html><h2>Error</h2>" +
                                "<i>PlugIn not found</i>");
                    }
                    else {
                        try {
                            if (plugInOn) {
                                Class plugClass = Class.forName("com.oop2.plugins." + plugName);
                                Object plugObject = plugClass.newInstance();
                                Method method0 = plugClass.getMethod("decode", String.class);
                                method0.invoke(plugObject, fileOpen.getSelectedFile() + "");
                                Class serClass = Class.forName("com.oop2.Serialize.Serialize" + exSer.toUpperCase());
                                Object serObject = serClass.newInstance();
                                Method method1 = serClass.getMethod("setFilename", String.class);
                                method1.invoke(serObject, fileOpen.getSelectedFile() + "temp");
                                Method method2 = serClass.getMethod("getObjects");
                                elementsNew = (ArrayList<Object>) method2.invoke(serObject);
                                File file = new File(fileOpen.getSelectedFile() + "temp");
                                file.delete();
                                for (Object newObj : elementsNew) {
                                    Class carClass = newObj.getClass();
                                    idInfo = carClass.getName() + "|globalId: " +
                                            Integer.toString(globalId);
                                    elements.put(idInfo, newObj);
                                    elementsClone.add(newObj);
                                    comboBox1.addItem(idInfo);

                                    globalId++;
                                }
                            } else {
                                Class serClass = Class.forName("com.oop2.Serialize.Serialize" + ex.toUpperCase());
                                Object serObject = serClass.newInstance();
                                Method method1 = serClass.getMethod("setFilename", String.class);
                                method1.invoke(serObject, fileOpen.getSelectedFile() + "");
                                Method method2 = serClass.getMethod("getObjects");
                                elementsNew = (ArrayList<Object>) method2.invoke(serObject);
                                for (Object newObj : elementsNew) {
                                    Class carClass = newObj.getClass();
                                    idInfo = carClass.getName() + "|globalId: " +
                                            Integer.toString(globalId);
                                    elements.put(idInfo, newObj);
                                    elementsClone.add(newObj);
                                    comboBox1.addItem(idInfo);

                                    globalId++;
                                }
                            }
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public ArrayList<String> getSerialize() {
        ArrayList<String> SerializeClasses = new ArrayList<String>();
        try (ScanResult scanResult = new ClassGraph()
                .whitelistPackages("com.oop2.Serialize")
                .scan()) {
            for (ClassInfo classInfo : scanResult.getAllClasses()) {
                if (classInfo.getName() != "com.oop2.Serialize.Serialize") {
                    String newStr = classInfo.getName();
                    newStr = newStr.substring(28, classInfo.getName().length());
                    newStr = newStr.toLowerCase();
                    if (newStr.length() != 0) {
                        SerializeClasses.add(newStr);
                    }
                }
            }
        }
        return SerializeClasses;
    }

    public void getClasses() {
        String output = "Classes:\n";
        try (ScanResult scanResult = new ClassGraph()
                .whitelistPackages("com.oop2.content")
                .scan()) {
            for (ClassInfo classInfo : scanResult.getAllClasses()) {
                    String newStr = classInfo.getName();
                    newStr = newStr.substring(17, classInfo.getName().length());
                    if (newStr.length() != 0) {
                        output += newStr;
                        output += "\n";
                    }
                }
            }
        textArea2.setText(output);
    }

    public void getPlugins() {
        plugins.clear();
        try (ScanResult scanResult = new ClassGraph()
                .whitelistPackages("com.oop2.plugins")
                .scan()) {
            for (ClassInfo classInfo : scanResult.getAllClasses()) {
                String newStr = classInfo.getName();
                newStr = newStr.substring(17);
                plugins.add(newStr);
            }
        }
        if (plugins.isEmpty()) {
            label2.setText("No plugins found");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new oop2GUI("OOP");
        frame.setVisible(true);
    }

}
