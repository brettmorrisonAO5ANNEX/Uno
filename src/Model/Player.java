package Model;

import java.util.ArrayList;
import java.util.List;

//represents a player in a game of uno
public class Player {
    private final int playerID;
    private Game game;
    private List<Card> hand = new ArrayList<>();

    public Player(int playerID, Game g) {
        this.playerID = playerID;
        this.game = g;
    }

    //take top card from game pile and remove from draw pile
    public void takeCard() {
        Card topCard = game.getDrawPile().get(0);
        hand.add(topCard);
        game.getDrawPile().remove(topCard);
    }

    //play the chosen card by placing it in the discard pile
    public void playCard(Card c) {
        game.getDiscardPile().add(c);
        hand.remove(c);
        //todo
        switch (c.getName()) {
            case "reverse": turnReverse();
            case "skip": skipTurn();
            case "drawTwo": drawTwo();
            case "drawFour": drawFour();
            case "wildCard": wildCard();
            }
        }

    //set reversed to true and advance to next turn with the opposite traversal array
    private void turnReverse() {
    }

    //advance next turn 2 indices rather than 1
    private void skipTurn() {
    }

    //next player draws two cards then it's their turn
    private void drawTwo() {
    }

    //next player draws four cards then it's their turn
    private void drawFour() {
    }

    //current player gets to change current color in game
    private void wildCard() {
    }


    public List<Card> getHand() {
        return hand;
    }
}
