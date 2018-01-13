package com.sqli.test.romanrunner;

import com.sqli.test.romanrunner.configuration.CircencesConfig;
import com.sqli.test.romanrunner.dao.SlotManagerImpl;
import com.sqli.test.romanrunner.dao.SlotsManager;
import com.sqli.test.romanrunner.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CircensesBuilder {

    private Stack<Slot> slots = new Stack<>();
    private boolean stayInLastSlot = false; //by default create a new slot for each material
    private String direction = CircencesConfig.LEFT;
    private SlotsManager slotsManager = new SlotManagerImpl(slots);


    public CircensesBuilder addCoin() {
        addNewMaterial(new Coin());
        return this;
    }

    public CircensesBuilder addEmptySlot() {
        addNewMaterial(new Empty());
        return this;
    }

    public CircensesBuilder addObstacle() {
        addNewMaterial(new Obstacle());
        return this;
    }

    public Circenses build() {
        return new Circenses(this);
    }

    public CircensesBuilder right() {
        stayInLastSlot = true;
        direction = CircencesConfig.RIGHT;
        return this;
    }

    public CircensesBuilder left() {
        stayInLastSlot = true;
        direction = CircencesConfig.LEFT;

        return this;
    }

    public Stack<Slot> getSlots() {
        return slots;
    }

    private void addNewMaterial(Material material) {

        material.direction = this.direction;

        if (stayInLastSlot) {
            Slot lastSlot = slots.lastElement();
            slotsManager.fillSlot(lastSlot, material);
            stayInLastSlot = false; //set it to default again !
        } else {
            slotsManager.pushNewSlot(material);
        }
    }

}
