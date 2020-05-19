package com.oop2.plugins;

import org.apache.commons.codec.binary.Base32;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class base32 {
    public void encode(String filename) throws IOException {
        byte[] toEncode = new byte[20000];
        Path path = Paths.get(filename);
        try {
            toEncode = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base32 base32 = new Base32();
        byte[] encoded = base32.encode(toEncode);
        try(FileOutputStream fos = new FileOutputStream(filename, false))
        {
            fos.write(encoded, 0, encoded.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void decode(String filename) throws IOException {
        byte[] toDecode = new byte[30000];
        Path path = Paths.get(filename);
        try {
            toDecode = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode(toDecode);
        try(FileOutputStream fos = new FileOutputStream(filename+"temp", false))
        {
            fos.write(decoded, 0, decoded.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
