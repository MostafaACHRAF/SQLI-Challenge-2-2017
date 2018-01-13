package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.configuration.CircencesConfig;

public class Charioteer extends Player {

    public Charioteer(String name) {
        this.name = name;
    }


    @Override
    void calculateScore() {
        if (lastEarnedMaterial instanceof Coin) {
            this.score += 10;
        }
        else if (lastEarnedMaterial instanceof Obstacle) {
            isAlive = false;
            this.score -= 5;
        }
        else if (lastEarnedMaterial instanceof End) {
            this.score += 100;
        }
    }
}
