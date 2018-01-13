package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.configuration.PlayerConfig;

public class PlayerFirstPosition extends Material {
    @Override
    String draw() {
        return PlayerConfig.PLAYER_FIRST_POSITION;
    }
}
