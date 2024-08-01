package com.blackjack;

public class Game {

    // Variables needed for managing the game state
    private Deck deck;           // The deck of cards used for the game
    private Deck discarded;     // The deck where discarded cards are placed

    private Dealer dealer;      // The dealer of the game
    private Player player;      // The player participating in the game
    private int wins;           // Number of wins by the player
    private int losses;         // Number of losses by the player
    private int ties;         // Number of ties in the game

    // Default constructor initializes a new game
    public Game(){
        // Create a new deck with 52 cards
        deck = new Deck(true);
        // Create a new empty deck for discarded cards
        discarded = new Deck();

        // Initialize the dealer and player
        dealer = new Dealer();
        player = new Player();

        // Shuffle the deck to start the game
        deck.shuffle();
        // Begin the first round of the game
        startRound();
    }

    // Method to start a new round of the game
    private void startRound(){

        // If it's not the first round, display scores and return cards to the discarded deck
        if(wins > 0 || losses > 0 || ties > 0){
            System.out.println();
            System.out.println("Starting Next Round.");
            System.out.println("Wins: " + wins + " Losses: " + losses + " Ties: " + ties);
            // Move all cards from dealer's and player's hands to the discarded deck
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }

        // Ensure there are at least 4 cards left in the deck; reload from discarded if needed
        if(deck.cardsLeft() < 4){
            deck.reloadDeckFromDiscard(discarded);
        }

        // Deal two cards to the dealer
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        // Deal two cards to the player
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        // Display the dealer's hand with one card face down
        dealer.printFirstHand();

        // Display the player's hand
        player.printHand();

        // Check if the dealer has Blackjack
        if(dealer.hasBlackjack()){
            // Show the dealer's hand if they have Blackjack
            dealer.printHand();

            // Check if the player also has Blackjack
            if(player.hasBlackjack()){
                // Both have Blackjack, so it's a tie
                System.out.println("You both have 21 - you tie");
                ties++;
                startRound(); // Start a new round
            }
            else{
                // Dealer has Blackjack, player loses
                System.out.println("Dealer has BlackJack - you lose");
                dealer.printHand();
                losses++;
                startRound(); // Start a new round
            }
        }

        // If we reach this point, the dealer did not have Blackjack
        // Check if the player has Blackjack
        if(player.hasBlackjack()){
            // Player has Blackjack and wins
            System.out.println("You have Blackjack - you win");
            wins++;
            startRound(); // Start a new round
        }

        // Allow the player to make a decision (hit or stand)
        player.makeDecision(deck, discarded);

        // Check if the player has busted (gone over 21)
        if(player.getHand().calculatedValue() > 21){
            System.out.println("You have gone over 21.");
            losses++;
            startRound(); // Start a new round
        }

        // Dealer's turn to play
        dealer.printHand();
        // Dealer hits until they reach at least 17
        while(dealer.getHand().calculatedValue() < 17){
            dealer.hit(deck, discarded);
        }

        // Determine the outcome of the round
        if(dealer.getHand().calculatedValue() > 21){
            // Dealer busts, player wins
            System.out.println("Dealer busts");
            wins++;
        }
        else if(dealer.getHand().calculatedValue() > player.getHand().calculatedValue()){
            // Dealer's hand is higher, player loses
            System.out.println("You lose");
            losses++;
        }
        else if(player.getHand().calculatedValue() > dealer.getHand().calculatedValue()){
            // Player's hand is higher, player wins
            System.out.println("You win");
            wins++;
        }
        else{
            // Hands are equal, it's a tie
            System.out.println("Tie");
            ties++;
        }

        // Start a new round
        startRound();
    }

    // Utility method to pause the game for 2 seconds
    public static void pause(){
        try {
            Thread.sleep(2000); // Pause for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace(); // Print stack trace if interrupted
        }
    }
}
