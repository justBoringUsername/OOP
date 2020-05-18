package com.oop2.content;

import java.io.Serializable;
import java.util.*;

public class Motorcycle extends MovingVehicle implements Serializable {
    private int jumpHeight;

    public Motorcycle() {
        super();
        this.jumpHeight = 0;
    }

    public void jump() {
        this.jumpHeight++;
    }

    public void fall() {
        this.jumpHeight = 0;
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
            case "maxSpeed":
                this.maxSpeed = Integer.parseInt(value);
                return true;
            case "acceleration":
                this.acceleration = Integer.parseInt(value);
                return true;
            case "direction":
                this.direction = Integer.parseInt(value);
                return true;
            case "handling":
                this.handling = Integer.parseInt(value);
                return true;
            case "gasTank":
                this.gasTank = Integer.parseInt(value);
                return true;
            case "working":
                this.working = Boolean.parseBoolean(value);
                return true;
            case "jumpHeight":
                this.jumpHeight = Integer.parseInt(value);
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
                    "engineState: " + Boolean.toString(this.engineState) + "\n" +
                    "maxSpeed: " + Integer.toString(this.maxSpeed) + "\n" +
                    "acceleration: " + Integer.toString(this.acceleration) + "\n" +
                    "direction: " + Integer.toString(this.direction) + "\n" +
                    "handling: " + Integer.toString(this.handling) + "\n" +
                    "gasTank: " + Integer.toString(this.gasTank) + "\n" +
                    "working: " + Boolean.toString(this.working) + "\n" +
                    "jumpHeight: " + Integer.toString(this.jumpHeight);
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
                case "maxSpeed":
                    return Integer.toString(this.maxSpeed);
                case "acceleration":
                    return Integer.toString(this.acceleration);
                case "direction":
                    return Integer.toString(this.direction);
                case "handling":
                    return Integer.toString(this.handling);
                case "gasTank":
                    return Integer.toString(this.gasTank);
                case "working":
                    return Boolean.toString(this.working);
                case "jumpHeight":
                    return Integer.toString(this.jumpHeight);
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

    public int getAcceleration() {
        return acceleration;
    }

    public int getDirection() {
        return direction;
    }

    public int getGasTank() {
        return gasTank;
    }

    public int getHandling() {
        return handling;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public boolean getWorking() {
        return working;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public Set<String> WhoAmI() {
        Set<String> newSet = null;
        LinkedHashMap<String, String> buf = new LinkedHashMap<>();
        buf.put("enginePower", "0");
        buf.put("body", "0");
        buf.put("id", "0");
        buf.put("engineState", "0");
        buf.put("maxSpeed", "0");
        buf.put("acceleration", "0");
        buf.put("handling", "0");
        buf.put("gasTank", "0");
        buf.put("direction", "0");
        buf.put("working", "0");
        buf.put("jumpHeight", "0");
        newSet = buf.keySet();
        return newSet;
    }
}
