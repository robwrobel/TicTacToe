import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import configuration.Mark;
import game.Move;
import game.MoveValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class MoveValidatorTest {

    @DataProvider(name="moveBetweenMinAndMaxId")
    public static Object[][] moveBetweenMinAndMaxId () {

        return new Object[][] {
                {1,1,new Move(Mark.O,0),true},
                {1,1,new Move(Mark.O,1),false},
                {10,10,new Move(Mark.X,-1),false},
                {10,10,new Move(Mark.X, 100),false},
                {10,10,new Move(Mark.X, 99),true},
                {10,10,new Move(Mark.X, 0),true}
        };

    }

    @Test(dataProvider = "moveBetweenMinAndMaxId")
    public void isMoveBetweenMinAndMaxId(int inputColumn, int inputRow, Move move, boolean isMoveValid) {
        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();
        MoveValidator moveValidator = new MoveValidator(board);
        assertEquals(isMoveValid, moveValidator.isMoveValid(move));

    }

    @DataProvider(name="moveOnAlreadyMarkedId")
    public static Object[][] moveOnAlreadyMarkedId () {

        return new Object[][] {
                {1,1,new Move(Mark.O,0), Arrays.asList(new Move(Mark.O,0)),false},
                {10,10,new Move(Mark.X,99), Arrays.asList(new Move(Mark.O,99)),false},
                {2,2,new Move(Mark.O,3),Arrays.asList(new Move(Mark.O,0), new Move(Mark.X,1), new Move(Mark.O,2)),true},
                {10,10,new Move(Mark.O,99),Arrays.asList(new Move(Mark.O,0), new Move(Mark.X,1), new Move(Mark.O,2)),true},
        };

    }

    @Test(dataProvider = "moveOnAlreadyMarkedId")
    public void isMoveOnAlreadyMarkedId(int inputColumn, int inputRow, Move move, List<Move> listOfMoves, boolean isMoveValid) {

        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);

        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();
        for (Move m : listOfMoves) {
            board.update(m);
        }


        MoveValidator moveValidator = new MoveValidator(board);

        assertEquals(isMoveValid, moveValidator.isMoveValid(move));

    }


}
