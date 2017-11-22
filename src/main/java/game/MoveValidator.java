package game;

import configuration.Board;
import configuration.MoveObserver;

import java.util.ArrayList;
import java.util.List;

public class MoveValidator {
    Board board;

    List<MoveObserver> observerList = new ArrayList<>();

    public MoveValidator(Board board) {
        this.board = board;
    }

    public boolean isMoveValid(Move move) {
        boolean isMoveValid = isIdBetweenMinAndMaxId(move.getId())&& !isIdAlreadyMarked(move.getId());
        if (isMoveValid) {
            notifyObservers(move);
        }
        return isMoveValid;
    }

    private void notifyObservers(Move move) {
        for (MoveObserver mo : observerList) {
            mo.update(move);
        }
    }

    public void addObserver(MoveObserver moveObserver) {
        observerList.add(moveObserver);
    }

    private boolean isIdAlreadyMarked(int id) {
        return board.isMarked(id);
    }

    private boolean isIdBetweenMinAndMaxId(int id) {
        return id >=0 && id <= board.getMaxId();
    }

}
