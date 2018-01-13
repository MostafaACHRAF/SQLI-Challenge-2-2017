package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.configuration.CircencesConfig;

public abstract class Material {
    public String direction = CircencesConfig.LEFT;
    String shape;
    abstract String draw();
}
