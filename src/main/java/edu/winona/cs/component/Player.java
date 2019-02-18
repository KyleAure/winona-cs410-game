
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String username;
    private String password;
    private List<Score> highScores = new ArrayList<>();

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Score> getHighScores() {
        return highScores;
    }

    public void setHighScores(List<Score> highScores) {
        this.highScores = highScores;
    }

    @Override
    public String toString() {
        return "Player: " + username
                + "Password: " + password
                + "High-Scores: " + highScores;
    }

}
