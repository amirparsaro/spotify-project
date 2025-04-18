package player;

import java.util.ArrayList;
import java.util.Objects;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream = 0;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public static void play(Music music) {
        System.out.println("PLaying music " + music.title + "...");
        music.numberOfStream++;
    }

    public static ArrayList<Music> search(String title) {
        ArrayList<Music> musicsReturned = new ArrayList<>();
        for (Music music : allMusics) {
            if (title.equals(music.title)) {
                musicsReturned.add(music);
            }
        }

        if (musicsReturned.isEmpty()) {
            return null;
        } else {
            return musicsReturned;
        }
    }

    public static Music search(String title, String singerName) {
        for (Music music : allMusics) {
            if (title.equals(music.title) && music.singer.getUsername().equals(singerName)) {
                return music;
            }
        }

        return null;
    }
}
