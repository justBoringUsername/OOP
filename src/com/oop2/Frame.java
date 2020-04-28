package com.oop2;

import java.io.Serializable;

public class Frame implements Serializable {
    protected int enginePower, body, id;
    protected boolean engineState;

    public Frame() {
        this.enginePower = 1;
        this.body = 0;
        this.id = oop2GUI.globalId;
        this.engineState = false;
    }

    public void startEngine() {
        this.engineState = true;
    }

    public void stopEngine() {
        this.engineState = false;
    }

    public boolean setValue(String name, String value) {
            switch (name) {
                case "enginePower":
                    this.enginePower = Integer.parseInt(value);
                    return true;
                case "body":
                    this.body = Integer.parseInt(value);
                    return true;
                case "id":
                    this.id = Integer.parseInt(value);
                    return true;
                case "engineState":
                    this.engineState = Boolean.parseBoolean(value);
                    return true;
                default:
                    return false;
            }
    }

    public String getValue(String name, boolean everyone) {
        if (everyone) {
            String result = "enginePower: " + Integer.toString(this.enginePower) + "\n" +
                    "body: " + Integer.toString(this.body) + "\n" +
                    "id: " + Integer.toString(this.id) + "\n" +
                    "engineState: " + Boolean.toString(this.engineState);
            return result;
        }
        else {
            switch (name) {
                case "enginePower":
                    return Integer.toString(this.enginePower);
                case "body":
                    return Integer.toString(this.body);
                case "id":
                    return Integer.toString(this.id);
                case "engineState":
                    return Boolean.toString(this.engineState);
                default:
                    return null;
            }
        }
    }

    public int getBody() {
        return body;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public int getId() {
        return id;
    }

    public boolean getEngineState() {
        return engineState;
    }
}