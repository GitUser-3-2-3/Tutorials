package com.parth;

import java.util.Comparator;
import java.util.List;

public class Jukebox1 {
    public static void main(String[] args) {
        System.out.println();

        new Jukebox1().go();
    }

    public void go() {
        List<SongV1> songV1List = MockSongs.getSongs();
        System.out.println(songV1List + "\n");
/*
        TitleCompare titleCompare = new TitleCompare();
        songV1List.sort(titleCompare);
        System.out.println(songV1List);

        ArtistCompare artistCompare = new ArtistCompare();
        songV1List.sort(artistCompare);
        System.out.println(songV1List);
*/
        songV1List.sort(Comparator.comparing(SongV1::getTitle));
        System.out.println(songV1List + "\n");

        songV1List.sort(Comparator.comparing(SongV1::getArtist));
        System.out.println(songV1List + "\n");

        songV1List.sort(Comparator.comparing(SongV1::getBpm));
        System.out.println(songV1List + "\n");
    }
}

/*
class TitleCompare implements Comparator<SongV1> {
    @Override
    public int compare(SongV1 one, SongV1 two) {
        return one.getTitle().compareTo(two.getTitle());
    }
}

class ArtistCompare implements Comparator<SongV1> {
    @Override
    public int compare(SongV1 one, SongV1 two) {
        return one.getArtist().compareTo(two.getArtist());
    }
}
*/
