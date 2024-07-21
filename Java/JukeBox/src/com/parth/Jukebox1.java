package com.parth;

import java.util.Comparator;
import java.util.List;

public class Jukebox1 {
    public static void main(String[] args) {
        System.out.println();

        new Jukebox1().go();
    }

    public void go() {
        List<Song> songList = MockSongs.getSongs();
        System.out.println(songList + "\n");
/*
        TitleCompare titleCompare = new TitleCompare();
        songList.sort(titleCompare);
        System.out.println(songList);

        ArtistCompare artistCompare = new ArtistCompare();
        songList.sort(artistCompare);
        System.out.println(songList);
*/
        songList.sort(Comparator.comparing(Song::getTitle));
        System.out.println(songList + "\n");

        songList.sort(Comparator.comparing(Song::getArtist));
        System.out.println(songList + "\n");

        songList.sort(Comparator.comparing(Song::getBpm));
        System.out.println(songList + "\n");
    }
}

/*
class TitleCompare implements Comparator<Song> {
    @Override
    public int compare(Song one, Song two) {
        return one.getTitle().compareTo(two.getTitle());
    }
}

class ArtistCompare implements Comparator<Song> {
    @Override
    public int compare(Song one, Song two) {
        return one.getArtist().compareTo(two.getArtist());
    }
}
*/
