package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.ObstacleHitedException;
import com.sqli.test.romanrunner.configuration.CircencesConfig;
import com.sqli.test.romanrunner.configuration.PlayerConfig;

public abstract class Player extends Material {

    String name;
    int position;
    Slot actualSlot;
    boolean isAlive;
    private Track track;
    int score;
    String previousDirection;
    Material lastEarnedMaterial;

    public void startGame(Circenses circenses) {
        //put the player in the first slot
        //get the first slot
        //update it
        //put the player in the left sideby default
        this.position = 0;
        this.track = circenses.getTrack(); //the player get the track
        this.actualSlot = circenses.getTrack().getSlots().get(0); // the player is in the first slot by default !
        this.isAlive = true;
        this.score = 0;
        this.previousDirection = CircencesConfig.LEFT;
        this.lastEarnedMaterial = new PlayerFirstPosition();
        circenses.getTrack().setPlayer(this); //give the player to the track

    }

    public Player forward() throws ObstacleHitedException {
        //if player is alive
        //if player in slot 0
        //change slot 0 put @ in the place
        //else
        //increment position
        //if coin
        //update score + 10
        //else if obstacle
        //update score - 5 and kill the player isAlive=false
        //else
        //throw exception

        position++;
        this.track.update();

        return this;
    }

    public Player right() throws ObstacleHitedException {
        moveTo(CircencesConfig.RIGHT);
        return this;
    }

    public Player left() throws ObstacleHitedException {
        moveTo(CircencesConfig.LEFT);
        return this;
    }

    private void moveTo(String direction) throws ObstacleHitedException {
        this.previousDirection = this.direction;
        if (direction.equals("left")) {
            this.direction = CircencesConfig.LEFT;
        } else {
            this.direction = CircencesConfig.RIGHT;
        }

        track.update();

    }

    abstract void calculateScore();


    public int score(){
        return this.score;
    }

    private char subName() {
        return name.toUpperCase().charAt(0);
    }

    String draw() {
        if (isAlive) {
            return "" + subName() + "";
        }
        return PlayerConfig.DEAD_PLAYER;
    }

}
