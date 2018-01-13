package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.configuration.CircencesConfig;

public class Slot {

    private Material leftMaterial; //the material in the left
    private Material rightMaterial; //the material in the right

    //create slot that contain coin or obstacl or player or x or dead player or the first position of a player
    public Slot() {}

    public String draw() {
        return "|" + leftMaterial.draw() + rightMaterial.draw() + "|";
    }


    //by default get the left material
    Material getLeftMaterial() {
        return leftMaterial;
    }

    public Material getRightMaterial() {
        return rightMaterial;
    }

    private void inLeftAndRight(Material material) {
        this.leftMaterial = material;
        this.rightMaterial = material;
    }

    void inLeft(Material material) {
        this.leftMaterial = material;
        this.rightMaterial = new Empty();
    }

    private void inRight(Material material) {
        this.rightMaterial = material;
        this.leftMaterial = new Empty();
    }

    Material getMaterial(String direction) {
        if (direction.equals("right")) {
            return rightMaterial;
        }
        return leftMaterial;
    }

    void update(Material material, String direction) {

        //if the material is a coin draw it in the appropriate side
        //change the coin to x
        if (material instanceof Coin) {
            ((Coin) material).earned();
        }
        updateMaterial(material, direction);

    }

    public void updateMaterial(Material material, String direction) {
        if (direction.equals("left")) {
            this.leftMaterial = material;
        } else {
            this.rightMaterial = material;
        }
    }

    public Slot setMaterial(Material material) {

        if (material instanceof Begin || material instanceof End) {
            inLeftAndRight(material);
        } else if (material.direction.equals(CircencesConfig.LEFT)) {
            inLeft(material);
        } else {
            inRight(material);
        }

        return this;
    }
}
