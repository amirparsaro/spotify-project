package player;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList = new ArrayList<>();
    private ArrayList<User> followingList = new ArrayList<>();
    private UserBehavior behavior = new RegularBehavior();
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void follow(User user) {
        for (User userInList : followingList) {
            if (user.username.equals(userInList.username)) {
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
        return this.behavior;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void addToPlaylists(Playlist playlist) { // I needed to do this to make "playlists" private
        playlists.add(playlist);
    }
}
