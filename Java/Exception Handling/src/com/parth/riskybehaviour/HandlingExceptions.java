package com.parth.riskybehaviour;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class HandlingExceptions {
    private final static Logger logger = LoggerFactory.getLogger(HandlingExceptions.class);

    public static void play() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            System.out.println("Got the sequencer");
        } catch (MidiUnavailableException e) {
            logger.debug("Bummer! An exception occurred: ", e);
        }
    }
}
