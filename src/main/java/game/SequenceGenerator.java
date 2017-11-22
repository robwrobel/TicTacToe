package game;

import configuration.Board;
import configuration.MoveObserver;

import java.util.List;

public class SequenceGenerator implements MoveObserver {
    private final Board board;
    private final Arbiter arbiter;

    public SequenceGenerator(Board board, Arbiter arbiter) {
        this.board = board;
        this.arbiter = arbiter;
    }

    @Override
    public void update(Move m) {
        List<String> sequences = generateSequences(m);
        arbiter.update(sequences,m.getMark().toString());
    }

    private List<String> generateSequences(Move m) {
        return null;
    }
}
