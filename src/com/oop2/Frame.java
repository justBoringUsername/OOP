package com.oop2;

import java.io.Serializable;

public class Frame implements Serializable {
    private int enginePower;
    private int body;
    private boolean engineState;

    public Frame() {
        this.enginePower = 1;
        this.body = 0;
        this.engineState = false;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getBody() {
        return body;
    }

    public void setEngineState(boolean engineState) {
        this.engineState = engineState;
    }

    public boolean getEngineState() {
        return engineState;
    }
}