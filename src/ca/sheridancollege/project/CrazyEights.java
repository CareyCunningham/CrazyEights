/* CrazyEights.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-19
 * purpose: Main class for running the Crazy Eights game 
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CrazyEights {

    private static final int CARDS_PER_PLAYER       =   5;
    private static final int POINTS_REQUIRED_TO_WIN = 200;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<String> draw    = new ArrayList<String>();   // defines the draw pile
        ArrayList<String> discard = new ArrayList<String>();   // defines the discard pile
        boolean gameOverFlag      = false;
        boolean quitFlag          = false;
        displayHelpHeader();
        do {
            Players players = new Players();   // custom class to define the player names, scores and hands
            do {          // play a round
                shuffleDrawPile(draw);
                dealHands(draw, discard, players);
                int currentPlayer = 0;
                boolean roundOverFlag = false;
                do {          // play a hand
                    printCurrentPlayer(draw, discard, players, currentPlayer);
                    quitFlag = getInputFromPlayer(draw, discard, players, currentPlayer);
                    currentPlayer = switchPlayer(currentPlayer, players);                   // switch to the next player
                    if (draw.isEmpty() && allPassed(players) || quitFlag) {
                        roundOverFlag = true;
                    }
                } while (!roundOverFlag); // end of hand
                if (quitFlag) {
                    gameOverFlag = true;
                } else {
                    gameOverFlag = scoreRound(players);
                }
            } while (!gameOverFlag); // end a round
        } while (getUserPlayAgain());
    }

    public static void displayHelpHeader() {
        System.out.print(DisplayHelp.getHelpHeader());
    }

    public static void displayHelp() {
        System.out.print(DisplayHelp.getHelpFull());
    }

    // clear the draw pile, initialize the draw pile using the enums for suit and values, then shuffle the deck
    public static void shuffleDrawPile(ArrayList<String> draw) {
        draw.removeAll(draw); // clear the array list for rounds 2+
        for (CardSuits suit : CardSuits.values()) {
            for (CardValues value : CardValues.values()) {
                draw.add(0, value.getCardAbbreviation() + suit.getCardAbbreviation());
            }
        }
        Collections.shuffle(draw);                                         // randomize the order of the arraylist
    }

    // declare a winner if anyone has reached the target score, then output all scores
    public static void declareWinner(Players players, int winnerIndex) {
        System.out.println("Player " + (winnerIndex + 1) + "(" + players.getPlayers().get(winnerIndex).getName() + ") won the game");
        for (int i = 0; i < players.getPlayerCount(); i++) {
            System.out.printf("Player %d: %-20s Score: %3d\n", i + 1, players.getPlayers().get(i).getName(), players.getPlayers().get(i).getScore());
        }
        System.out.println("");
    }

    // move a card from one pile to another
    public static void playCard(ArrayList<String> from, ArrayList<String> to, int fromIndex) {
        to.add(from.get(fromIndex));
        from.remove(fromIndex);
    }

    // clear the discard pile and player card piles
    // initialize the card deck in draw
    // deal cards to each player
    // flip the top remaining card to start the discard pile
    public static void dealHands(ArrayList<String> draw, ArrayList<String> discard, Players players) {
        discard.removeAll(discard);                                                                    // clear the discard pile
        for (int i = 0; i < players.getPlayerCount(); i++) {                                                  // for each player
            players.getPlayers().get(i).getCards().removeAll(players.getPlayers().get(i).getCards());    // clear the hands
        }
        // deal the first 5 cards to each player
        for (int card = 0; card < CARDS_PER_PLAYER; card++) {                              // for each card a player should receive
            for (int i = 0; i < players.getPlayerCount(); i++) {              // for each player
                playCard(draw, players.getPlayers().get(i).getCards(), 0);       // draw a card from the draw pile and give it to the player
            }
        }
        playCard(draw, discard, 0);                   // flip the top draw card to form the discard pile
    }

    // switch player - increment the player, if out of bounds reset to first
    private static int switchPlayer(int currentPlayer, Players players) {
        currentPlayer++;
        if (currentPlayer >= players.getPlayers().size()) {
            currentPlayer = 0;
        }
        return currentPlayer;
    }

    // print messages for current user updating them on the status of the game
    public static void printCurrentPlayer(ArrayList<String> draw, ArrayList<String> discard, Players players, int currentPlayer) {
        System.out.println("\n\nPlayer " + (currentPlayer + 1) + " (" + players.getPlayers().get(currentPlayer).getName() + ")'s turn. Current standings");
        for (int i = 0; i < players.getPlayerCount(); i++) {
            System.out.printf(" - Player %d: %-20s Cards Remaining: %2d Score: %3d\n", i + 1, players.getPlayers().get(i).getName(), players.getPlayers().get(i).getCards().size(), players.getPlayers().get(i).getScore());
        }
        System.out.printf(" - The draw pile currently has %d card(s) left in it.\n", draw.size());
        System.out.printf(" - The current active card is %s\n", discard.get(discard.size() - 1));
        System.out.printf(" - Your hand contains: %s\n", players.getPlayers().get(currentPlayer).getCards());
    }

    // checks if all players have passed, returns true if all have, otherwise returns false
    public static boolean allPassed(Players players) {
        boolean allPassedFlag = true;
        for (int i = 0; i < players.getPlayerCount(); i++) {
            if (players.getPlayers().get(i).getPassed() == false) {
                allPassedFlag = false;
            }
        }
        return allPassedFlag;
    }

    // get user input on play options
    //   return true means a user quit the current round
    public static boolean getInputFromPlayer(ArrayList<String> draw, ArrayList<String> discard, Players players, int currentPlayer) {
        String response = "";
        do {
            String options = "CARD/h=HELP/q=QUIT/";
            if (draw.isEmpty()) {
                options += "p=PASS";
            } else {
                options += "d=DRAW";
            }  // add the last option based on cards left in draw deck
            System.out.print(" - Please enter your choice - " + options + ": ");
            response = sc.nextLine().trim().toUpperCase();
        } while (!(validateInputFromPlayer(response, draw, discard, players, currentPlayer) || response.equals("Q"))); // repeat until a valid entry is made
        if (response.equals("Q")) {
            return true;
        } else {
            return false;
        }
    }

    // decide what to do with the input provided and act on it
    //   if the user draws they need to go again
    //   if the user plays a card, all userPassed variables are cleared
    public static boolean validateInputFromPlayer(String response, ArrayList<String> draw, ArrayList<String> discard, Players players, int currentPlayer) {
        boolean moveToNextPlayerFlag = true;
        if (response.equals("D") && ! draw.isEmpty()) {                                     // draw
            playCard(draw, players.getPlayers().get(currentPlayer).getCards(), 0);
            printCurrentPlayer(draw, discard, players, currentPlayer);
            moveToNextPlayerFlag = false;
        } else if (response.equals("P") && ! draw.isEmpty()) {                               // pass
            players.getPlayers().get(currentPlayer).setPassed(true);
        } else if (response.equals("Q")) {                                                   // quit
            System.out.println("Player one quit - the game is over");
        } else if (response.equals("H")) {                                                   // help
            System.out.print(DisplayHelp.getHelpFull());
            printCurrentPlayer(draw, discard, players, currentPlayer);
            moveToNextPlayerFlag = false;
        } else {                                                                                     // other
            int indexOfCard = findCard(response, players.getPlayers().get(currentPlayer).getCards());        // find the index that matches the card choice (-1 for fail)
            if (indexOfCard >= 0) {                                                                           // if card found in hand
                String topDiscardSuit = discard.get(discard.size() - 1).substring(discard.get(0).length() - 1);
                String topDiscardValue = discard.get(discard.size() - 1).substring(0, discard.get(0).length() - 1);
                String card = players.getPlayers().get(currentPlayer).getCards().get(indexOfCard);
                String cardSuit = card.substring(card.length() - 1);
                String cardValue = card.substring(0, card.length() - 1);
                if (cardValue.equals("8")) {                                  // here if wild card (8) played
                    changeSuit(currentPlayer, players, discard, indexOfCard);
                } else if (topDiscardSuit.equals(cardSuit) || topDiscardValue.equals(cardValue)) {       // here if a valid non-8 was played
                    playCard(players.getPlayers().get(currentPlayer).getCards(), discard, indexOfCard);
                    if (players.getPlayers().get(currentPlayer).getCards().isEmpty()) {
                        endRound(draw, players);
                    } else {
                        clearPassFlags(players);
                    }
                }
            } else {
                moveToNextPlayerFlag = false;
                System.out.print("Invalid entry, please try again. ");
            }
        }
        return moveToNextPlayerFlag;
    }

    // change the suit if the user plays an 8 (wild card)
    public static void changeSuit(int currentPlayer, Players players, ArrayList<String> discard, int indexOfCard) {
        String newSuit = "";
        players.getPlayers().get(currentPlayer).getCards().remove(indexOfCard);
        do {
            System.out.print("8 card played, please enter the new suit (H/D/C/S): ");
            newSuit = sc.nextLine().trim().toUpperCase();
        } while (!validateChangeSuit(newSuit));
        discard.add("8" + newSuit);
    }

    // validate the suit to change to (return false unless valid)
    public static boolean validateChangeSuit(String newSuit) {
        if (newSuit.matches("^[HDCS]$")) {
            return true;
        } else {
            System.out.print("Invalid suit chosen, please try again. ");
            return false;
        }
    }

    // set the flags to denote end of a round
    public static void endRound(ArrayList<String> draw, Players players) {
        draw.removeAll(draw);
        for (int i = 0; i < players.getPlayerCount(); i++) {
            players.getPlayers().get(i).setPassed(true);
        }
    }

    // clear pass flags because someone played a card
    public static void clearPassFlags(Players players) {
        for (int i = 0; i < players.getPlayerCount(); i++) {
            players.getPlayers().get(i).setPassed(false);
        }
    }

    // look for the card entered within the users cards (return the index or -1 for failure to find it)
    public static int findCard(String cardToFind, ArrayList<String> cardList) {
        int index = -1;  // -1 means not found
        for (int i = 0; i < cardList.size(); i++) {
            if (cardToFind.equals(cardList.get(i))) {
                index = i;
            }
        }
        return index;
    }

    // ask the user if they wish to play again
    public static boolean getUserPlayAgain() {
        String response = "";
        do {
            System.out.print("Would you like to play again (y/n): ");
            response = sc.nextLine().trim().toUpperCase();
        } while (!validateGetUserPlayAgain(response));
        if (response.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    // validate the user input, repeat until valid input received
    public static boolean validateGetUserPlayAgain(String response) {
        if (response.matches("^[YN]$")) {
            return true;
        } else {
            System.out.print("Invalid entry, please try again. ");
            return false;
        }
    }

    // method to tally points
    private static boolean scoreRound(Players players) {
        ArrayList<Integer> roundScores = new ArrayList<Integer>();
        int roundWinner = 0;
        for (int i = 0; i < players.getPlayerCount(); i++) {
            int score = 0;
            for (int j = 0; j < players.getPlayers().get(i).getCards().size(); j++) {
                String value = players.getPlayers().get(i).getCards().get(j).substring(0, players.getPlayers().get(i).getCards().get(j).length() - 1);
                if (value.matches("8")) {
                    score += 50;
                } else if (value.matches("A")) {
                    score += 1;
                } else if (value.matches("[JQK]")) {
                    score += 10;
                } else {
                    score += Integer.parseInt(value);
                }
            }
            roundScores.add(i, score);        // initialize the arrayList element to 0
        }

        // find the lowest score
        for (int i = 1; i < roundScores.size(); i++) {
            if (roundScores.get(roundWinner) > roundScores.get(i)) {
                roundWinner = i;
            }
        }

        // if lowest subtract from the others, otherwise add to the score
        int score = 0;
        for (int i = 0; i < roundScores.size(); i++) {
            if (i == roundWinner) {
                score -= roundScores.get(i);
            } else {
                score += roundScores.get(i);
            }
        }

        System.out.println("Player " + (roundWinner + 1) + "(" + players.getPlayers().get(roundWinner).getName() + ") won this round and was rewarded " + score + " points");
        players.getPlayers().get(roundWinner).setScore(score + players.getPlayers().get(roundWinner).getScore());
        if (players.getPlayers().get(roundWinner).getScore() >= POINTS_REQUIRED_TO_WIN) {
            declareWinner(players, roundWinner);
            return true;
        } else {
            return false;
        }
    }
}
