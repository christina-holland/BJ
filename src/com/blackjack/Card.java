package com.blackjack;

public class Card implements Comparable<Card> {

    // Instance variables to hold the suit and rank of the card
    private Suit suit;
    private Rank rank;

    // Constructor to initialize a card with a given suit and rank
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Copy constructor to create a new card with the same suit and rank as an existing card
    public Card(Card card) {
        this.suit = card.getSuit();  // Copy the suit from the given card
        this.rank = card.getRank();  // Copy the rank from the given card
    }

    // Method to get the value of the card based on its rank
    public int getValue() {
        return rank.getRankValue();
    }

    // Getter method to retrieve the suit of the card
    public Suit getSuit() {
        return suit;
    }

    // Getter method to retrieve the rank of the card
    public Rank getRank() {
        return rank;
    }

    // Override the toString() method to provide a string representation of the card
    @Override
    public String toString() {
        return ("[" + rank + " of " + suit + "] (" + this.getValue() + ")");
        // Format: [Rank of Suit] (Value)
    }

    // Compare this card to another card based on their values
    @Override
    public int compareTo(Card c) {
        // If this card's value is greater than the other card's value, return 1
        if (this.getValue() > c.getValue()) {
            return 1;
        }
        // If this card's value is less than the other card's value, return -1
        else if (this.getValue() < c.getValue()) {
            return -1;
        }
        // If both cards have the same value, return 0
        else {
            return 0;
        }
    }
}
