package com.sqli.test.romanrunner.dao;

import com.sqli.test.romanrunner.entities.Material;
import com.sqli.test.romanrunner.entities.Slot;

import java.util.Stack;

public class SlotManagerImpl implements SlotsManager {

    private Stack<Slot> slots;

    public SlotManagerImpl(Stack<Slot> slots) {
        this.slots = slots;
    }

    @Override
    public void pushNewSlot(Material material) {
        this.slots.push(new Slot().setMaterial(material));
    }

    @Override
    public Slot fillSlot(Slot slot, Material material) {
        System.out.println("direction : " + material.direction);
        slot.updateMaterial(material, material.direction);
        return slot;
    }


}
