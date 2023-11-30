package com.example.battleship;

import java.io.Serializable;

public class Cell implements Serializable {
    private boolean isOccupied;
    private boolean isHit;

    public Cell() {
        this.isOccupied = false; // По замовчуванню клітинка порожня
        this.isHit = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
