package Model;

import java.util.ArrayList;
import java.util.List;

//represents a player in a game of uno
public class Player {
    private final int playerID;
    private Game game;
    private List<Card> hand = new ArrayList<>();

    public Player(int playerID) {
        this.playerID = playerID;
        this.game = Game.getInstance();
    }

    //take top card from game pile and remove from draw pile
    public void takeCard() {
        Card topCard = Game.getInstance().getDrawPile().get(0);
        hand.add(topCard);
        Game.getInstance().getDrawPile().remove(topCard);
    }

    public List<Card> getHand() {
        return hand;
    }
}
