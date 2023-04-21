/* Player.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-19
 * purpose: Define a class to store all player data in
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

public class Player {

    private String name;
    private int score;
    private ArrayList<String> cards = new ArrayList<String>();
    private boolean passed = false;                     // flag to see if the user passed this round

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // getters
    public String getName()               {return this.name;    }
    public int getScore()                 { return this.score;  }
    public ArrayList<String> getCards()   { return this.cards;  }
    public boolean getPassed()            { return this.passed; }

    // setters
    public void setName(String name)      { this.name = name;     }
    public void setScore(int score)       { this.score = score;   }
    public void setPassed(boolean passed) { this.passed = passed; }

    // modifiers
    public void addCard(String card) { this.cards.add(card);    }   // add a card to your hand
    public void remCard(String card) { this.cards.remove(card); }   // remove a card from your hand
}
