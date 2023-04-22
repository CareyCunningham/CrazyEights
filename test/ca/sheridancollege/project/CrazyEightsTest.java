/* CrazyEightsTest.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-19
 * purpose: JUnit testing some of the methods
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class CrazyEightsTest {

    /**
     * Tests of validateChangeSuit method, of class CrazyEights.
     */
    @Test
    public void testValidateChangeSuitGood() {
        System.out.println("validateChangeSuit - Good");
        String newSuit = "H";
        boolean expResult = true;
        boolean result = CrazyEights.validateChangeSuit(newSuit);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidateChangeSuitBad() {
        System.out.println("validateChangeSuit - Bad");
        String newSuit = "Q";
        boolean expResult = false;
        boolean result = CrazyEights.validateChangeSuit(newSuit);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidateChangeSuitBoundary() { // chose the last suit option
        System.out.println("validateChangeSuit - Boundary");
        String newSuit = "S";
        boolean expResult = true;
        boolean result = CrazyEights.validateChangeSuit(newSuit);
        assertEquals(expResult, result);
    }

    /**
     * Tests of findCard method, of class CrazyEights.
     */
    @Test
    public void testFindCardGood() {
        System.out.println("findCard - Good");
        String cardToFind = "AD";
        ArrayList<String> cardList = new ArrayList<>(Arrays.asList("2S", "QH", "AD", "2C", "3S"));
        int expResult = 2;
        int result = CrazyEights.findCard(cardToFind, cardList);
        assertEquals(expResult, result);
    }
    @Test
    public void testFindCardBad() {
        System.out.println("findCard - Bad");
        String cardToFind = "AD";
        ArrayList<String> cardList = new ArrayList<>(Arrays.asList("2S", "QH"));
        int expResult = -1;
        int result = CrazyEights.findCard(cardToFind, cardList);
        assertEquals(expResult, result);
    }
    @Test // end of list check
    public void testFindCardBoundary() {
        System.out.println("findCard - Boundary");
        String cardToFind = "AD";
        ArrayList<String> cardList = new ArrayList<>(Arrays.asList("2S", "QH", "AD"));
        int expResult = 2;
        int result = CrazyEights.findCard(cardToFind, cardList);
        assertEquals(expResult, result);
    }
}
