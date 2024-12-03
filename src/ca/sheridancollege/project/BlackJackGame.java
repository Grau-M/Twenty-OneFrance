package ca.sheridancollege.project;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author chukwukeshiem
 * @edited Marcus Grau, Tam Nguyen
 *
 */
public class BlackJackGame extends Game {

    private Deck deck;
    private BlackJackPlayer player;
    private Dealer dealer;
    private double wager; // Class-level variable to store the wager amount

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public BlackJackGame() {
        super("Blackjack");
        deck = new Deck();
        dealer = new Dealer();
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        double buyInAmount = getBuyInAmount(scanner);

        player = new BlackJackPlayer(playerName, buyInAmount);

        System.out.println("\nWelcome, " + playerName + "! Your starting balance is: $" + df.format(player.getBalance()));

        boolean playAgain;
        do {
            playRound(scanner); // Sets the wager value
            playAgain = promptPlayAgain(scanner);
        } while (playAgain);

        System.out.println("\nThanks for playing, " + playerName + "! Your final balance is: $" + df.format(player.getBalance()));
    }

    @Override
    public void declareWinner() {
        int playerValue = player.getHand().calculateHandValue();
        int dealerValue = dealer.getHand().calculateHandValue();

        if ((playerValue > dealerValue && playerValue <= 21) || (playerValue <= 21 && dealerValue > 21)) {
            System.out.println("\nCongratulations, you win! Wager: $" + df.format(wager));
            player.winBet(wager);
        } else if (dealerValue > playerValue || playerValue > 21) {
            System.out.println("\nDealer wins! Wager: $" + df.format(wager));
        } else {
            System.out.println("\nIt's a tie! Wager: $" + df.format(wager));
            player.setBalance(player.getBalance() + wager); // Return the wager
        }
    }

    private void playRound(Scanner scanner) {
        resetGame();

        wager = getWager(scanner); // Set the class-level wager variable
        player.placeBet(wager);

        // Deal initial cards
        dealInitialCards();

        // Display game state
        System.out.println("\nYour wager: $" + df.format(wager));
        System.out.println("Dealer's hand: " + dealer.getHand().getCards().get(0) + " [Hidden]");

        // Player's turn
        playerTurn(scanner);

        // Dealer's turn
        if (!player.getHand().isBusted()) {
            dealerTurn();
        }

        declareWinner();
        System.out.println("\nYour balance: $" + df.format(player.getBalance()));
    }

    private double getBuyInAmount(Scanner scanner) {
        double buyInAmount = 0.0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter your buy-in amount: ");
            try {
                buyInAmount = scanner.nextDouble();
                if (buyInAmount > 0) {
                    validInput = true;
                } else {
                    System.out.println("Buy-in amount must be greater than zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
            }
        }
        return buyInAmount;
    }

    private double getWager(Scanner scanner) {
        double wager = 0.0;

        while (true) {
            System.out.print("\nEnter your wager: ");
            try {
                wager = scanner.nextDouble();
                if (wager > 0 && wager <= player.getBalance()) {
                    break;
                } else {
                    System.out.println("Invalid wager. Enter an amount between $0 and $" + df.format(player.getBalance()));
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
            }
        }

        return wager;
    }

    private void resetGame() {
        deck = new Deck();
        deck.shuffle();
        player.getHand().clear();
        dealer.getHand().clear();
    }

    private void dealInitialCards() {
        player.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
    }

    private void playerTurn(Scanner scanner) {
        while (true) {
            System.out.println("\nYour hand: " + player.getHand().displayHand());
            System.out.println("Hand value: " + player.getHand().calculateHandValue());

            int choice = 0;

            do {
                System.out.println("\nWould you like to hit or stand? (1) Hit, (2) Stand");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    scanner.next(); // Clear the invalid input
                }

                choice = scanner.nextInt();

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
                    System.out.println("Invalid choice. Please try again.");
                    choice = 0; // Reset choice to force another iteration
                }
            } while (choice != 1 && choice != 2);
        }
    }

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

    private boolean promptPlayAgain(Scanner scanner) {
//        System.out.println("\nWould you like to play again? (1) Yes, (2) No");
//        int choice = scanner.nextInt();
//        
        int choice = 1;

        do {
            System.out.println("\nWould you like to play again: (1) Yes, (2) No");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.next();
            }

            choice = scanner.nextInt();

            if (choice == 1) {
                return true;
            } else if (choice == 2) {
                return false;
            } else {
                System.out.println("Invalid choice. Please try again");
                choice = 0;
            }
        } while (choice != 1 && choice != 2);
        return false;
    }

    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame();
        game.play();
    }
}
