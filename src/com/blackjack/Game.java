package com.blackjack;

public class Game {

    // Declare variables needed for Game class
    private Deck deck, discarded;
    private Dealer dealer;
    private Player player;
    private int wins, losses, ties;

    public Game() {
        // Create a new deck with 52 cards
        deck = new Deck(true);
        // Create a new empty deck
        discarded = new Deck();
        // Create the People
        dealer = new Dealer();
        player = new Player();
        // Shuffle the deck and start the first round
        deck.shuffle();
        startRound();
    }

    private void startRound() {
        // Check if player has sufficient balance to continue
        if (player.getBalance() <= 0) {
            System.out.println("Game over! You have no money left.");
            System.exit(0);
        }

        // If this isn't the first time, display the user's score and put their cards back in the deck
        if (wins > 0 || losses > 0 || ties > 0) {
            System.out.println();
            System.out.println("Starting Next Round... Wins: " + wins + " Losses: " + losses + " Ties: " + ties);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }

        // Check to make sure the deck has at least 4 cards left
        if (deck.cardsLeft() < 4) {
            deck.reloadDeckFromDiscard(discarded);
        }

        // Give the dealer two cards
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        // Give the player two cards
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        // Show the dealer's hand with one card face down
        dealer.printFirstHand();

        // Show the player's hand
        player.printHand();

        // Check if dealer has Blackjack to start
        if (dealer.hasBlackjack()) {
            // Show the dealer has Blackjack
            dealer.printHand();

            // Check if the player also has Blackjack
            if (player.hasBlackjack()) {
                // End the round with a tie
                System.out.println("You both have 21 - Tie.");
                ties++;
                player.adjustBalance(false); // No win or loss for tie
                startRound();
            } else {
                System.out.println("Dealer has Blackjack. You lose.");
                dealer.printHand();
                losses++;
                player.adjustBalance(false); // Player loses
                startRound();
            }
        }

        // Check if player has Blackjack to start
        if (player.hasBlackjack()) {
            System.out.println("You have Blackjack! You win!");
            wins++;
            player.adjustBalance(true); // Player wins
            startRound();
        }

        // Let the player decide what to do next
        player.makeDecision(deck, discarded);

        // Check if they busted
        if (player.getHand().calculatedValue() > 21) {
            System.out.println("You have gone over 21.");
            losses++;
            player.adjustBalance(false); // Player loses
            startRound();
        }

        // Now it's the dealer's turn
        dealer.printHand();
        while (dealer.getHand().calculatedValue() < 17) {
            dealer.hit(deck, discarded);
        }

        // Check who wins
        if (dealer.getHand().calculatedValue() > 21) {
            System.out.println("Dealer busts");
            wins++;
            player.adjustBalance(true); // Player wins
        } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
            System.out.println("You lose.");
            losses++;
            player.adjustBalance(false); // Player loses
        } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
            System.out.println("You win.");
            wins++;
            player.adjustBalance(true); // Player wins
        } else {
            System.out.println("Tie.");
            ties++;
            player.adjustBalance(false); // No win or loss for tie
        }

        // Start a new round
        startRound();
    }

    public static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
