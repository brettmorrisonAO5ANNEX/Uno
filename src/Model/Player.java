package Model;

//represents a player in a game of uno
public class Player {
    private int playerID;
    private Game game;

    public Player(int playerID) {
        this.playerID = playerID;
        this.game = Game.getInstance();
    }

    //take top card from game pile and remove from draw pile
    public void takeCard() {
    }
}
