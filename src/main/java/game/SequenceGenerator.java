package game;

import configuration.Board;
import configuration.BoardEdges;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public abstract class SequenceGenerator {
    protected final Board board;
    protected final Arbiter arbiter;
    protected BoardEdges boardEdges;
    protected int counter = 0;


    public SequenceGenerator(Board board, Arbiter arbiter) {
        this.board = board;
        this.arbiter = arbiter;
        boardEdges = new BoardEdges(board);
    }


    String generate(int id) {
        Set<Integer> ids = findIds(id);
        StringBuilder sb = new StringBuilder();
        for (Integer i : ids) {
            sb.append(board.getMark(i));
        }
        return sb.toString();
    }

    public Set<Integer> findIds(int id) {
        Set<Integer> integerSet = new TreeSet<>();
        integerSet.add(id);
        counter = 1;
        integerSet.addAll(goForSmaller(id,integerSet));
        counter = 1;
        integerSet.addAll(goForBigger(id,integerSet));

        return integerSet;

    }

    abstract protected Set<Integer> goForSmaller(int id, Set<Integer> integerSet);
    abstract protected Set<Integer> goForBigger(int id, Set<Integer> integerSet);




}

