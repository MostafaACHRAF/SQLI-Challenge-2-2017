package com.sqli.test.romanrunner;



import static org.junit.Assert.assertEquals;

import com.sqli.test.romanrunner.entities.Charioteer;
import com.sqli.test.romanrunner.entities.Circenses;
import com.sqli.test.romanrunner.entities.Knight;
import com.sqli.test.romanrunner.entities.Player;
import org.junit.Test;

/**
 * 
 * Chariot racing (Latin: Ludi Circenses) was one of the most popular ancient
 * Roman sports. More : https://en.wikipedia.org/wiki/Chariot_racing
 * 
 * This exercise is not about a race or a competition, it is about a single
 * player in a Circenses, where a player tries to collect coins. The Circenses
 * is represented by a rectangle with a start line at the bottom, and a final
 * line at top. There are two tracks in the Circenses : the left tack and the
 * right track. A player start the race at the start line, and is represented by
 * the first letter of his name in capital letter.
 * 
 * We can find many objects in the Circensus tracks : 
 * o  : a coin 
 * _  : an obstacle
 * ## : the finish line
 * 
 * Example of a circus (See more in the comments of the exercise):
 *  |##|
 *  |_ |
 *  |o |
 *  | o|
 *  |o |
 *  |  |
 * 
 */
public class RomanRunnerTest {

    /**
     * We should be able to build the circenses using a CircensesBuilder. All
     * objects passed to the CircensesBuilder are by default added to the left
     * track. If there is no player in the circenses, thus the start line should
     * be empty.
     */
    @Test
    public void circensesBuilderTest() {
        Circenses circenses = new CircensesBuilder()
                .addCoin().addEmptySlot().addCoin().addObstacle()
                .build();

        String expectedDisplay = new StringBuilder()
                .append("|##|\n") // the final line
                .append("|_ |\n") // the obstacle
                .append("|o |\n") // the second coin
                .append("|  |\n") // empty slot
                .append("|o |\n") // the first coin
                .append("|  |") // the start line is empty as no player is registered
                .toString();

        assertEquals(expectedDisplay, circenses.draw());
    }

    /**
     * When a player start the game, he stands at the first line, left position.
     * The players are represented by the first letter of their name in capital.
     */
    @Test
    public void aPlayerCanStartTheGame() {
        Circenses circenses = new CircensesBuilder()
                .addCoin().addEmptySlot().addCoin().addObstacle()
                .build();

        Player player = new Charioteer("lucius");
        player.startGame(circenses);

        String expectedDisplay = new StringBuilder()
                .append("|##|\n") // the final line
                .append("|_ |\n") // the obstacle
                .append("|o |\n") // the second coin
                .append("|  |\n") // empty slot
                .append("|o |\n") // the first coin
                .append("|L |") // the Charioteer "Lucius" at the start line
                .toString();

        assertEquals(expectedDisplay, circenses.draw());
    }

    /**
     * A player can move forward. When a player moves, the position where he
     * started the game in the first line turns to '@'
     */
    @Test
    public void aPlayerCanMoveForward() throws ObstacleHitedException {
        Circenses circenses = new CircensesBuilder()
                .addEmptySlot()
                .build();

        Player player = new Charioteer("tiberius");
        player.startGame(circenses);

        player.forward();

        String expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|T |\n")
                .append("|@ |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());
    }

    /**
     * A Charioteer earns 100 points when he arrived to the final line.
     */
    @Test
    public void aPlayerEarns100AtFinishLine() throws ObstacleHitedException {
        Circenses circenses = new CircensesBuilder()
                .addEmptySlot()
                .build();

        Player player = new Charioteer("tiberius");
        player.startGame(circenses);

        String expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|  |\n")
                .append("|T |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(0, player.score());

        player.forward();

        assertEquals(0, player.score());

        player.forward();

        expectedDisplay = new StringBuilder()
                .append("|T#|\n")
                .append("|  |\n")
                .append("|@ |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(100, player.score());
    }

    /**
     * A Charioteer earns 10 points per coin.
     * All earned coins turns to 'x' instead of 'o'.
     */
    @Test
    public void aPlayerEarns10PerCoin() throws ObstacleHitedException {
        Circenses circenses = new CircensesBuilder()
                .addCoin()
                .build();

        Player player = new Charioteer("tiberius");
        player.startGame(circenses);

        String expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|o |\n")
                .append("|T |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(0, player.score());

        player.forward();

        expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|T |\n")
                .append("|@ |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(10, player.score());

        player.forward();

        expectedDisplay = new StringBuilder()
                .append("|T#|\n")
                .append("|x |\n")
                .append("|@ |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(110, player.score());

    }

    /**
     * It should be possible to fill both tracks using the CircensesBuilder
     * methods {@code right()} and {@code left()}.
     */
    @Test
    public void complexCircensesBuilderTest() {
        Circenses circenses = new CircensesBuilder()
                .addCoin().addCoin().addEmptySlot().addCoin().addObstacle()
                .right().addCoin().addEmptySlot().addCoin().addObstacle()
                .left().addCoin().addCoin()
                .right().addEmptySlot().addCoin().addCoin()
                .build();


        String expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("| o|\n")
                .append("| o|\n")
                .append("|o |\n")
                .append("|o_|\n")
                .append("| o|\n")
                .append("|  |\n")
                .append("|_o|\n")
                .append("|o |\n")
                .append("|  |\n")
                .append("|o |\n")
                .append("|o |\n")
                .append("|  |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());
    }

    /**
     * A player can move forward, but also right and left.
     */
    @Test
    public void aPlayerCanMoveRight() throws ObstacleHitedException {
        Circenses circenses = new CircensesBuilder()
                .addEmptySlot()
                .build();

        Player player = new Charioteer("tiberius");
        player.startGame(circenses);

        player.right();

        String expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|  |\n")
                .append("|@T|")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());

        player.forward();

        expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("| T|\n")
                .append("|@ |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());
    }

    /**
     * A player can move forward, right and left. When a player moves, the
     * position where he started the game in the first line turns to '@'
     */
    @Test
    public void aPlayerCanMoveLeft() throws ObstacleHitedException {
        Circenses circenses = new CircensesBuilder()
                .addEmptySlot()
                .build();

        Player player = new Charioteer("tiberius");
        player.startGame(circenses);

        player.forward().right().forward();

        String expectedDisplay = new StringBuilder()
                .append("|#T|\n")
                .append("|  |\n")
                .append("|@ |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());

    }

    /**
     * A Charioteer is dead when he hit an obstacle and the game should be ended. In
     * that case, he lose 5 points and he turns to 'D' at the obstacle position.
     */
    @Test
    public void playerHitObstacle() throws ObstacleHitedException {
        Circenses circenses = new CircensesBuilder()
                .addCoin().addEmptySlot()
                .right().addCoin().addEmptySlot()
                .left().addCoin().addObstacle()
                .build(); 
        Player player = new Charioteer("augustus");
        player.startGame(circenses);

        String expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|_ |\n")
                .append("|o |\n")
                .append("| o|\n")
                .append("|o |\n")
                .append("|A |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());

        player.forward().right().forward().left().forward();

        expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|_ |\n")
                .append("|A |\n")
                .append("| x|\n")
                .append("|x |\n")
                .append("|@ |")
                .toString();

        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(30, player.score());

        player.forward();

        expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|D |\n")
                .append("|x |\n")
                .append("| x|\n")
                .append("|x |\n")
                .append("|@ |")
                .toString();

        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(25, player.score());
    }

    /**
     * Once the game is ended, an exception should be fired when the player try
     * to move.
     */
    @Test(expected = ObstacleHitedException.class)
    public void playerCantMoveAfterHitingObstacle() throws ObstacleHitedException {
        Circenses circenses = new CircensesBuilder()
                .addCoin().addObstacle()
                .build();
        Player player = new Charioteer("augustus");
        player.startGame(circenses);
        player.forward().forward();

        String expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|D |\n")
                .append("|x |\n")
                .append("|@ |")
                .toString();
        assertEquals(expectedDisplay, circenses.draw());

        player.forward();
    }

    /**
     * A new player type, the Knight. A Knight earns 20 points per coin.
     * A Knight can bypass an obstacle, but he loses 10 points if he does.
     */
    @Test
    public void charioteerCanBypassHitObstacle() throws ObstacleHitedException {
        Circenses circenses = new CircensesBuilder()
                .addCoin().addEmptySlot()
                .right().addCoin().addEmptySlot()
                .left().addCoin().addObstacle()
                .build();
        Player player = new Knight("heniokhos");
        player.startGame(circenses);

        String expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|_ |\n")
                .append("|o |\n")
                .append("| o|\n")
                .append("|o |\n")
                .append("|H |")
                .toString();

        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(0, player.score());

        player.forward().right().forward().left().forward().forward();

        expectedDisplay = new StringBuilder()
                .append("|##|\n")
                .append("|H |\n")
                .append("|x |\n")
                .append("| x|\n")
                .append("|x |\n")
                .append("|@ |")
                .toString();

        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(50, player.score());

        player.forward();

        expectedDisplay = new StringBuilder()
                .append("|H#|\n")
                .append("|_ |\n")
                .append("|x |\n")
                .append("| x|\n")
                .append("|x |\n")
                .append("|@ |")
                .toString();

        assertEquals(expectedDisplay, circenses.draw());
        assertEquals(150, player.score());
    }

}