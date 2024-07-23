package com.parth;

import java.util.ArrayList;
import java.util.List;

public class MockSongs {

    public static List<String> getSongStrings() {
        List<String> songs = new ArrayList<>();

        songs.add("somersault");
        songs.add("cassidy");
        songs.add("$10");
        songs.add("havana");
        songs.add("Cassidy");
        songs.add("50 Ways");

        return songs;
    }

    public static List<SongV1> getSongs() {
        List<SongV1> songV1s = new ArrayList<>();

        songV1s.add(new SongV1("somersault", "zero 7", 147));
        songV1s.add(new SongV1("cassidy", "grateful dead", 158));
        songV1s.add(new SongV1("$10", "hitchhiker", 140));
        songV1s.add(new SongV1("havana", "cabello", 105));
        songV1s.add(new SongV1("Cassidy", "grateful dead", 158));
        songV1s.add(new SongV1("50 ways", "simon", 102));

        return songV1s;
    }
}
