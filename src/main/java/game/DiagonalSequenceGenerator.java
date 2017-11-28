package game;

import configuration.Board;

import java.util.Set;


public class DiagonalSequenceGenerator extends SequenceGenerator {
    final int STEP;

    public DiagonalSequenceGenerator(Board board, Arbiter arbiter) {
        super(board, arbiter);
        STEP = board.getBd().getColumns() + 1;
    }
    public Set<Integer> goForSmaller(int id, Set<Integer> integerSet){
        if ( boardEdges.isTopEdge(id) || boardEdges.isLeftEdge(id) || counter == arbiter.getWinSequenceLength()) {
            return integerSet;
        } else {
            int nextId = id - STEP;
            integerSet.add(nextId);
            counter ++;
            return goForSmaller(nextId,integerSet);
        }
    }

    public Set<Integer> goForBigger(int id, Set<Integer> integerSet) {
        if ( boardEdges.isBottomEdge(id) || boardEdges.isRightEdge(id) || counter == arbiter.getWinSequenceLength()) {
            return integerSet;
        } else {
            int nextId = id + STEP;
            integerSet.add(nextId);
            counter++;
            return goForBigger(nextId,integerSet);
        }

    }

}
