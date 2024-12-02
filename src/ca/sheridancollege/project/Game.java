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
        
        boolean playAgain = true;
        
        // Get player's name and buy-in amount
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.print("Enter your buy-in amount: ");
        double buyInAmount = scanner.nextDouble();

        player = new Player(playerName, buyInAmount);
        
        // Prompt to start the game
        System.out.println("\nWelcome, " + playerName + "! Your starting balance is: $" + df.format(player.getBalance()));
        
        while (playAgain) {
            
            // Get player's wager
            double wager;

            do {
                System.out.print("\nEnter your wager: ");
                wager = scanner.nextDouble();

                if (wager > player.getBalance()) {
                    System.out.println("Insufficient funds. Please enter a wager less than or equal to: $" + df.format(player.getBalance()));
                }
            } while (wager > player.getBalance());
                    
            // Shuffle the deck before gameplay
            deck.shuffle();

            // Deal initial cards
            player.getHand().addCard(deck.drawCard());
            player.getHand().addCard(deck.drawCard());
            dealer.getHand().addCard(deck.drawCard());
            dealer.getHand().addCard(deck.drawCard());

            // Display initial game state
            System.out.println("\nYour balance: $" + df.format(player.getBalance()));
            System.out.println("Your wager: $" + df.format(wager) + "\n");
            System.out.println("Dealer's hand: " + dealer.getHand().getCards().get(0) + " [Hidden]");

            // Player's turn
            playerTurn(scanner);

            // Dealer's turn if the player hasn't busted
            if (!player.getHand().isBusted()) {
                dealerTurn();
            }

            int playerHandValue = player.getHand().calculateHandValue();
            int dealerHandValue = dealer.getHand().calculateHandValue();

            // Determine the winner and update the player's balance
            if (playerHandValue > dealerHandValue && playerHandValue <= 21) {
                System.out.println("\nCongradulations! You win!");
                player.winBet(wager);
            } else if ((dealerHandValue > playerHandValue && dealerHandValue <= 21) || playerHandValue > 21) {
                System.out.println("\nDealer wins!");
                player.loseBet(wager);
            } else {
                System.out.println("\nIt's a tie!");
                // Return the bet to the player
                player.setBalance(player.getBalance() + wager);
            }
            
            System.out.println("\nYour balance: $" + df.format(player.getBalance()));

            System.out.println("\nWould you like to play again: (1) Yes, (2) No");
            int choice = scanner.nextInt();

            if (choice == 1) {
                playAgain = true;
            } else if (choice == 2) {
                playAgain = false;
                System.out.println("Thanks for playing, your payout is: " + df.format(player.getBalance()));

            } else {
                System.out.println("Invalid choice. Please try again");
            }
        }
    }

    // Player's turn logic for hit and stand
    private void playerTurn(Scanner scanner) {
        while (true) {
            System.out.println("\nYour hand: " + player.getHand().displayHand());
            System.out.println("Hand value: " + player.getHand().calculateHandValue());
            System.out.println("Choose an action: (1) Hit, (2) Stand");
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                player.hit(deck);
                
                if (player.getHand().isBusted()) {
                    System.out.println("You busted with hand value " + player.getHand().calculateHandValue());
                    return;
                }
            } else if (choice == 2) {
                player.stand();
                return;
            } else {
                System.out.println("Invalid choice. Please try again");
            }
        }
    }
    
    // Dealer's turn logic for hit and stand
    private void dealerTurn() {
        System.out.println("\nDealer's turn:\n");
        
        while (dealer.getHand().calculateHandValue() < 17) {
            dealer.hit(deck);
        }
        
        if (dealer.getHand().isBusted()) {
            System.out.println("Dealer busted with hand value: " + dealer.getHand().calculateHandValue());   
        } else {
            dealer.stand();
        }
    }
    
    // Main method
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}