package com.oop2.Serialize;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.*;
import java.util.Collection;
import java.util.List;
//https://www.boraji.com/jackson-api-collection-serialization-and-deserialization-example
public class SerializeJSON {
    String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void putObjects(Collection<Object> carObjects) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithType(new TypeReference<Collection
                        <Object>>() {
        }).writeValue(new File(filename), carObjects);
    }

    public Collection<Object> getObjects() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(Collection.class, Object.class);
        Collection<Object> carObjects = mapper.readValue(new File(filename), typeReference);
        return carObjects;
    }
}
