package com.oop2.Serialize;

import java.util.ArrayList;
import java.util.Collection;

public interface Serialize {
    public void setFilename(String filename);

    public void putObj(ArrayList<Object> carObjects);

    public ArrayList<Object> getObj();
}
