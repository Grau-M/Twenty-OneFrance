package ca.sheridancollege.project;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chukwukeshiem
 * @edited Marcus Grau
 */
public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    
    // Add a decimal formatter to the string
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Constructor
    public Game() {
        deck = new Deck();
        dealer = new Dealer();
    }

    // Start the game
    public void startGame() {
        // Add a scanner to prompt user input
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        
        System.out.print("Enter your wager: ");
        double wager = scanner.nextDouble();

        player = new Player(playerName, wager);
        
        deck.shuffle(); // Shuffle the deck

        // Deal initial cards
        player.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());

        // Display the balance of the user to the terminal
        System.out.println("\n" + player.getName() + " Balance: " + df.format(player.getBalance()) + "\n");
        
        // Display hands
        System.out.println(player.getName() + " Hand: " + player.getHand().displayHand());
        System.out.println("Dealer's Hand: " + dealer.getHand().getCards().get(0) + " [Hidden]"); 
    }

    // Main method
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}