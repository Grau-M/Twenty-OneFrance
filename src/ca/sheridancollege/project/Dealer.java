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
public class Dealer {
    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }
    
    // Hit method
    public void hit(Deck deck) {
        // Use drawCard() to take from the top of the deck
        Card drawnCard = deck.drawCard();
        hand.addCard(drawnCard);
        System.out.println("Dealer hits and draws: " + drawnCard);
        System.out.println("Dealer's hand: " + hand.displayHand());
    }
}