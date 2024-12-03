package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents any grouping of cards for a game.
 * Can be extended to implement specific card collections like Deck or Hand.
 * 
 * @author tamnguyen
 */
public class GroupOfCards {
    private ArrayList<Card> cards; // Initialize as private to enforce encapsulation
    private int size;

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>(); // Initialize the cards list
    }

    /**
     * Get the group of cards.
     *
     * @return the cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Shuffle the group of cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Get the size of the card group.
     *
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Set the size of the card group.
     *
     * @param size the new size.
     */
    public void setSize(int size) {
        this.size = size;
    }
}