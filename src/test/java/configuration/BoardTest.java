package configuration;

import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import configuration.Mark;
import game.Move;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class BoardTest {

    @DataProvider(name = "correctBoardDimensions")

    public static Object[][] correctBoardDimensions() {

        return new Object[][]{
                {1, 1, 1, 1},
                {2, 3, 2, 3},
                {100,100,100,100}
        };
    }


    @Test(dataProvider="correctBoardDimensions")

    void testBoardDimensions(int inputColumn, int inputRow, int expectedColumn, int expectedRow) {
        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();
        int actualRow = board.getBd().getRows();
        int actualColumn = board.getBd().getColumns();
        assertEquals(actualColumn, expectedColumn);
        assertEquals(actualRow, expectedRow);
    }

    @DataProvider(name = "boardDimensionsSet")

    public static Object[][] boardDimensionsSet() {

        return new Object[][]{
                {1, 1},
                {2, 3},
                {100,10}
        };
    }


    @Test(dataProvider = "boardDimensionsSet")
    void testIfAllMarksAreEmptyInTheBeginingOfTheMatch(int inputColumn,int inputRow) {
        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();

        for(int id = 0; id <= board.getMaxId(); id++) {
            assertFalse(board.isMarked(id));
        }
    }

    @DataProvider(name = "boardDisplay")

    public static Object[][] boardDisplay() {

        return new Object[][]{
                {1, 1, new ArrayList(), "   0\n"},
                {1, 1, Arrays.asList(new Move(Mark.O,0)), "   O\n"},
                {1, 1, Arrays.asList(new Move(Mark.X,0)), "   X\n"},
                {3, 3, Arrays.asList(new Move(Mark.X,8)), "   0   1   2\n   3   4   5\n   6   7   X\n"},
                {3, 3, Arrays.asList(new Move(Mark.X,0),new Move(Mark.O,8)), "   X   1   2\n   3   4   5\n   6   7   O\n"}
        };
    }

    @Test(dataProvider = "boardDisplay")
    void testIfBoardDisplaysCorrectly (int inputColumn, int inputRow, List<Move> listOfMoves, String expectedBoardDisplay){

        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);

        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();

        for (Move move: listOfMoves)
            board.update(move);

        assertEquals(board.toString(), expectedBoardDisplay);
    }

    @DataProvider(name = "boardMaxId")

    public static Object[][] boardMaxId() {

        return new Object[][]{
                {1, 1, 0},
                {3, 3, 8},
                {5, 7, 34}
        };
    }

    @Test(dataProvider = "boardMaxId")
    void testBoardMaxId (int inputColumn, int inputRow, int expectedMaxId){

        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);

        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();

        assertEquals(board.getMaxId(), expectedMaxId );
    }

    @DataProvider(name = "boardBasedOnMoveList")

    public static Object[][] boardBasedOnMoveList() {

        return new Object[][]{
                {1, 1, Arrays.asList(new Move(Mark.O,0)), "   O\n"},
                {3, 3, Arrays.asList(new Move(Mark.X,8)), "   0   1   2\n   3   4   5\n   6   7   X\n"},
                {3, 3, Arrays.asList(new Move(Mark.X,0),new Move(Mark.O,8)), "   X   1   2\n   3   4   5\n   6   7   O\n"}
        };
    }


    @Test(dataProvider = "boardBasedOnMoveList")
    void testCreatingBoardBasedOnArchive(int inputColumn, int inputRow, List<Move> listOfMoves, String expectedBoardDisplay) {

        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);


        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();
        for (Move move: listOfMoves) {
            board.update(move);
        }

        assertEquals(board.toString(), expectedBoardDisplay );

    }
}
