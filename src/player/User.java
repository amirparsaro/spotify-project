package player;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList = new ArrayList<>();
    private ArrayList<User> followingList = new ArrayList<>();
    private UserBehavior behavior = new RegularBehavior();
    protected ArrayList<Playlist> playlists = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();

    public void follow(User user) {
        for (User userInList : followingList) {
            if (user.username == userInList.username) {
                throw new InvalidOperationException("User with " + user.username +
                        " is already followed by " + this.username + ".");
            }
        }
        followingList.add(user);
        user.followerList.add(this);
    }

    public void createPlaylist(String title, User owner) {
        this.behavior.createPlaylist(title, owner);
    }

    public void playMusic(Music music) {
        this.behavior.playMusic(music);
    }

    public void buyPremium(User owner, int month) {
        this.behavior.buyPremium(owner, month);
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }
}
