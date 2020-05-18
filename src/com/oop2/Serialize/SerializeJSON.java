package com.oop2.Serialize;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.oop2.content.Car;
import com.oop2.content.Frame;
import com.oop2.content.Truck;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class SerializeJSON {
    String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void putObjects(ArrayList<Object> carObjects) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), carObjects);
    }

    public <carClass> ArrayList<Object> getObjects() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Object> carObjects = mapper.readValue(new File(filename), mapper.getTypeFactory().constructCollectionType(ArrayList.class, Object.class));
        ArrayList<Object> res = new ArrayList<>();
        for (Object carObject: carObjects) {
            LinkedHashMap<String, String> newObj = new LinkedHashMap<>();
            newObj = (LinkedHashMap<String, String>)carObject;
            Set<String> newSet = newObj.keySet();
            Object unknown = null;
                try (ScanResult scanResult = new ClassGraph()
                        .whitelistPackages("com.oop2.content")
                        .scan()) {
                    for (ClassInfo classInfo : scanResult.getAllClasses()) {
                        Class carClass = Class.forName(classInfo.getName());
                        unknown = carClass.newInstance();
                        Set<String> compare = null;
                        try {
                            Method method = carClass.getMethod("WhoAmI");
                            compare = (Set<String>)method.invoke(unknown);
                            if (newSet.equals(compare)) {
                                res.add(unknown);
                            }
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }
        return res;
    }
}
