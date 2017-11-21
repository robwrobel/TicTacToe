import configuration.Mark;
import game.Archive;
import game.Move;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ArchiveTests {

    @DataProvider(name="archiveMoves")
    public static Object[][] archiveMoves() {
        return new Object[][] {
                {Arrays.asList(),0},
                {Arrays.asList(new Move(Mark.O,1)),1},
                {Arrays.asList(new Move(Mark.O,1),new Move(Mark.X,1)),2}
        };
    }

    @Test(dataProvider = "archiveMoves")
    public void testNoOfItemsInArchive(List<Move> listOfMoves, int expectedNoOfItems) {
        Archive archive = new Archive();
        for(Move move: listOfMoves) {
            archive.update(move);
        }
        assertEquals(expectedNoOfItems, archive.getNoOfMoves());
    }
}
