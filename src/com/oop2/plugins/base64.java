package com.oop2.plugins;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Collection;

public class base64 {
    public Collection<Object> encode(Collection<Object> carObjects) throws IOException {
        for (Object carObject: carObjects) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(carObject);
            oos.flush();
            byte[] data = bos.toByteArray();
            byte[] encodedBytes = Base64.getEncoder().encode(data);
        }
        return null;
    }
}
