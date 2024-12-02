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
        
        // Get player's name and buy-in amount
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.print("Enter your buy-in amount: ");
        double buyInAmount = scanner.nextDouble();

        player = new Player(playerName, buyInAmount);
        
        // Prompt to start the game
        System.out.println("\nWelcome, " + playerName + "! Your starting balance is: $" + df.format(player.getBalance()));
        System.out.print("Are you ready to start the game? (yes/no): ");
        String startGameInput = scanner.next();
        
        if (startGameInput.equalsIgnoreCase("yes")) {
            // Get player's wager
            System.out.print("\nEnter your wager: ");
            double wager = scanner.nextDouble();

            // Check if wager is valid
            if (wager > player.getBalance()) {
                System.out.println("Insufficient funds. Please enter a valid wager.");
                return;
            }

            // Update player's balance
            player.setBalance(player.getBalance() - wager);

            // Shuffle the deck before gameplay
            deck.shuffle();
            
            // Deal initial cards
            player.getHand().addCard(deck.drawCard());
            player.getHand().addCard(deck.drawCard());
            dealer.getHand().addCard(deck.drawCard());
            dealer.getHand().addCard(deck.drawCard());

            // Display initial game state
            System.out.println("\nYour balance: $" + player.getBalance());
            System.out.println("Your wager: $" + wager + "\n");
            System.out.println("Your hand: " + player.getHand());
            System.out.println("Dealer's hand: " + dealer.getHand().getCards().get(0) + " [Hidden]");
            
            int playerHandValue = player.getHand().calculateHandValue();
            int dealerHandValue = dealer.getHand().calculateHandValue();

            System.out.println("Player's hand value: " + playerHandValue);
            System.out.println("Dealer's hand value: " + dealerHandValue);


        } else {
            System.out.println("Thank you for playing. Your cashout is: " + player.getBalance() + " Goodbye!");
        } 
    }

    // Main method
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}