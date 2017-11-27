package configuration;

import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import configuration.BoardEdges;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BoardEdgesTest {

    @DataProvider(name = "boardDimensionsAndLeftEdgeIds")

    public static Object[][] boardDimensionsAndLeftEdgeIds() {

        return new Object[][]{
                {3, 3, new int[] {0, 3, 6}, true },
                {2, 3, new int[] {0, 2, 4}, true},
                {100,10,new int[] {0,100,200,300,400,500,600,700,800,900}, true},
                {3, 3, new int[] {1, 4, 7}, false },
                {2, 3, new int[] {1, 3, 5}, false},
                {100,10,new int[] {1,101,201,301,399,499,599,701,801,901}, false},
        };
    }


    @Test(dataProvider="boardDimensionsAndLeftEdgeIds")

    void testIsIdOnLeftEdge(int inputColumn, int inputRow, int[] ids, boolean isOnEdge) {
        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();

        BoardEdges boardEdges = new BoardEdges(board);
        for (int id :ids) {
            assertEquals(boardEdges.isLeftEdge(id), isOnEdge);
        }
    }

    @DataProvider(name = "boardDimensionsAndRightEdgeIds")

    public static Object[][] boardDimensionsAndRightEdgeIds() {

        return new Object[][]{
                {3, 3, new int[] {2, 5, 8}, true },
                {2, 3, new int[] {1, 3, 5}, true},
                {100,10,new int[] {99,199,299,399,499,599,699,799,899,999}, true},
                {3, 3, new int[] {1, 6, 7}, false },
                {2, 3, new int[] {2, 4, 6}, false },
                {100,10,new int[] {98,198,298,398,498,598,698,798,898,998}, false}
        };
    }


    @Test(dataProvider="boardDimensionsAndRightEdgeIds")

    void testIsIdOnRightEdge(int inputColumn, int inputRow, int[] ids, boolean isOnEdge) {
        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();

        BoardEdges boardEdges = new BoardEdges(board);
        for (int id :ids) {
            assertEquals(boardEdges.isRightEdge(id), isOnEdge);
        }
    }

    @DataProvider(name = "boardDimensionsAndTopEdgeIds")
    public static Object[][] boardDimensionsAndTopEdgeIds() {

        return new Object[][]{
                {3, 3, new int[] {0, 1, 2}, true },
                {2, 3, new int[] {0, 1}, true},
                {10,100,new int[] {0,1,2,3,4,5,6,7,8,9}, true},
                {3, 3, new int[] {-1, 3, 4}, false },
                {2, 3, new int[] {-1, 2}, false},
                {10,100,new int[] {-1,10}, false}
        };
    }


    @Test(dataProvider="boardDimensionsAndTopEdgeIds")

    void testIsIdOnTopEdge(int inputColumn, int inputRow, int[] ids, boolean isOnEdge) {
        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();

        BoardEdges boardEdges = new BoardEdges(board);
        for (int id :ids) {
            assertEquals(boardEdges.isTopEdge(id), isOnEdge);
        }
    }

    @DataProvider(name = "boardDimensionsAndBottomEdgeIds")
    public static Object[][] boardDimensionsAndBottomEdgeIds() {

        return new Object[][]{
                {3, 3, new int[] {6, 7, 8}, true },
                {2, 3, new int[] {4, 5}, true},
                {10,100,new int[] {990,991,992,993,994,995,996,997,998,999}, true},
                {3, 3, new int[] {5, 9}, false },
                {2, 3, new int[] {3, 6}, false},
                {10,100,new int[] {989,1000}, false},

        };
    }


    @Test(dataProvider="boardDimensionsAndBottomEdgeIds")

    void testIsIdOnBottomEdge(int inputColumn, int inputRow, int[] ids, boolean isOnEdge) {
        BoardDimensions boardDimensions = new BoardDimensions(inputColumn,inputRow);
        Board board = new BoardBuilder(boardDimensions).viaArrayList().build();

        BoardEdges boardEdges = new BoardEdges(board);
        for (int id :ids) {
            assertEquals(boardEdges.isBottomEdge(id), isOnEdge);
        }
    }



}
