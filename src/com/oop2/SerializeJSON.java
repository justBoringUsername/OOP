package com.oop2;

import java.io.StringWriter;

public class SerializeJSON implements Serialize {
    private int b;

   public void print() {
       StringWriter writer = new StringWriter();

       //это объект Jackson, который выполняет сериализацию
       // ObjectMapper mapper = new ObjectMapper();

       // сама сериализация: 1-куда, 2-что
       //mapper.writeValue(writer, cat);

       //преобразовываем все записанное во StringWriter в строку
       String result = writer.toString();
    }
}
