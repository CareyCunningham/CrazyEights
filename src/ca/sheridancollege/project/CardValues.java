/* CardValues.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-19
 * purpose: Enum class for defining the values of cards available, their abbreviation and scoring values
 */
package ca.sheridancollege.project;

public enum CardValues {
    ACE(   0, "A",  1),
    TWO(   1, "2",  2),
    THREE( 2, "3",  3),
    FOUR(  3, "4",  4),
    FIVE(  4, "5",  5),
    SIX(   5, "6",  6),
    SEVEN( 6, "7",  6),
    EIGHT( 7, "8", 50),
    NINE(  8, "9",  9),
    TEN(   9, "10",10),
    JACK( 10, "J", 10),
    QUEEN(11, "Q", 10),
    KING( 12, "K", 10);

    private int    cardIndex;
    private String cardAbbreviation;
    private int    cardScoringValue;

    // constructor
    CardValues(int cardIndex, String cardAbbreviation, int cardScoringValue) {
        this.cardIndex = cardIndex;
        this.cardAbbreviation = cardAbbreviation;
        this.cardScoringValue = cardScoringValue;
    }

    // getters
    public int getCardIndex() {
        return this.cardIndex;
    }

    public String getCardAbbreviation() {
        return this.cardAbbreviation;
    }

    public int getCardScoringValue() {
        return this.cardScoringValue;
    }
}
