package player;

import java.util.ArrayList;

public class Playlist {
    private String title;
    private ArrayList<Music> playlist = new ArrayList<>();
    private User owner;

    public Playlist(String title, User owner) {
        this.title = title;
        this.owner = owner;
    }

    public void editTitle(String title, String password) throws InvalidOperationException {
        if (!password.equals(this.owner.getPassword())) {
            throw new InvalidOperationException("Incorrect password.");
        }

        Playlist returnedPlaylist = owner.searchForPlaylist(this.title);
        if (returnedPlaylist != null) {
            throw new InvalidOperationException("Playlist already exists with this name.");
        }

        this.title = title;
    }

    public void addMusic(Music music, String password) throws InvalidOperationException {
        if (!password.equals(this.owner.getPassword())) {
            throw new InvalidOperationException("Incorrect password.");
        }

        this.playlist.add(music);
    }

    public void removeMusic(Music music, String password) throws InvalidOperationException {
        if (!password.equals(this.owner.getPassword())) {
            throw new InvalidOperationException("Incorrect password.");
        }

        if (!playlist.contains(music)) {
            throw new InvalidOperationException("Music is not in playlist.");
        }

        this.playlist.remove(music);
    }

    public ArrayList<Music> searchInPlaylist(String title) {
        ArrayList<Music> musicsReturned = new ArrayList<>();
        for (Music music : playlist) {
            if (title.equals(music.getTitle())) {
                musicsReturned.add(music);
            }
        }

        if (musicsReturned.isEmpty()) {
            return null;
        } else {
            return musicsReturned;
        }
    }

    public Music searchInPlaylist(String title, String singerName) {
        for (Music music : playlist) {
            if (title.equals(music.getTitle()) && music.getSinger().getUsername().equals(singerName)) {
                return music;
            }
        }

        return null;
    }

    public void playPlaylist() {
        for (Music music : playlist) {
            music.play();
        }
    }

    public void shufflePlaylist() {
        int playlistSize = playlist.size();
        int musicsPlayed = 0;
        ArrayList<Integer> musicIdPlayed = new ArrayList<>();

        while (musicsPlayed < playlistSize) {
            int randomId = (int) (Math.random() * playlistSize);

            if (!musicIdPlayed.contains(randomId)) {
                playlist.get(randomId).play();
                musicsPlayed++;
                musicIdPlayed.add(randomId);
            }
        }
    }

    public String getTitle() {
        return title;
    } // removed setTitle because we already have editTitle method
}
