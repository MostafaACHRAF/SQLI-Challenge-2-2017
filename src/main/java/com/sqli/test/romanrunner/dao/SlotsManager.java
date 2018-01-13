package com.sqli.test.romanrunner.dao;

import com.sqli.test.romanrunner.entities.Material;
import com.sqli.test.romanrunner.entities.Slot;

import java.util.Stack;

public interface SlotsManager {

    void pushNewSlot(Material material);
    Slot fillSlot(Slot slot, Material material); //fill slot with a material could be [coin, obstacle,...]in right or left
}
