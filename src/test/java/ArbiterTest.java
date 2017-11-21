import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import configuration.Mark;
import game.Arbiter;
import game.Archive;
import game.Move;
import game.MoveValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ArbiterTest {

    @DataProvider(name = "WEVictory")
    public static Object[][] WEVictory() {
        return new Object[][] { {1, 1, 1, new Move(Mark.O,0), new ArrayList<>(), true},
                                {1, 1, 2, new Move(Mark.O,0), new ArrayList<>(), false},
                                {3, 3, 2, new Move(Mark.O,0), Arrays.asList(new Move(Mark.O,1)), true},
                                {3, 3, 3, new Move(Mark.O,0), Arrays.asList(new Move(Mark.O,1)), false},
                                {10, 10, 3, new Move(Mark.O,1), Arrays.asList(new Move(Mark.O,0), new Move(Mark.O, 2)), true},
                                {10, 10, 4, new Move(Mark.O,1), Arrays.asList(new Move(Mark.O,0), new Move(Mark.O, 2)), false},
                                {10, 10, 3, new Move(Mark.O,1), Arrays.asList(new Move(Mark.O,0), new Move(Mark.O, 2),new Move(Mark.O,3)), true},

        };
    }

    @Test(dataProvider = "WEVictory")
    public void checkForWEVictory(int inputColumn, int inputRow, int noForWin, Move move, List<Move> listOfMovesInArchive, boolean isVictory) {

        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);

        Archive archive = new Archive();
        for (Move m : listOfMovesInArchive) {
            archive.update(m);
        }

        Board board = new BoardBuilder(boardDimensions).viaArrayList().basedOnArchive(archive).build();

        Arbiter arbiter = new Arbiter(archive, board);
        arbiter.setNoForWin(noForWin);
        arbiter.update(move);

        assertEquals(isVictory, arbiter.isVictory());

    }



}
