package game;

import configuration.Board;

public class MoveValidator {
    Board board;

    public MoveValidator(Board board) {
        this.board = board;
    }

    public boolean isMoveValid(Move move) {
        return isIdBetweenMinAndMaxId(move.getId())&& !isIdAlreadyMarked(move.getId());
    }

    private boolean isIdAlreadyMarked(int id) {
        return board.isMarked(id);
    }

    private boolean isIdBetweenMinAndMaxId(int id) {
        return id >=0 && id <= board.getMaxId();
    }
}
