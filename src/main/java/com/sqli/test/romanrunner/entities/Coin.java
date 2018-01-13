package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.configuration.CircencesConfig;

public class Coin extends Material {

    private boolean earned = false; // by default no yet earned

    @Override
    String draw() {
        if (earned) {
            return CircencesConfig.EARNED_COIN;
        }
        return CircencesConfig.COIN;
    }

    public void earned() {
        this.earned = true;
    }
}
