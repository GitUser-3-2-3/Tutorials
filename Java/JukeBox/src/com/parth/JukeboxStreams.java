package com.parth;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JukeboxStreams {
    public static void main(String[] args) {
        System.out.println();

        methodTwo();
        methodThree();
    }

    public static void methodThree() {
        List<Song> songs = new Songs().getSongs();

        /* List<Song> oldToNew = songs.stream()
            .sorted(((o1, o2) -> o1.getYear() - o2.getYear()))
            .toList(); */

        List<Song> oldToNew = songs.stream()
            .sorted(Comparator.comparingInt(Song::getYear))
            .toList();
        System.out.println(oldToNew);
    }

    public static void methodTwo() {
        List<Song> songs = new Songs().getSongs();

        /* List<String> allGenre = songs.stream()
            .map(Song::getGenre)
            .distinct()
            .toList();
        System.out.println(allGenre); */

        Set<String> allGenre = songs.stream()
            .map(Song::getGenre)
            .collect(Collectors.toSet());
        System.out.println(allGenre);
    }

    public static void methodOne() {
        List<Song> songs = new Songs().getSongs();

        /* List<Song> rockSongs = songs.stream()
            .filter(song -> song.getGenre().equals("Rock"))
            .toList();
        System.out.println(rockSongs); */

        List<Song> allRockSongs = songs.stream()
            .filter(song -> song.getGenre().contains("Rock"))
            .toList();
        System.out.println(allRockSongs);

        List<Song> byTheBeatles = songs.stream()
            .filter(song -> song.getTitle().contains("The Beatles"))
            .toList();
        System.out.println(byTheBeatles);

        List<Song> startsWithH = songs.stream()
            .filter(song -> song.getTitle().startsWith("H"))
            .toList();
        System.out.println(startsWithH);

        List<Song> after1995 = songs.stream()
            .filter(song -> song.getYear() > 1995)
            .toList();
        System.out.println(after1995);
    }
}
