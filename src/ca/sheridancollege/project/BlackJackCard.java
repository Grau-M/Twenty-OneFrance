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
public class BlackJackCard extends Card {
    private String rank;
    private String suit;
    private int value;

    public BlackJackCard(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        
        if (rank.equals("Ace")){
            value = 11;
        } else if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
            value = 10;
        } else {
            value = Integer.parseInt(rank);
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