package com.sqli.test.romanrunner.entities;

public class Knight extends Player {

    public Knight(String name) {
        this.name = name;
    }

    @Override
    void calculateScore() {
        if (lastEarnedMaterial instanceof Coin) {
            this.score += 20;
        }
        else if (lastEarnedMaterial instanceof Obstacle) {
            this.score -= 10;
        }
        else if (lastEarnedMaterial instanceof End) {
            this.score += 100;
        }
    }
}
