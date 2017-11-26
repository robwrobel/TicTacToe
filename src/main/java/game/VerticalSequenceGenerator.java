package game;

import configuration.Board;

import java.util.Set;


public class VerticalSequenceGenerator extends SequenceGenerator {

    final int STEP;

    public VerticalSequenceGenerator(Board board, Arbiter arbiter) {
        super(board, arbiter);
        STEP = board.getBd().getColumns();
    }
    public Set<Integer> goForSmaller(int id, Set<Integer> integerSet){
        if ( boardEdges.isTopEdge(id) || counter == arbiter.getNoForWin()) {
            return integerSet;
        } else {
            int nextId = id - STEP;
            integerSet.add(nextId);
            counter ++;
            return goForSmaller(nextId,integerSet);
        }
    }

    public Set<Integer> goForBigger(int id, Set<Integer> integerSet) {
        if ( boardEdges.isBottomEdge(id) || counter == arbiter.getNoForWin()) {
            return integerSet;
        } else {
            int nextId = id + STEP;
            integerSet.add(nextId);
            counter ++;
            return goForBigger(nextId,integerSet);
        }

    }


}