package com.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    // An ArrayList to hold the deck of Cards
    private ArrayList<Card> deck;

    // Default constructor initializes an empty deck
    public Deck(){
        deck = new ArrayList<Card>();
    }

    // Copy constructor creates a new deck from an existing deck
    public Deck(Deck e){
        // Copy the cards from the provided deck to this new deck
        deck = new ArrayList<Card>(e.getCards());
    }

    // Constructor to create a full deck of cards if makeDeck is true
    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        if(makeDeck){
            // Iterate through all suits
            for(Suit suit : Suit.values()){
                // Iterate through all ranks
                for(Rank rank : Rank.values()){
                    // Add a new card with the current suit and rank to the deck
                    deck.add(new Card(suit, rank));
                }
            }
        }
    }

    // Method to add a single card to the deck
    public void addCard(Card card){
        deck.add(card);
    }

    // Method to add multiple cards to the deck
    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }

    // Method to provide a string representation of the deck
    public String toString(){
        // String to accumulate the output representation of the deck
        String output = "";

        // Append each card in the deck to the output string
        for(Card card: deck){
            output += card;
            output += "\n";
        }
        return output;
    }

    // Method to shuffle the deck using a random number generator
    public void shuffle(){
        Collections.shuffle(deck, new Random());
    }

    // Method to take (remove and return) the top card from the deck
    public Card takeCard(){
        // Create a copy of the top card in the deck
        Card cardToTake = new Card(deck.get(0));
        // Remove the top card from the deck
        deck.remove(0);
        // Return the removed card
        return cardToTake;
    }

    // Method to check if there are any cards left in the deck
    public boolean hasCards(){
        return deck.size() > 0; // Return true if deck size is greater than 0
    }

    // Method to get the number of cards remaining in the deck
    public int cardsLeft(){
        return deck.size(); // Return the size of the deck
    }

    // Method to get the list of cards in the deck
    public ArrayList<Card> getCards() {
        return deck; // Return the ArrayList of cards
    }

    // Method to empty the deck (remove all cards)
    public void emptyDeck(){
        deck.clear(); // Clear all cards from the deck
    }

    // Method to reload the deck from a discard pile, shuffle the deck, and empty the discard pile
    public void reloadDeckFromDiscard(Deck discard){
        // Add all cards from the discard pile to the current deck
        this.addCards(discard.getCards());
        // Shuffle the newly reloaded deck
        this.shuffle();
        // Empty the discard pile
        discard.emptyDeck();
        // Print a message indicating the deck was reloaded and shuffled
        System.out.println("Deck reloaded and shuffled.");
    }
}
