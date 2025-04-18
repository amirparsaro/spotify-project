package player;

public class PremiumBehavior implements UserBehavior {
    private int month;
    @Override
    public void createPlaylist(String Title, User Owner) {
        Playlist playlist = new Playlist();
        Owner.playlists.add(playlist);
    }

    @Override
    public void playMusic(Music music) {
        Music.play(music);
    }

    @Override
    public void buyPremium(User owner, int month) {
        this.month += month;
    }
}
