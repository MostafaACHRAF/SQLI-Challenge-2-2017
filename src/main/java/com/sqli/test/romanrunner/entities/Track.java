package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.ObstacleHitedException;

import java.util.Stack;

public class Track {

    private Stack<Slot> slots;
    private Player player;


    Track() {
        this.slots = new Stack<Slot>();
    }

    Stack<Slot> getSlots() {
        return this.slots;
    }

    void setSlots(Stack<Slot> slots) {
        this.slots = slots;
    }

    void addBeginSlot() {
        slots.add(0, new Slot().setMaterial(new Begin()));
    }

    void addEndSlot() {
        slots.add(slots.size(), new Slot().setMaterial(new End())); //add the final slot
    }

    public String draw() {

        StringBuilder slotsList = new StringBuilder();

        Stack<Slot> tmpSlots = new Stack<>();
        tmpSlots.addAll(this.slots);

        while (!tmpSlots.isEmpty()) {
            slotsList.append(tmpSlots.pop().draw()).append((tmpSlots.size() == 0) ? "" : "\n");
        }

        return slotsList.toString();
    }

    void setPlayer(Player player) {
        this.player = player;
        slots.get(0).inLeft(player); //update the first slot and put in it the subName of the player
    }

    void update() throws ObstacleHitedException {

        if (player.isAlive) {
            Slot oldSlot = player.actualSlot;
            System.out.println("last earned material : " + player.lastEarnedMaterial.draw() + " previous direction : " + player.previousDirection + " - ");
            oldSlot.update(player.lastEarnedMaterial, player.previousDirection); //put the last earned material in the previous slot

            player.actualSlot = slots.get(player.position);
            player.lastEarnedMaterial = player.actualSlot.getMaterial(player.direction); //memorise the last earned material

            player.calculateScore(); //doing the tests there...

            System.out.println("after earned material : " + player.lastEarnedMaterial.draw() + " -- " + player.direction);
            player.actualSlot.update(player, player.direction);
            player.previousDirection = player.direction;

        } else {
            throw new ObstacleHitedException("You're not alive ! You can't move !!!");
        }
    }


}
