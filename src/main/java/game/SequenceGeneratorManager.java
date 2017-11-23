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
        sequenceGeneratorList.add(new DiagonalSequenceGenerator());
        sequenceGeneratorList.add(new AntiDiagonalSequenceGenerator());
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

    public class DiagonalSequenceGenerator extends SequenceGenerator {
        @Override
        public Set<Integer> findIds(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            integerSet.add(id);
            integerSet.addAll(goUpLeft(id));
            integerSet.addAll(goDownRight(id));

            return integerSet;
        }

        private Set<Integer> goUpLeft(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            int colNo = board.getBd().getColumns();
            int winSequenceLength = arbiter.getNoForWin();

            for(int i = id - colNo -1, j = 2, previousId = id;
                i>=0 && j <= winSequenceLength && isNeighbourRow(i,previousId);
                previousId = i, i -= colNo+1, j++) {
                integerSet.add(i);
            }
            return integerSet;
        }

        private boolean isNeighbourRow(int i, int previousId) {
            int colNo = board.getBd().getColumns();
            int rowId1 = i/colNo;
            int rowId2 = previousId/colNo;

            return Math.abs(rowId1-rowId2) == 1;
        }

        private Set<Integer> goDownRight(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            int colNo = board.getBd().getColumns();
            int maxId = board.getMaxId();
            int winSequenceLength = arbiter.getNoForWin();

            for(int i = id + colNo + 1, j = 2, previousId = id;
                i <= maxId && j <= winSequenceLength && isNeighbourRow(i,previousId);
                previousId = i, i += colNo+1, j++) {
                integerSet.add(i);
            }
            return integerSet;

        }

    }

    public class AntiDiagonalSequenceGenerator extends SequenceGenerator {
        @Override
        public Set<Integer> findIds(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            integerSet.add(id);
            integerSet.addAll(goUpRight(id));
            integerSet.addAll(goDownLeft(id));

            return integerSet;
        }

        private Set<Integer> goUpRight(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            int colNo = board.getBd().getColumns();
            int winSequenceLength = arbiter.getNoForWin();

            for(int i = id - colNo + 1, j = 2, previousId = id;
                i>=0 && j <= winSequenceLength && isNeighbourRow(i,previousId);
                previousId = i, i -= colNo-1, j++) {
                integerSet.add(i);
            }
            return integerSet;
        }

        private boolean isNeighbourRow(int i, int previousId) {
            int colNo = board.getBd().getColumns();
            int rowId1 = i/colNo;
            int rowId2 = previousId/colNo;

            return Math.abs(rowId1-rowId2) == 1;
        }

        private Set<Integer> goDownLeft(int id) {
            Set<Integer> integerSet = new TreeSet<>();
            int colNo = board.getBd().getColumns();
            int maxId = board.getMaxId();
            int winSequenceLength = arbiter.getNoForWin();

            for(int i = id + colNo - 1, j = 2, previousId = id;
                i <= maxId && j <= winSequenceLength && isNeighbourRow(i,previousId);
                previousId = i, i += colNo-1, j++) {
                integerSet.add(i);
            }
            return integerSet;

        }

    }


}
