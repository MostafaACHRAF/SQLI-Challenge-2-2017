package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.configuration.CircencesConfig;

public class End extends Material {
    @Override
    String draw() {
        return CircencesConfig.END;
    }
}
