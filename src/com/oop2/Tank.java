package com.oop2;

import java.io.Serializable;

public class Tank extends MovingVehicle implements Serializable {
    private int armor, shootPower, ammo;

    public Tank() {
        super();
        this.armor = 0;
        this.shootPower = 0;
        this.ammo = 0;
    }

    public boolean shoot(int ammoToUse) {
        if (this.ammo >= ammoToUse) {
            this.ammo = this.ammo - ammoToUse;
            this.armor = this.armor - this.shootPower * ammoToUse / 2;
            return true;
        }
        else {
            return false;
        }
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
            case "armor":
                this.armor = Integer.parseInt(value);
                return true;
            case "shootPower":
                this.shootPower = Integer.parseInt(value);
                return true;
            case "ammo":
                this.ammo = Integer.parseInt(value);
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
                    "armor: " + Integer.toString(this.armor) + "\n" +
                    "shootPower: " + Integer.toString(this.shootPower) + "\n" +
                    "ammo: " + Integer.toString(this.ammo);
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
                case "armor":
                    return Integer.toString(this.armor);
                case "shootPower":
                    return Integer.toString(this.shootPower);
                case "ammo":
                    return Integer.toString(this.ammo);
                default:
                    return null;
            }
        }
    }
}
