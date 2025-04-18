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

    public User(String username, String password) throws InvalidOperationException {
        for (User user : allUsers) {
            if (user.username.equals(username)) {
                throw new InvalidOperationException("User already exists with this name.");
            }
        }

        if (password.length() < 8) {
            throw new InvalidOperationException("Password length is less that 8 characters.");
        }

        this.username = username;
        this.password = password;
        allUsers.add(this);
    }

    public void follow(User user) throws InvalidOperationException {
        for (User userInList : followingList) {
            if (user.username.equals(userInList.username)) {
                throw new InvalidOperationException("User with " + user.username +
                        " is already followed by " + this.username + ".");
            }
        }
        followingList.add(user);
        user.followerList.add(this);
    }

    public void createPlaylist(String title) throws InvalidOperationException {
        this.behavior.createPlaylist(title, this);
    }

    public void playMusic(Music music) throws InvalidOperationException {
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

    protected void addToPlaylists(Playlist playlist) throws InvalidOperationException { // I needed to do this to make "playlists" private
        Playlist returnedPlaylist = null;
        try {
            returnedPlaylist = this.searchForPlaylist(playlist.getTitle());
        } catch (InvalidOperationException e) {

        }
        if (returnedPlaylist != null) {
            throw new InvalidOperationException("Playlist already exists with this name.");
        }

        playlists.add(playlist);
    }
}
