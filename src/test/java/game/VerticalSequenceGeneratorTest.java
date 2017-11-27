package game;

import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import game.Arbiter;
import game.SequenceGeneratorManager;
import game.VerticalSequenceGenerator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

public class VerticalSequenceGeneratorTest {

    @DataProvider(name = "BoardSize_Id_NoForWin_ResultSequenceSet")
    public static Object[][] BoardSize_Id_NoForWin_ResultSequenceSet() {
        return new Object[][]{ {3, 3, 0, 3, new TreeSet<>(Arrays.asList(0, 3, 6))},
                {3, 3, 2, 3, new TreeSet<>(Arrays.asList(2, 5, 8))},
                {3, 3, 4, 3, new TreeSet<>(Arrays.asList(1, 4, 7))},
                {10, 10, 5, 10, new TreeSet<>(Arrays.asList(5, 15, 25, 35, 45, 55, 65, 75, 85, 95))},
                {10, 10, 0, 10, new TreeSet<>(Arrays.asList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90))},
                {10, 10, 5, 3, new TreeSet<>(Arrays.asList(5, 15, 25))},
                {3, 2, 2, 2, new TreeSet<>(Arrays.asList(2,5))}

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

        VerticalSequenceGenerator verticalSequenceGenerator =
                new VerticalSequenceGenerator(board, arbiter);
        Set<Integer> actualIdSet = verticalSequenceGenerator.findIds(id);
        assertEquals(actualIdSet, expectedResult);

    }

}

