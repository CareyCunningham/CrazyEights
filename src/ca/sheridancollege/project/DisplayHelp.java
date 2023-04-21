/* DisplayHelp.java
 * class:   SYST17796 - Final Project - Crazy Eights
 * name:    Carey Cunningham & Bruno Silva
 * date:    2023-04-19
 * purpose: Enum class for displaying help in the game and displaying version/name info
 */
package ca.sheridancollege.project;

public enum DisplayHelp {
    TITLE       { @Override public String helpMessage(){ return "CRAZY EIGHTS"; } },
    AUTHORS     { @Override public String helpMessage(){ return "by: Carey Cunningham & Bruno Silva"; } },
    DATE        { @Override public String helpMessage(){ return "date: 2023-04-20"; } },
    VERSION     { @Override public String helpMessage(){ return "version: 1.3"; } },
    WINNING     { @Override public String helpMessage(){ return "  A player must gain a value of 200 points to become the victor."; } },
    OBJECTIVE   { @Override public String helpMessage(){ return "  Each round, the objective is to use up all your cards before your opponents.\n" +
                                                                "  Play rounds until one player reaches 200 points and wins the game."; } },
    ON_YOUR_TURN{ @Override public String helpMessage(){ return "  Your turn continues until you either play a card or pass because the draw deck is empty.\n" + 
                                                                "  Options:\n" +
                                                                "   - play a wild card (8) to change the active suit\n" +
                                                                "   - play a matching number OR suit to the previously played card\n" +
                                                                "   - draw (get a new card), unless the draw deck is empty\n" +
                                                                "   - pass (only an option if the draw deck is empty)\n"; } },
    SCORING     { @Override public String helpMessage(){ return "  When all players have passed, or one player runs out of cards, the player with the least amount\n" +
                                                                "  of points in their hand wins the difference between the points in their hand and the sum of all\n" + 
                                                                "  points in other players hands.\n" +
                                                                "  Card Point Breakdown:" +
                                                                "   - A (Ace): 1 point\n" +
                                                                "   - 2/3/4/5/6/7/9: face value points\n" +
                                                                "   - 8 (Wild): 50 points\n" +
                                                                "   - 10/J/Q/K: 10 points"; } };
    
    public abstract String helpMessage();
    
    // display just the headers (start of game)
    public static String getHelpHeader(){
        return TITLE.helpMessage() + "\n" + AUTHORS.helpMessage() + "\n" + DATE.helpMessage() + "\n" + VERSION.helpMessage() + "\n";
    }

    // display the full help (when thy hit H)
    public static String getHelpFull(){
        return getHelpHeader() + "Objective:\n" + OBJECTIVE.helpMessage() + "On Your Turn:\n" + ON_YOUR_TURN.helpMessage() + "Scoring:\n" + SCORING.helpMessage();
    }
}
