package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//singleton pattern - represents game with players and cards
public final class Game {
    private static Game instance;
    private final HashMap<Integer, Player> players;
    private List<Card> drawPile;
    private List<Card> discardPile = new ArrayList<>();

    //creates game with set amount of players
    private Game() {
        players = new HashMap<Integer, Player>();
        assignDeck();
    }

    //assign players to game
    private void addPlayers(int numPlayers) {
        for (int i = 1; i <= numPlayers; i++) {
            players.put(i, new Player(i));
        }
    }

    //assign deck from Deck singleton to unshuffled drawPile
    private void assignDeck() {

    }

    //shuffles deck of uno cards
    public void shuffleCards() {
        List<Card> shuffled = new ArrayList<>();
        while (!drawPile.isEmpty()) {
            //get random index within current size of drawpile (the original un-shuffled deck)
            int randIndex = ThreadLocalRandom.current().nextInt(0, drawPile.size() + 1);

            //add corresponding card to shuffled deck
            shuffled.add(drawPile.get(randIndex));

            //remove corresponding card from draw pile
            drawPile.remove(randIndex);
        }

        //update drawPile with the new shuffled deck
        drawPile = shuffled;
    }

    //todo
    //deals 8 cards (alternating) to each player
    public void dealCards() {
        for (int i = 0; i < 8; i++) {
            for (int p = 0; p < players.size(); p++) {
                players.get(p).takeCard();
            }
        }
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public HashMap<Integer, Player> getPlayers() {
        return players;
    }

    public List<Card> getDrawPile() {
        return drawPile;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }
}
