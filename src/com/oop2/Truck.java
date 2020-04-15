package com.oop2;

import java.io.Serializable;

public class Truck extends Car implements Serializable {
    private int cargoSize;
    private Car cargoSpecial;

    public Truck() {
        super();
        this.cargoSize = 0;
        this.cargoSpecial = new Car();
    }

    public boolean putCargo(int newCargo) {
        if (this.cargoSize >= newCargo) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setValue(String name, String value) {
        int pos = name.indexOf(".");
        String agr = name.substring(0, pos-1);

        if (agr == "cargoSpecial") {
            String agrP = name.substring(pos+1);
            switch (agrP) {
                case "enginePower":
                    this.cargoSpecial.enginePower = Integer.parseInt(value);
                    return true;
                case "body":
                    this.cargoSpecial.body = Integer.parseInt(value);
                    return true;
                case "id":
                    this.cargoSpecial.id = Integer.parseInt(value);
                    return true;
                case "engineState":
                    this.cargoSpecial.engineState = Boolean.parseBoolean(value);
                    return true;
                case "maxSpeed":
                    this.cargoSpecial.maxSpeed = Integer.parseInt(value);
                    return true;
                case "acceleration":
                    this.cargoSpecial.acceleration = Integer.parseInt(value);
                    return true;
                case "direction":
                    this.cargoSpecial.direction = Integer.parseInt(value);
                    return true;
                case "handling":
                    this.cargoSpecial.handling = Integer.parseInt(value);
                    return true;
                case "gasTank":
                    this.cargoSpecial.gasTank = Integer.parseInt(value);
                    return true;
                case "working":
                    this.cargoSpecial.working = Boolean.parseBoolean(value);
                    return true;
                case "soundLevel":
                    this.cargoSpecial.soundLevel = Integer.parseInt(value);
                    return true;
                case "lightsOn":
                    this.cargoSpecial.lightsOn = Boolean.parseBoolean(value);
                    return true;
                default:
                    return false;
            }
        }

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
            case "soundLevel":
                this.soundLevel = Integer.parseInt(value);
                return true;
            case "lightsOn":
                this.lightsOn = Boolean.parseBoolean(value);
                return true;
            case "cargoSize":
                this.cargoSize = Integer.parseInt(value);
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
                    "soundLevel: " + Integer.toString(this.soundLevel) + "\n" +
                    "lightsOn: " + Boolean.toString(this.lightsOn) + "\n" +
                    "cargoSize: " + Integer.toString(this.cargoSize);

                result = result +
                        "cargoSpecial.enginePower: " + Integer.toString(this.cargoSpecial.enginePower) + "\n" +
                        "cargoSpecial.body: " + Integer.toString(this.cargoSpecial.body) + "\n" +
                        "cargoSpecial.id: " + Integer.toString(this.cargoSpecial.id) + "\n" +
                        "cargoSpecial.engineState: " + Boolean.toString(this.cargoSpecial.engineState) + "\n" +
                        "cargoSpecial.maxSpeed: " + Integer.toString(this.cargoSpecial.maxSpeed) + "\n" +
                        "cargoSpecial.acceleration: " + Integer.toString(this.cargoSpecial.acceleration) + "\n" +
                        "cargoSpecial.direction: " + Integer.toString(this.cargoSpecial.direction) + "\n" +
                        "cargoSpecial.handling: " + Integer.toString(this.cargoSpecial.handling) + "\n" +
                        "cargoSpecial.gasTank: " + Integer.toString(this.cargoSpecial.gasTank) + "\n" +
                        "cargoSpecial.working: " + Boolean.toString(this.cargoSpecial.working) + "\n" +
                        "cargoSpecial.soundLevel: " + Integer.toString(this.cargoSpecial.soundLevel) + "\n" +
                        "cargoSpecial.lightsOn: " + Boolean.toString(this.cargoSpecial.lightsOn);

            return result;
        }
        else {
            int pos = name.indexOf(".");
            String agr = name.substring(0, pos-1);

            if (agr == "cargoSpecial") {
                String agrP = name.substring(pos+1);
                switch (agrP) {
                    case "enginePower":
                        return Integer.toString(this.cargoSpecial.enginePower);
                    case "body":
                        return Integer.toString(this.cargoSpecial.body);
                    case "id":
                        return Integer.toString(this.cargoSpecial.id);
                    case "engineState":
                        return Boolean.toString(this.cargoSpecial.engineState);
                    case "maxSpeed":
                        return Integer.toString(this.cargoSpecial.maxSpeed);
                    case "acceleration":
                        return Integer.toString(this.cargoSpecial.acceleration);
                    case "direction":
                        return Integer.toString(this.cargoSpecial.direction);
                    case "handling":
                        return Integer.toString(this.cargoSpecial.handling);
                    case "gasTank":
                        return Integer.toString(this.cargoSpecial.gasTank);
                    case "working":
                        return Boolean.toString(this.cargoSpecial.working);
                    case "soundLevel":
                        return Integer.toString(this.cargoSpecial.soundLevel);
                    case "lightsOn":
                        return Boolean.toString(this.cargoSpecial.lightsOn);
                    default:
                        return null;
                }
            }

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
                case "soundLevel":
                    return Integer.toString(this.soundLevel);
                case "lightsOn":
                    return Boolean.toString(this.lightsOn);
                case "cargoSize":
                    return Integer.toString(this.cargoSize);
                default:
                    return null;
            }
        }
    }
}
