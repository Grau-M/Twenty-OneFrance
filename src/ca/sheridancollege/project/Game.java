package ca.sheridancollege.project;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.InputMismatchException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author chukwukeshiem
 * @edited Marcus Grau, Tam Nguyen
 *
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

        double buyInAmount = 0.0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("Enter your buy-in amount: ");
            try {
                buyInAmount = scanner.nextDouble();
                if (buyInAmount > 0) {
                    validInput = true;
                } else {
                    System.out.println("Buy-in amount must be greater than zero. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
            }
            
        }

        player = new Player(playerName, buyInAmount);

        // Prompt to start the game
        System.out.println("\nWelcome, " + playerName + "! Your starting balance is: $" + df.format(player.getBalance()));

        while (playAgain) {

            // Get player's wager
            double wager = 0.0;

            do {
                System.out.print("\nEnter your wager: ");
                try {
                    wager = scanner.nextDouble();

                    // Check if the wager is positive
                    if (wager <= 0) {
                        System.out.println("Wager must be greater than zero");

                        // Check if the wager is greater than the player's balance
                    } else if (wager > player.getBalance()) {
                        System.out.println("Insufficient funds. Please enter a wager less than or equal to: $" + df.format(player.getBalance()));
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input: Please enter a numerical value.");
                    scanner.next();
                    wager = -1;
                }
            } while (wager <= 0 || wager > player.getBalance());
            
            // Add the wager to the game
            player.placeBet(wager);

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
            if ((playerHandValue > dealerHandValue && playerHandValue <= 21) || (playerHandValue <= 21 && dealerHandValue > 21)) {
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

            // Balance check - If the balance is zero, end the game
            if (player.getBalance() <= 0) {
                System.out.println("You don't have enough funds to continue. Game over!");
                playAgain = false;
                break; // Exit the loop, game ends
            }
            
            // Create a choice integer
            int choice = 1;
            
            do {
                System.out.println("\nWould you like to play again: (1) Yes, (2) No");
                choice = scanner.nextInt();

                if (choice == 1) {
                    playAgain = true;
                    // Create new Deck and clear all cards from the last match
                    deck = new Deck();
                    deck.shuffle();
                    player.getHand().clear();
                    dealer.getHand().clear();
                } else if (choice == 2) {
                    playAgain = false;
                    System.out.println("\nThanks for playing, your payout is: " + df.format(player.getBalance()));

                } else {
                    System.out.println("Invalid choice. Please try again");
                    choice = 0;
                }
            }while (choice != 1 && choice != 2);
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
