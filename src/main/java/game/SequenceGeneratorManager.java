package game;

import configuration.Board;
import configuration.MoveObserver;

import java.util.ArrayList;
import java.util.List;

public class SequenceGeneratorManager implements MoveObserver {
    private final Board board;
    private final Arbiter arbiter;


    List<SequenceGenerator> sequenceGeneratorList = new ArrayList<>();

    SequenceGeneratorManager(Board board, Arbiter arbiter) {
        this.board = board;
        this.arbiter = arbiter;
        sequenceGeneratorList.add(new HorizontalSequenceGenerator());
    }

    public void update(Move m) {
        List<String> sequences = generateSequences(m.getId());
        arbiter.update(sequences,m.getMark().toString());
    }

    private List<String> generateSequences(int id) {
        List<String> sequenceList = new ArrayList<>();

        for(SequenceGenerator sequenceGenerator: sequenceGeneratorList)
            sequenceList.add(sequenceGenerator.generate(id));

        return sequenceList;
    }

    private abstract class SequenceGenerator {

        String generate(int id) {
            List<Integer> ids = findIds(id);
            StringBuilder sb = new StringBuilder();
            for (Integer i : ids) {
                sb.append(board.getMark(i));
            }
            return sb.toString();
        }

        protected abstract List<Integer> findIds(int id);
    }


    private class HorizontalSequenceGenerator extends SequenceGenerator {

    @Override
    public List<Integer> findIds(int id) {
        return null;
    }
    }
}
