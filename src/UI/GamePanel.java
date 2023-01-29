package UI;

import Model.Card;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private final Color ORANGE = new Color(255, 178, 102);
    private final Color BLUE = new Color(102, 178, 255);
    private final Game game;

    public GamePanel() {
        super();
        game = new Game(2);
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout(1,2));
        addPlayerPanel(1, BLUE);
        addCenterPanel();
        addPlayerPanel(2, ORANGE);
    }

    //constructs center panel with current card and draw pile amount
    private void addCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.setLayout(new GridLayout(3, 1));
        addSubPanels(centerPanel);

        add(centerPanel);
    }

    //adds top, middle (with data), and bottom panels
    private void addSubPanels(JPanel centerPanel) {
        addTwoColorPanel(centerPanel);
        addDataPanel(centerPanel);
        addTwoColorPanel(centerPanel);
    }

    private void addTwoColorPanel(JPanel centerPanel) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new GridLayout(1, 2));

        createColorPanel(panel, BLUE);
        createColorPanel(panel, ORANGE);

        centerPanel.add(panel);
    }

    private void createColorPanel(JPanel panel, Color color) {
        JPanel redPanel = new JPanel();
        redPanel.setBackground(color);

        panel.add(redPanel);
    }

    private void addDataPanel(JPanel centerPanel) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new GridBagLayout());
        addCurrentCard(panel);

        centerPanel.add(panel);
    }

    private void addCurrentCard(JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();
        Card currCard = game.getDrawPile().get(0);
        String cardData = currCard.getName() + "/" + currCard.getColor() + "/" + currCard.getValue();
        JLabel currentCard = new JLabel(cardData);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;

        panel.add(currentCard, c);
    }

    //creates simple panel with dropdown for player and their cards
    private void addPlayerPanel(int playerID, Color color) {
        JPanel playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createEmptyBorder());
        playerPanel.setLayout(new GridBagLayout());
        playerPanel.setBackground(color);
        createTitle(playerPanel, playerID);
        createDropDown(playerID, playerPanel);
        createDrawButton(playerPanel);
        createPlayButton(playerPanel);

        add(playerPanel);
    }

    private void createTitle(JPanel playerPanel, int playerID) {
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;

        playerPanel.add(new JLabel("Player " + playerID), c);
    }

    //creates dropdown to display all cards in hand
    private void createDropDown(int playerID, JPanel playerPanel) {
        GridBagConstraints c = new GridBagConstraints();
        ArrayList<Card> hand = game.getPlayers().get(playerID).getHand();
        String[] cards = new String[hand.size()];

        assignCards(cards, hand);

        JComboBox handDropDown = new JComboBox(cards);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        playerPanel.add(handDropDown, c);
    }

    //add all cards from players hand into options for
    private void assignCards(String[] cards, ArrayList<Card> hand) {
        for (int i = 0; i<hand.size(); i++) {
            cards[i] = hand.get(i).getName() + "/" + hand.get(i).getColor() + "/" + hand.get(i).getValue();
        }
    }

    //creates button to play chosen card
    private void createPlayButton(JPanel playerPanel) {
        GridBagConstraints c = new GridBagConstraints();
        JButton playButton = new JButton("play card");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;

        playerPanel.add(playButton, c);
    }

    //creates button to draw card from draw pile
    private void createDrawButton(JPanel playerPanel) {
        GridBagConstraints c = new GridBagConstraints();
        JButton drawButton = new JButton("draw");

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;

        playerPanel.add(drawButton, c);
    }
}
