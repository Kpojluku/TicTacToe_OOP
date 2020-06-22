package Game;

public class GamePlayer {
    private char playerSing;
    private boolean realPlayer = true;

    public GamePlayer(boolean isRealPlayer ,char playerSing) {
        this.realPlayer = isRealPlayer;
        this.playerSing = playerSing;
    }

    public char getPlayerSing() {
        return playerSing;
    }

    public boolean isRealPlayer() {
        return realPlayer;
    }
}
