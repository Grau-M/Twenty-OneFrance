package ca.sheridancollege.project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chukwukeshiem
 * @edited Marcus Grau
 * @edited Tam Nguyen
 */
public class Player {
    private String name;
    private double balance;
    private Hand hand;

    // Constructor
    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.hand = new Hand();
    }

    // Get the hand
    public Hand getHand() {
        return hand;
    }

    // Get the name
    public String getName() {
        return name;
    }

    // Get the balance
    public double getBalance() {
        return balance;
    }
    
    // Set the balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Place a bet
    public void placeBet(double amount) {
        if (amount > balance) {
            System.out.println("Not enough balance!");
        } else {
            balance -= amount;
        }
    }
    
    public void winBet(double betAmount) {
        balance += betAmount * 2; // Double the bet on a win
    }

    public void loseBet(double betAmount) {
        // Balance is already decreased when placing the bet
    }

    // Add balance (e.g., after winning)
    public void addBalance(double amount) {
        balance += amount;
    }
    
    // Hit method to draw a card
    public void hit(Deck deck) {
        if (deck.isEmpty()) {
            System.out.println("The deck is empty, " + name + " cannot draw a card.");
            return;
        }
        // Use drawCard() to take from the top of the deck
        Card drawnCard = deck.drawCard();
        hand.addCard(drawnCard);
        System.out.println(name + " hits and draws: " + drawnCard);
        System.out.println(name + "'s hand: " + hand.displayHand());
        
    }
    
    // Stand method 
    public void stand() {
        System.out.println(name + " stands with a value of: " + hand.calculateHandValue());
    }
}