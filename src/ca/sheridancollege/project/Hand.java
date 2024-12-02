package ca.sheridancollege.project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chukwukeshiem
 * @edited Marcus Grau
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
    
    public int calculateHandValue() {
        int handValue = 0;
        int aceCount = 0;
        
        for (Card card : cards) {
            int cardValue = card.getValue();
            if(cardValue == 11) { // This means the card is an ace
                aceCount++;
                handValue += 11;                
            } else if (cardValue > 10) { // Face value cards
                handValue += 10;
            } else {
                handValue += cardValue;
            }
        }
        
        // Adjust the ace value if needed
        while (handValue > 21 && aceCount > 0) {
            handValue -= 10;
            aceCount--;
        }
        
        return handValue;
    }
    
    public boolean isBusted() {
        return calculateHandValue() > 21;
    }
    
    public void clear() {
        cards.clear(); // Clear the list of cards in the hand
    }
    
    @Override
    public String toString() {
        return displayHand(); // Use the custom display method in toString
    }
}