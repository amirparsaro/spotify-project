package player;

public class RegularBehavior implements UserBehavior {
    private int playingLimit = 5;

    @Override
    public void createPlaylist(String Title, User Owner) throws InvalidOperationException {
        throw new InvalidOperationException("Regular user Cannot create a playlist.");
    }

    @Override
    public void playMusic(Music music) throws InvalidOperationException {
        if (playingLimit > 0) {
            music.play();
            playingLimit--;
        } else {
            throw new InvalidOperationException("Music playing limit reached for user.");
        }
    }

    @Override
    public void buyPremium(User owner, int month) {
        UserBehavior behavior = new PremiumBehavior(month);
        owner.setBehavior(behavior);
    }
}
