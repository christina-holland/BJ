package com.blackjack;

public class Dealer extends Person {

    // Constructor for creating a new Dealer
    public Dealer() {
        // Set the name of the dealer to "Dealer" using the superclass method
        super.setName("Dealer");
    }

    // Method to print the dealer's hand with the second card face down
    public void printFirstHand() {
        // Print a message indicating that the dealer's hand is being displayed
        System.out.println("The dealer's hand is:");
        // Print the first card in the dealer's hand
        System.out.println(super.getHand().getCard(0));
        // Indicate that the second card is not being shown
        System.out.println("The second card is face down.");
    }
}
