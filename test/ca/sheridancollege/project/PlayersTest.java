/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cunni
 */
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
