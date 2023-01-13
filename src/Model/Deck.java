package Model;

import java.util.ArrayList;
import java.util.List;

//represents a deck of uno cards (singleton)
public final class Deck {
    private static Deck originalDeck;
    private List<Card> deck;

    private Deck() {
        createDeck();
    }

    //creates deck of uno cards
    public void createDeck() {
        deck = new ArrayList<>();
        createNumberCardsNaturals();
        createZeros();
        createSpecialColorCards();
        createSpecialCards();
    }

    //creates double set of 1-9 for each color
    private void createNumberCardsNaturals() {
        for (int c = 0; c < 4; c++) {
            for (int i = 0; i < 2; i++) {
                for (int n = 1; n < 10; n++) {
                    colorAlternateAndCreate("number", c, n, 0);
                }
            }
        }
    }

    //creates zeros for each color
    private void createZeros() {
        deck.add(new Card("number", "yellow", 0, 0));
        deck.add(new Card("number", "blue", 0, 0));
        deck.add(new Card("number", "red", 0, 0));
        deck.add(new Card("number", "green", 0, 0));
    }

    //creates skip, reverse, +2 for each color
    private void createSpecialColorCards() {
        createColorSpecial("skip", 0);
        createColorSpecial("reverse", 0);
        createColorSpecial("drawTwo", 2);
    }

    //creates two special cards of specified type for each color
    private void createColorSpecial(String name, int additiveValue) {
        for (int c=0; c<4; c++) {
            for (int n=0; n<2; n++) {
                colorAlternateAndCreate(name, c, 0, additiveValue);
            }
        }
    }

    //creates cards for each color based on color counter and given type
    private void colorAlternateAndCreate(String name, int color, int value, int additiveValue) {
        switch (color) {
            case 0:
                deck.add(new Card(name, "yellow", value, additiveValue));
            case 1:
                deck.add(new Card(name, "blue", value, additiveValue));
            case 2:
                deck.add(new Card(name, "red", value, additiveValue));
            default:
                deck.add(new Card(name, "green", value, additiveValue));
        }
    }

    //creates WC and +4
    private void createSpecialCards() {
        createSpecial("wildcard", 0);
        createSpecial("drawFour", 4);
    }

    private void createSpecial(String name, int additiveValue) {
        for (int i=0; i<4; i++) {
            deck.add(new Card(name, "null", 0, additiveValue));
        }
    }

    public List<Card> getDeck() {
        return deck;
    }

    public static Deck getInstance() {
        if (originalDeck == null) {
            originalDeck = new Deck();
        }
        return originalDeck;
    }
}
