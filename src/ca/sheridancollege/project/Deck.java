package ca.sheridancollege.project;

import java.util.Collections;

/**
 * Represents a deck of cards for the Blackjack game.
 * Extends GroupOfCards to inherit common functionality.
 * @author chukwukeshiem
 * @edited Marcus Grau
 * @edited Tam Nguyen
 */
public class Deck extends GroupOfCards {

    public Deck() {
        super(52); // Set the maximum size to 52 cards
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        // Generate the deck (52 cards)
        for (String suit : suits) {
            for (String rank : ranks) {
                getCards().add(new BlackJackCard(rank, suit)); // Safely add to the initialized list
            }
        }
    }

    @Override
    public void shuffle() {
        Collections.shuffle(getCards());
    }

    public Card drawCard() {
        return getCards().remove(0); // Remove and return the top card
    }

    public Card dealCard() {
        return getCards().remove(getCards().size() - 1); // Remove and return the bottom card
    }

    public boolean isEmpty() {
        return getCards().isEmpty();
    }
}