package com.blackjack;

public enum Rank {
    ACE("Ace", 11),
    TWO("Two", 2),
    THREE("Three", 3),
    FOUR("Four", 4),
    FIVE("Five", 5),
    SIX("Six", 6),
    SEVEN("Seven", 7),
    EIGHT("Eight", 8),
    NINE("Nine", 9),
    TEN("Ten", 10),
    JACK("Jack", 10),
    QUEEN("Queen", 10),
    KING("King", 10);

    private String rankName; // Name of the rank
    private int rankValue;   // Value of the rank

    // Constructor to initialize the rank with its name and value
    Rank(String rankName, int rankValue) {
        this.rankName = rankName; // Set the rank's name
        this.rankValue = rankValue; // Set the rank's value
    }

    // Public method to get the rank's value
    public int getRankValue() {
        return rankValue;
    }

    // Override toString() to return the name of the rank
    @Override
    public String toString() {
        return rankName; // Return the name of the rank
    }
}
