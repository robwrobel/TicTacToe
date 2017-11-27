package game;

import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import game.Arbiter;
import game.DiagonalSequenceGenerator;
import game.SequenceGeneratorManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

public class DiagonalSequenceGeneratorTest {

    @DataProvider(name = "BoardSize_Id_NoForWin_ResultSequenceSet")
    public static Object[][] BoardSize_Id_NoForWin_ResultSequenceSet() {
        return new Object[][]{ {3, 3, 0, 3, new TreeSet<>(Arrays.asList(0, 4, 8))},
                {3, 3, 2, 3, new TreeSet<>(Arrays.asList(2))},
                {3, 3, 4, 3, new TreeSet<>(Arrays.asList(0, 4, 8))},
                {10, 10, 5, 10, new TreeSet<>(Arrays.asList(5, 16, 27, 38, 49))},
                {10, 10, 0, 10, new TreeSet<>(Arrays.asList(0, 11, 22, 33, 44, 55, 66, 77, 88, 99))},
                {10, 10, 5, 3, new TreeSet<>(Arrays.asList(5, 16, 27))},
                {3, 2, 1, 2, new TreeSet<>(Arrays.asList(1,5))},
                {4, 5, 15, 3, new TreeSet<>(Arrays.asList(5,10,15))}

        };


    }


    @Test(dataProvider = "BoardSize_Id_NoForWin_ResultSequenceSet")
    public void checkIfGeneratedSequenceIsCorrectOrNot(int inputColumn,
                                                       int inputRow,
                                                       int id,
                                                       int noForWin,
                                                       Set<Integer> expectedResult) {

        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();
        Arbiter arbiter = new Arbiter(null, null);
        arbiter.setNoForWin(noForWin);

        DiagonalSequenceGenerator diagonalSequenceGenerator =
                new DiagonalSequenceGenerator(board, arbiter);
        Set<Integer> actualIdSet = diagonalSequenceGenerator.findIds(id);
        assertEquals(actualIdSet, expectedResult);

    }

}
