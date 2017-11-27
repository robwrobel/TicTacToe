package game;

import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import game.AntiDiagonalSequenceGenerator;
import game.Arbiter;
import game.SequenceGeneratorManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

public class AntiDiagonalSequenceGeneratorTest {

    @DataProvider(name = "BoardSize_Id_NoForWin_ResultSequenceSet")
    public static Object[][] BoardSize_Id_NoForWin_ResultSequenceSet() {
        return new Object[][]{ {3, 3, 0, 3, new TreeSet<>(Arrays.asList(0))},
                {3, 3, 2, 3, new TreeSet<>(Arrays.asList(2, 4, 6))},
                {3, 3, 4, 3, new TreeSet<>(Arrays.asList(2, 4, 6))},
                {10, 10, 5, 10, new TreeSet<>(Arrays.asList(5, 14, 23, 32, 41, 50))},
                {10, 10, 0, 10, new TreeSet<>(Arrays.asList(0))},
                {10, 10, 5, 3, new TreeSet<>(Arrays.asList(5, 14, 23))},
                {3, 2, 1, 2, new TreeSet<>(Arrays.asList(1,3))}

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
        SequenceGeneratorManager sgm = new SequenceGeneratorManager(board, arbiter);
        AntiDiagonalSequenceGenerator antiDiagonalSequenceGenerator =
                new AntiDiagonalSequenceGenerator(board, arbiter);
        Set<Integer> actualIdSet = antiDiagonalSequenceGenerator.findIds(id);
        assertEquals(actualIdSet, expectedResult);

    }

}
