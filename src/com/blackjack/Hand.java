package com.blackjack;

import java.util.ArrayList;

public class Hand {

    // List to hold the cards in this hand
    private ArrayList<Card> hand;

    // Constructor initializes an empty hand
    public Hand(){
        hand = new ArrayList<Card>();
    }

    // Method to take a card from the given deck and add it to this hand
    public void takeCardFromDeck(Deck deck){
        // Add a card taken from the deck to this hand
        hand.add(deck.takeCard());
    }

    // Method to discard all cards from this hand to the given discard deck
    public void discardHandToDeck(Deck discardDeck){
        // Add all cards in this hand to the discard deck
        discardDeck.addCards(hand);
        // Clear the hand as all cards have been discarded
        hand.clear();
    }

    // Method to return a string representation of the cards in this hand
    public String toString(){
        String output = "";
        // Append each card's string representation to the output
        for(Card card: hand){
            output += card + " - ";
        }
        return output; // Return the concatenated string of cards
    }

    // Method to calculate the total value of the hand
    public int calculatedValue(){
        // Variable to accumulate the total value of the hand
        int value = 0;
        // Variable to count the number of aces in the hand
        int aceCount = 0;

        // Iterate through each card in the hand
        for(Card card: hand){
            // Add the card's value to the total hand value
            value += card.getValue();
            // Count the number of aces (value of 11) in the hand
            if (card.getValue() == 11){
                aceCount++;
            }
        }

        // Adjust the value if it exceeds 21 and there are aces in the hand
        if (value > 21 && aceCount > 0){
            // Convert each ace from 11 to 1 until the total value is 21 or less
            while(aceCount > 0 && value > 21){
                aceCount--;
                value -= 10; // Reduce the total value by 10 for each ace adjusted
            }
        }

        // Return the calculated value of the hand
        return value;
    }

    // Method to get the card at a specific index in the hand
    public Card getCard(int index){
        // Return the card at the specified index
        return hand.get(index);
    }
}
