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
public class Card {
    private String rank;
    private String suit;
    private int value;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        
        if (rank.equals("2")) {
            value = 2;
        } else if (rank.equals("3")) {
            value = 3;
        } else if (rank.equals("4")) {
            value = 4;
        } else if (rank.equals("5")) {
            value = 5;
        } else if (rank.equals("6")) {
            value = 6;
        } else if (rank.equals("7")) {
            value = 7;
        } else if (rank.equals("8")) {
            value = 8;
        } else if (rank.equals("9")) {
            value = 9;
        } else if (rank.equals("Ace")){
            value = 11;
        } else {
            value = 10;
        }
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
    
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit; // Format the card as "Rank of Suit"
    }
}