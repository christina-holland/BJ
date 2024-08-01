package com.blackjack;

public abstract class Person {

    // Represents the hand of cards that this person holds
    private Hand hand;
    // Represents the name of this person
    private String name;

    // Default constructor initializes a new hand and an empty name
    public Person() {
        this.hand = new Hand(); // Initialize the person's hand as a new, empty hand
        this.name = ""; // Set the person's name to an empty string
    }

    // Getter for the hand of cards
    public Hand getHand() {
        return this.hand; // Return the current hand
    }

    // Setter for the hand of cards
    public void setHand(Hand hand) {
        this.hand = hand; // Set the hand to the provided Hand object
    }

    // Getter for the name of the person
    public String getName() {
        return this.name; // Return the current name
    }

    // Setter for the name of the person
    public void setName(String name) {
        this.name = name; // Set the name to the provided string
    }

    // Method to print the person's hand and its value
    public void printHand() {
        // Print the name and details of the person's hand
        System.out.println(this.name + "'s hand is:");
        System.out.println(this.hand + " value: " + this.hand.calculatedValue());
    }

    // Method for the person to take a card from the deck
    public void hit(Deck deck, Deck discard) {
        // Check if there are any cards left in the deck
        if (!deck.hasCards()) {
            // If no cards are left, reload the deck from the discard pile and shuffle
            deck.reloadDeckFromDiscard(discard);
        }
        // Take a card from the deck and add it to the person's hand
        this.hand.takeCardFromDeck(deck);
        // Inform the console that the person has received a card
        System.out.println(this.name + " gets a card");
        // Print the updated hand of the person
        this.printHand();
        // Pause the game to allow the player to see the update
        Game.pause();
    }

    // Method to check if the person has a Blackjack (a hand valued at 21)
    public boolean hasBlackjack() {
        // Check if the calculated value of the hand is 21
        return this.getHand().calculatedValue() == 21;
    }
}
