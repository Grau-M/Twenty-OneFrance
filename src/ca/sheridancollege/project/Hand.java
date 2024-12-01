package ca.sheridancollege.project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chukwukeshiem
 */
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // Add a card to the hand
    public void addCard(Card card) {
        cards.add(card);
    }

    // Get the list of cards in the hand
    public List<Card> getCards() {
        return cards;
    }

    // Custom method to display the hand without brackets
    public String displayHand() {
        StringBuilder handString = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            handString.append(cards.get(i)); // Append card
            if (i < cards.size() - 1) {
                handString.append(", "); // Add a comma between cards
            }
        }
        return handString.toString();
    }

    @Override
    public String toString() {
        return displayHand(); // Use the custom display method in toString
    }
}