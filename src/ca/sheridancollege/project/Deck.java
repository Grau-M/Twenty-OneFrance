package ca.sheridancollege.project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chukwukeshiem
 * @edited Tam Nguyen
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        // Generate the deck (52 cards)
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Draw a card from the deck
    public Card drawCard() {
        return cards.remove(0); // Remove and return the top card
    }
    
    public Card dealCard() {
        return cards.remove(cards.size() - 1); // Remove from the end of the deck
    }
    
    // Error handling when the deck is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}