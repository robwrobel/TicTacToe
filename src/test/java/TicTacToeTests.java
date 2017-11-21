import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import game.Arbiter;
import game.Archive;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class TicTacToeTests {

    @DataProvider(name = "correctTestData")

    public static Object[][] correctTestData() {

        return new Object[][]{
                {2, 2, 2, 2},
                {1, 2, 1, 2},
                {2, 3, 2, 3},
                {77, 2, 77, 2},
                {135, 2, 135, 2},
                {1000, 2, 1000, 2},
                {10, 200, 10, 200},
                {2, 2, 2, 2},
        };
    }

    @Test
    public void testArbiterIsDraw() {
        BoardDimensions boardDimensions = new BoardDimensions(3,3);
        Archive archive = new Archive();
        Board board = new BoardBuilder(boardDimensions).viaArrayList().basedOnArchive(archive).build();

        Arbiter arbiter = new Arbiter(archive,board);
        assertFalse(arbiter.isDraw());

    }
}
