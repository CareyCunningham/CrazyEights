/* PlayersTest.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-21
 * purpose: JUnit testing some of the methods
 */
package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayersTest {
    
    /**
     * Tests of validatePlayerCount method, of class Players.
     */
    @Test
    public void testValidatePlayerCountGood() {
        System.out.println("validatePlayerCount - GOOD");
        String playerCountString = "4";
        Players instance = new Players("TEST");
        boolean expResult = true;
        boolean result = instance.validatePlayerCount(playerCountString);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidatePlayerCountBad() {
        System.out.println("validatePlayerCount BAD");
        String playerCountString = "6";
        Players instance = new Players("TEST");
        boolean expResult = false;
        boolean result = instance.validatePlayerCount(playerCountString);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidatePlayerCountBoundaryLow() {
        System.out.println("validatePlayerCount");
        String playerCountString = "2";
        Players instance = new Players("TEST");
        boolean expResult = true;
        boolean result = instance.validatePlayerCount(playerCountString);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidatePlayerCountBoundaryHigh() {
        System.out.println("validatePlayerCount");
        String playerCountString = "5";
        Players instance = new Players("TEST");
        boolean expResult = true;
        boolean result = instance.validatePlayerCount(playerCountString);
        assertEquals(expResult, result);
    }

    /**
     * Test of validatePlayerName method, of class Players.
     */
    @Test
    public void testValidatePlayerNameGood() {
        System.out.println("validatePlayerName GOOD");
        String playerName = "Tom";
        Players instance = new Players("TEST");
        boolean expResult = true;
        boolean result = instance.validatePlayerName(playerName);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidatePlayerNameBad() {
        System.out.println("validatePlayerName BAD");
        String playerName = "TomTomTomTomTomTomTomTom";
        Players instance = new Players("TEST");
        boolean expResult = false;
        boolean result = instance.validatePlayerName(playerName);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidatePlayerNameBoundaryLow() {
        System.out.println("validatePlayerName Boundary Low");
        String playerName = "T";
        Players instance = new Players("TEST");
        boolean expResult = true;
        boolean result = instance.validatePlayerName(playerName);
        assertEquals(expResult, result);
    }
    @Test
    public void testValidatePlayerNameBoundaryHigh() {
        System.out.println("validatePlayerName Boundary High");
        String playerName = "CareyBrunoCareyBruno";
        Players instance = new Players("TEST");
        boolean expResult = true;
        boolean result = instance.validatePlayerName(playerName);
        assertEquals(expResult, result);
    }
    
}
