package game;
import configuration.Mark;
import configuration.Player;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ScoresTest {

    @Test
    public void testScoresAfterWin() {
        Player player1 = new Player(Mark.O);
        Player player2 = new Player(Mark.X);
        List<Player> listOfPlayers = Arrays.asList(player1, player2);
        Scores scores = new Scores(listOfPlayers);
        scores.updateScoreForWinner(player1);
        assertEquals(scores.getScoreForPlayer(player1),Scores.SCORE_FOR_WIN);
    }

    @Test
    public void testScoresForDraw() {
        Player player1 = new Player(Mark.O);
        Player player2 = new Player(Mark.X);
        List<Player> listOfPlayers = Arrays.asList(player1, player2);
        Scores scores = new Scores(listOfPlayers);
        scores.updateScoreForDraw();
        assertEquals(scores.getScoreForPlayer(player1),Scores.SCORE_FOR_DRAW);
        assertEquals(scores.getScoreForPlayer(player2),Scores.SCORE_FOR_DRAW);
    }

    @Test
    public void testHighestScore() {
        Player player1 = new Player(Mark.O);
        Player player2 = new Player(Mark.X);
        List<Player> listOfPlayers = Arrays.asList(player1, player2);
        Scores scores = new Scores(listOfPlayers);
        scores.updateScoreForDraw();
        scores.updateScoreForWinner(player1);
        assertEquals(scores.getHighestScore(),Scores.SCORE_FOR_DRAW + Scores.SCORE_FOR_WIN);

    }
}
