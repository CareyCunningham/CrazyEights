/* CardSuits.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-19
 * purpose: Enum class for defining the suits available in the game as well as their abbreviations
 */
package ca.sheridancollege.project;

public enum CardSuits {
    HEART(  0, "H"),
    SPADE(  1, "S"),
    CLUB(   2, "C"),
    DIAMOND(3, "D");

    private int    cardIndex;
    private String cardAbbreviation;

    // constructor
    CardSuits(int cardIndex, String abbreviation){
        this.cardIndex        = cardIndex;
        this.cardAbbreviation = abbreviation;
    }
    // getter
    public int    getCardIndex()        { return this.cardIndex;        }   
    public String getCardAbbreviation() { return this.cardAbbreviation; }   
}