/* Players.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-19
 * purpose: Define a class to initialize and store the Player ArrayList
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Players {

    private ArrayList<Player> players = new ArrayList<Player>();    // holds the player info
    private int playerCount;                                        // holds a count of the players playing
    private final int MAX_NAME_LENGTH = 20;                         // define the max length for a player name

    // constructor method to call the other private methods to define the players
    public Players() {
        setPlayerCount();
        setPlayerNames();
    }
    
    // used to make JUnit testing the validation method possible
    public Players(String name){
        // throw away input - just to be able to instantiate the class without using Scanner
    }

    // getters
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public int getPlayerCount() {
        return this.playerCount;
    }

    // #########################
    // # internal calculations #
    // #########################
    // method to get the amount of players playing the game
    private void setPlayerCount() {
        Scanner sc = new Scanner(System.in);
        String playerCountString = "";
        do {
            System.out.print("How many players will be playing today? [2-5]: ");
            while (sc.hasNextLine()) {
                playerCountString = sc.nextLine().trim();
            }
        } while (!validatePlayerCount(playerCountString));
        this.playerCount = Integer.parseInt(playerCountString);
    }

    // validate input for the number of players in the game (2-5 allowed)
    public boolean validatePlayerCount(String playerCountString) {
        if (playerCountString.matches("^[2-5]$")) {
            return true;
        } else {
            System.out.print("Invalid input: ");
            return false;
        }
    }

    // assign the player names and initialize their scores
    private void setPlayerNames() {
        Scanner sc = new Scanner(System.in);
        String playerName = "";
        for (int i = 0; i < this.playerCount; i++) {
            do {
                System.out.print("What is the name of player " + (i + 1) + " (max 20 characters)?: ");
                while (sc.hasNextLine()) {
                    playerName = sc.nextLine();
                }
            } while (!validatePlayerName(playerName));
            Player newPlayer = new Player(playerName);
            players.add(newPlayer);
        }
    }

    // validate the names (not blank and max length defined in the constant in this class header)
    public boolean validatePlayerName(String playerName) {
        if (!playerName.matches("^$") && playerName.length() <= MAX_NAME_LENGTH) {
            return true;
        } else {
            System.out.print("Invalid input: ");
            return false;
        }
    }
}
