package com.oop2;

import java.io.Serializable;

public class SUV extends Car implements Serializable {
    private int crushPower;

    public SUV() {
        super();
        this.crushPower = 0;
    }

    public void turnAround() {
        if (this.direction > 360) {
            this.direction = this.direction - 360;
        }
        if (this.direction < -360) {
            this.direction = this.direction + 360;
        }
    }

    public void crushObstacle() {
        this.crushPower--;
    }
}
