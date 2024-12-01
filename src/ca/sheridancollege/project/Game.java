package ca.sheridancollege.project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chukwukeshiem
 */
public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    // Constructor
    public Game() {
        deck = new Deck();
        player = new Player("John", 100); // Example player with name and balance
        dealer = new Dealer();
    }

    // Start the game
    public void startGame() {
        deck.shuffle(); // Shuffle the deck

        // Deal initial cards
        player.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());

        // Display hands
        System.out.println("Player's Hand: " + player.getHand().displayHand());
        System.out.println("Dealer's Hand: " + dealer.getHand().getCards().get(0) + " [Hidden]");
    }

    // Main method
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}