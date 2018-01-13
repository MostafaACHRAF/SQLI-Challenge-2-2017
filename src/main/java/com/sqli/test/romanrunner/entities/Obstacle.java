package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.configuration.CircencesConfig;

public class Obstacle extends Material {
    @Override
    String draw() {
        return CircencesConfig.OBSTACLE;
    }
}
