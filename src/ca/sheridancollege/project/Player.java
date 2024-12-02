package ca.sheridancollege.project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chukwukeshiem
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

    // Add balance (e.g., after winning)
    public void addBalance(double amount) {
        balance += amount;
    }
}