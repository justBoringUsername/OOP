package com.oop2.Serialize;

import java.io.*;
import java.util.Collection;

public class SerializeOOS {
    String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void putObjects(Collection<Object> carObjects) {

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(carObjects);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public Collection<Object> getObjects() {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            Collection<Object> carObjects =(Collection<Object>)ois.readObject();
            return carObjects;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
