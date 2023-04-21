/* DisplayHelpTest.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-19
 * purpose: JUnit testing some of the methods
 */
package ca.sheridancollege.project;

import org.junit.Test;
import static org.junit.Assert.*;

public class DisplayHelpTest {

    @Test
    public void testHelpMessageGood() {
        System.out.println("helpMessage - GOOD");
        String expResult = "by: Carey Cunningham & Bruno Silva";
        String result = DisplayHelp.AUTHORS.helpMessage();
        assertEquals(expResult, result);
    }

    @Test
    public void testHelpMessageBad() {
        System.out.println("helpMessage - BAD");
        String expResult = "version: 1.4";
        String result = DisplayHelp.VERSION.helpMessage();
        assertNotEquals(expResult, result);
    }

    @Test
    public void testHelpMessageBoundary() { // first enum
        System.out.println("helpMessage - BOUNDARY");
        String expResult = "CRAZY EIGHTS";
        String result = DisplayHelp.TITLE.helpMessage();
        assertEquals(expResult, result);
    }
}
