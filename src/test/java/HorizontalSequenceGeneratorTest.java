import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import game.Arbiter;
import game.SequenceGeneratorManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

public class HorizontalSequenceGeneratorTest {

    @DataProvider(name = "BoardSizeIdNoForWinResultSequenceSet")
    public static Object[][] BoardSizeIdNoForWinResultSequenceSet() {
        return new Object[][]{ {3, 3, 0, 3, new TreeSet<>(Arrays.asList(0, 1, 2))},
                {3, 3, 2, 3, new TreeSet<>(Arrays.asList(0, 1, 2))},
                {3, 3, 4, 3, new TreeSet<>(Arrays.asList(3, 4, 5))},
                {10, 10, 5, 10, new TreeSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))},
                {10, 10, 0, 10, new TreeSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))},
                {10, 10, 5, 3, new TreeSet<>(Arrays.asList(3,4,5,6,7))},
                {3,2,2,3, new TreeSet<>(Arrays.asList(0, 1, 2))}

        };


    }


    @Test(dataProvider = "BoardSizeIdNoForWinResultSequenceSet")
    public void checkIfGeneratedSequenceIsCorrectOrNot(int inputColumn,
                                                       int inputRow,
                                                       int id,
                                                       int noForWin,
                                                       Set<Integer> expectedResult) {

        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();
        Arbiter arbiter = new Arbiter();
        arbiter.setNoForWin(noForWin);
        SequenceGeneratorManager sgm = new SequenceGeneratorManager(board, arbiter);
        SequenceGeneratorManager.HorizontalSequenceGenerator horizontalSequenceGenerator =
                sgm.new HorizontalSequenceGenerator();
        Set<Integer> actualIdSet = horizontalSequenceGenerator.findIds(id);
        assertEquals(actualIdSet, expectedResult);

    }

}
