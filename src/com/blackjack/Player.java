package com.blackjack;

import java.util.Scanner;

public class Player extends Person {

    // Scanner object to read user input
    private Scanner input = new Scanner(System.in);

    // Constructor for creating a new Player
    public Player() {
        // Prompt the user to enter their name
        System.out.println("Enter your name: ");
        String playerName = input.nextLine(); // Read the player's input for their name
        super.setName(playerName); // Set the player's name using the superclass method
    }

    // Method to allow the player to make a decision during their turn
    public void makeDecision(Deck deck, Deck discard) {

        int decision = 0; // Variable to store the player's decision
        boolean validInput = false; // Flag to control the input loop

        while (!validInput) {
            try {
                // Prompt the player to choose between hitting, standing, or exiting
                System.out.println("Make a choice: 1) Hit, 2) Stand, or 0) Exit the game");
                decision = input.nextInt(); // Read the player's input

                // Validate the input
                if (decision == 0) {
                    System.out.println("Exiting the game.");
                    System.exit(0); // Exit the program
                } else if (decision == 1 || decision == 2) {
                    validInput = true; // Exit the loop if input is valid
                } else {
                    System.out.println("Invalid input. Please enter 1, 2, or 0.");
                }
            } catch (Exception e) {
                // Handle invalid input (non-integer) and prompt the player again
                System.out.println("Invalid input. Please enter 1, 2, or 0.");
                input.next(); // Clear the invalid input from the scanner
            }
        }

        // Process the player's decision
        if (decision == 1) {
            // If the player chooses to hit, draw a card and add it to their hand
            this.hit(deck, discard);
            // If the hand's value is greater than 20, return
            if (this.getHand().calculatedValue() > 20) {
                return;
            } else {
                // Otherwise, prompt the player to make another decision
                this.makeDecision(deck, discard);
            }
        } else {
            // If the player chooses to stand, inform them of their choice
            System.out.println("You stand.");
        }
    }
}
