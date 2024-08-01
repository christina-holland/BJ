package com.blackjack;

// Enum to represent the suits of cards in a standard deck
public enum Suit {
    CLUB("Clubs"),       // Clubs suit with the name "Clubs"
    DIAMOND("Diamonds"), // Diamonds suit with the name "Diamonds"
    HEART("Hearts"),     // Hearts suit with the name "Hearts"
    SPADE("Spades");     // Spades suit with the name "Spades"

    // Field to hold the name of the suit
    private String suitName; // The name of the suit (e.g., "Clubs", "Diamonds")

    // Constructor to initialize the suit with its name
    Suit(String suitName) {
        this.suitName = suitName; // Set the suit's name
    }

    // Method to return the string representation of the suit
    @Override
    public String toString() {
        return suitName; // Return the name of the suit
    }
}
