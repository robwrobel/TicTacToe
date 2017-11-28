package game;

import configuration.Board;

import java.util.Set;

public class HorizontalSequenceGenerator extends SequenceGenerator {

    final int STEP = 1;

    public HorizontalSequenceGenerator(Board board, Arbiter arbiter) {
        super(board, arbiter);
    }
    public Set<Integer> goForSmaller(int id, Set<Integer> integerSet){
        if ( boardEdges.isLeftEdge(id) || counter == arbiter.getWinSequenceLength()) {
            return integerSet;
        } else {
            int nextId = id - STEP;
            integerSet.add(nextId);
            counter ++;
            return goForSmaller(nextId,integerSet);
        }
    }

    public Set<Integer> goForBigger(int id, Set<Integer> integerSet) {
        if ( boardEdges.isRightEdge(id) || counter == arbiter.getWinSequenceLength()) {

            return integerSet;
        } else {
            int nextId = id + STEP;
            integerSet.add(nextId);
            counter ++;
            return goForBigger(nextId,integerSet);
        }

    }

}