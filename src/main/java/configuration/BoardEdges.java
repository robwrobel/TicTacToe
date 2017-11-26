package configuration;

import java.util.HashSet;
import java.util.Set;

public class BoardEdges {
    Board board;
    Set<Integer> leftEdge;
    Set<Integer> rightEdge;
    Set<Integer> topEdge;
    Set<Integer> bottomEdge;

    public BoardEdges(Board board) {
        this.board = board;
        initializeEdges();
    }

    private void initializeEdges() {
        leftEdge = new HashSet<>();

        for (int id = 0; id <= board.getMaxId(); id += board.getBd().getColumns()) {
            leftEdge.add(id);
        }

        rightEdge = new HashSet<>();

        for (int id = board.getBd().getColumns() - 1 ; id <= board.getMaxId(); id+= board.getBd().getColumns()) {
            rightEdge.add(id);
        }

        topEdge = new HashSet<>();

        for (int id = 0; id < board.getBd().getColumns(); id+=1) {
            topEdge.add(id);
        }

        bottomEdge = new HashSet<>();

        for (int id = board.getBd().getColumns()*(board.getBd().getRows() - 1);
             id <= board.getMaxId();
             id += 1 ) {
            bottomEdge.add(id);
        }

    }

    public boolean isLeftEdge(int id) {
        return leftEdge.contains(id);
    }

    public boolean isRightEdge(int id) {
        return rightEdge.contains(id);
    }

    public boolean isTopEdge(int id) {
        return topEdge.contains(id);
    }

    public boolean isBottomEdge(int id) {
        return bottomEdge.contains(id);
    }

}
