package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//singleton pattern - represents game with players and cards
public final class Game {
    //used to determine which turn array with player IDs to traverse through
    private Boolean reversed;
    private int currentTurn;
    private String currentColor;
    private int currentNumber;
    private List<Integer> forwardTurn = new ArrayList<>();
    private List<Integer> reversedTurn = new ArrayList<>();
    private HashMap<Integer, Player> players;
    private List<Card> drawPile;
    private List<Card> discardPile = new ArrayList<>();

    //creates game with set amount of players
    public Game(int numPlayers) {
        addPlayers(numPlayers);
        //random player starts
        currentTurn = ThreadLocalRandom.current().nextInt(1, players.size() + 1);
        reversed = false;
        createTurnArrays();
        assignDeck();
        dealCards();
        initializeGame();
    }

    //create ascending and descending arrays of all player IDs
    private void createTurnArrays() {
        createAscending();
        createDescending();
    }
    //creates ascending array of player indices
    private void createAscending() {
        for (int i = 1; i<= players.size(); i++) {
            forwardTurn.add(i);
        }
    }

    //creates descending array of player indices
    private void createDescending() {
        for (int i = players.size(); i>0; i--) {
            reversedTurn.add(i);
        }
    }

    //assign players to game
    private void addPlayers(int numPlayers) {
        players = new HashMap<Integer, Player>();
        for (int i = 1; i <= numPlayers; i++) {
            players.put(i, new Player(i, this));
        }
    }

    //assign deck from Deck singleton to drawPile (pre-shuffle) and then shuffle
    private void assignDeck() {
        drawPile = Deck.getInstance().getDeck();
        shuffleCards();
    }

    //shuffles deck of uno cards
    public void shuffleCards() {
        List<Card> shuffled = new ArrayList<>();
        while (!drawPile.isEmpty()) {
            //get random index within current size of drawPile (the original un-shuffled deck)
            int randIndex = ThreadLocalRandom.current().nextInt(0, drawPile.size());

            //add corresponding card to shuffled deck
            shuffled.add(drawPile.get(randIndex));

            //remove corresponding card from draw pile
            drawPile.remove(randIndex);
        }

        //update drawPile with the new shuffled deck
        drawPile = shuffled;
    }

    //deals 8 cards (alternating) to each player
    public void dealCards() {
        for (int i = 0; i < 8; i++) {
            for (int p = 1; p <= players.size(); p++) {
                players.get(p).takeCard();
            }
        }
    }

    //turns first card face up (from drawPile to discard)
    private void initializeGame() {
        for (Card card: drawPile) {
            if (card.getName().equals("number")) {
                discardPile.add(card);
                drawPile.remove(card);

                currentColor = card.getColor();
                currentNumber = card.getValue();
                break;
            }
        }
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

    public void setReversed(boolean b) {
        reversed = b;
    }

    public boolean getReversed() {
        return reversed;
    }
}
