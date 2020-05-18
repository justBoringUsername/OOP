package com.oop2.Serialize;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SerializeTEXT {
    String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void putObjects(ArrayList<Object> carObjects) throws FileNotFoundException {
        String output = null;
        new FileOutputStream(filename);
        int i = 0;
        for (Object carObject: carObjects) {
            i++;
            Class carClass = carObject.getClass();
            output = "@\n" + carClass.getName() + "\n";
            if (i >= 2) {
                output = "\n@\n" + carClass.getName() + "\n";
            }

            try {
                Method method = carClass.getMethod("getValue", String.class, boolean.class);
                output = output + (String)method.invoke(carObject, "", true);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            try(FileOutputStream fos=new FileOutputStream(filename, true))
            {
                byte[] buffer = output.getBytes();

                fos.write(buffer, 0, buffer.length);
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            output = null;
        }

        output = "\n@";
        try(FileOutputStream fos=new FileOutputStream(filename, true))
        {
            byte[] buffer = output.getBytes();

            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Object> getObjects() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ArrayList<Object> carObjects = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int state = 2;
        Class carClass = null;
        Object carObject = null;
        while ((line = reader.readLine()) != null) {
            if ((state == 1) && (!(line.equals("@")))) {
                int pos = line.indexOf(":");
                String name = line.substring(0, pos);
                String value = line.substring(pos+2);
                Method method = carClass.getMethod("setValue", String.class, String.class);
                method.invoke(carObject, name, value);
            }
            if ((state == 1) && (line.equals("@"))) {
                carObjects.add(carObject);
            }
            if (state == 0) {
                carClass = Class.forName(line);
                carObject = carClass.newInstance();
                state = 1;
            }
            if (line.equals("@")) {
                state = 0;
            }
        }
        return carObjects;
    }
}
