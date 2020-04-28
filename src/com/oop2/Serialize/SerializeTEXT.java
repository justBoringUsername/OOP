package com.oop2.Serialize;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class SerializeTEXT {
    String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void putObjects(Collection<Object> carObjects) {
        String output = null;
        for (Object carObject: carObjects) {
            Class carClass = carObject.getClass();
            output = "@\n";
            output = carClass.getName() + "\n";

            try {
                Method method = carClass.getMethod("getValue", String.class, boolean.class);
                output = output + (String)method.invoke(carObject, "", true);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            try(FileOutputStream fos=new FileOutputStream(filename))
            {
                byte[] buffer = output.getBytes();

                fos.write(buffer, 0, buffer.length);
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            output = null;
        }
    }

    public Collection<Object> getObjects() {
        try(FileReader reader = new FileReader(filename))
        {
            int c;
            String input = null;
            boolean recorder = false;
            while((c=reader.read())!=-1){
                if (c == '@') {
                    input = null;

                }
                input = input + c;
                Class carClass = Class.forName("com.oop2.");
                Object carObject = carClass.newInstance();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
