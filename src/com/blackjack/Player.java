package com.blackjack;

import java.util.Scanner;

public class Player extends Person {

    // Scanner object to read user input
    private Scanner input = new Scanner(System.in);
    // Player's balance
    private int balance;

    // Constructor for creating a new Player
    public Player() {
        // Prompt the user to enter their name
        System.out.println("Enter your name: ");
        String playerName = input.nextLine(); // Read the player's input for their name
        super.setName(playerName); // Set the player's name using the superclass method
        this.balance = 20; // Initialize the player's balance
    }

    // Method to get the player's current balance
    public int getBalance() {
        return balance;
    }

    // Method to update the player's balance
    public void updateBalance(int amount) {
        balance += amount;
    }

    // Method to allow the player to make a decision during their turn
    public void makeDecision(Deck deck, Deck discard) {
        if (balance <= 0) {
            System.out.println("You are out of money! Game over.");
            System.exit(0);
        }

        // Deduct bet amount
        updateBalance(-5);

        int decision = 0; // Variable to store the player's decision
        boolean getNum = true; // Flag to control the input loop

        while (getNum) {
            try {
                // Prompt the player to choose between hitting or standing
                System.out.println("Would you like to: 1) Hit or 2) Stand");
                decision = input.nextInt(); // Read the player's input
                getNum = false; // Exit the loop if input is valid
            } catch (Exception e) {
                // Handle invalid input (non-integer) and prompt the player again
                System.out.println("Invalid input. Please enter 1 or 2.");
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

    // Method to update the player's balance based on the outcome
    public void adjustBalance(boolean playerWon) {
        if (playerWon) {
            updateBalance(10); // Increase balance by $10 if player wins
            System.out.println("You win! Your balance is now $" + getBalance());
        } else {
            System.out.println("You lose. Your balance is now $" + getBalance());
        }
    }
}
