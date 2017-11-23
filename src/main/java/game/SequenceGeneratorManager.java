package game;

import configuration.Board;
import configuration.MoveObserver;

import java.util.*;

public class SequenceGeneratorManager implements MoveObserver {
    private final Board board;
    private final Arbiter arbiter;


    List<SequenceGenerator> sequenceGeneratorList = new ArrayList<>();

    public SequenceGeneratorManager(Board board, Arbiter arbiter) {
        this.board = board;
        this.arbiter = arbiter;
        sequenceGeneratorList.add(new HorizontalSequenceGenerator());
        sequenceGeneratorList.add(new VerticalSequenceGenerator());
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

    public abstract class SequenceGenerator {

        String generate(int id) {
            Set<Integer> ids = findIds(id);
            StringBuilder sb = new StringBuilder();
            for (Integer i : ids) {
                sb.append(board.getMark(i));
            }
            return sb.toString();
        }

        protected abstract Set<Integer> findIds(int id);
    }


    public class HorizontalSequenceGenerator extends SequenceGenerator {

        @Override
        public Set<Integer> findIds(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            integerSet.add(id);
            integerSet.addAll(goLeft(id));
            integerSet.addAll(goRight(id));

            return integerSet;
        }

        private Set<Integer> goLeft(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            int colNo = board.getBd().getColumns();
            int winSequenceLength = arbiter.getNoForWin();
            for (int i = id - 1, j = 2; i >= 0 && j <= winSequenceLength; i--,j++) {
                if (i/colNo == id/colNo) {
                    integerSet.add(i);
                } else {
                    break;
                }
            }
            return integerSet;
        }

        private Set<Integer> goRight(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            int colNo = board.getBd().getColumns();
            int maxId = board.getMaxId();
            int winSequenceLength = arbiter.getNoForWin();
            for (int i = id + 1, j=2; i <= maxId && j <= winSequenceLength; i++,j++) {
                if (i/colNo == id/colNo) {
                    integerSet.add(i);
                } else {
                    break;
                }
            }
            return integerSet;
        }
    }

    public class VerticalSequenceGenerator extends SequenceGenerator {
        @Override
        public Set<Integer> findIds(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            integerSet.add(id);
            integerSet.addAll(goUp(id));
            integerSet.addAll(goDown(id));

            return integerSet;
        }

        private Set<Integer> goDown(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            int colNo = board.getBd().getColumns();
            int maxId = board.getMaxId();
            int winSequenceLength = arbiter.getNoForWin();
            for(int i = id + colNo, j = 2; i<=maxId && j <= winSequenceLength; i+=colNo, j++) {
                integerSet.add(i);
            }
            return integerSet;
        }

        private Set<Integer> goUp(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            int colNo = board.getBd().getColumns();
            int maxId = board.getMaxId();
            int winSequenceLength = arbiter.getNoForWin();
            for(int i = id - colNo, j = 2; i>=0 && j <= winSequenceLength; i-=colNo, j++) {
                integerSet.add(i);
            }
            return integerSet;

        }

    }
}
