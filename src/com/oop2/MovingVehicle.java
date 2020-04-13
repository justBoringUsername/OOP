package com.oop2;

public class MovingVehicle extends Frame {
    private int maxSpeed, acceleration, direction,
            handling, gasTank;
    private boolean working;

    public MovingVehicle() {
      //  super();
        this.maxSpeed = 1;
        this.acceleration = 1;
        this.direction = 0;
        this.handling = 1;
        this.gasTank = 1;
        this.working = false;
    }

    //move,brake,turn:bool
}