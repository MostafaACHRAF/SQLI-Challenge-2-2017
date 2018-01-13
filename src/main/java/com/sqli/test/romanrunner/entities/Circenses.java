package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.CircensesBuilder;

public class Circenses {

    private Track track = new Track();

    public Circenses (CircensesBuilder circensesBuilder) {
        this.track.setSlots(circensesBuilder.getSlots());
        this.track.addBeginSlot();
        this.track.addEndSlot();
    }

    public String draw() {
        return track.draw();
    }

    Track getTrack() {
        return this.track;
    }

}
