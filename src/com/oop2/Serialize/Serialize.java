package com.oop2.Serialize;

import java.util.Collection;

public interface Serialize {
    public void setFilename(String filename);

    public void putObj(Collection<Object> carObjects);

    public Collection<Object> getObj();
}
