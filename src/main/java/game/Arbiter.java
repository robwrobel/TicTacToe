package game;

import configuration.Board;
import configuration.BoardDimensions;
import configuration.MoveObserver;

public class Arbiter implements MoveObserver{

    private final Board board;
    int noForWin;
    boolean draw;
    boolean victory;
    Archive archive;

    public Arbiter(Archive archive, Board board) {
        this.archive = archive;
        this.board = board;
    }

    @Override
    public void update(Move m) {
        checkforVictory(m);
        checkforDraw();

    }

    private void checkforVictory(Move m) {

    }

    private void checkforDraw() {

        int noOfFieldsInBoard = board.getBd().getRows()*board.getBd().getColumns();

        if ((archive.getNoOfMoves() == noOfFieldsInBoard) && (victory == false)) {
            draw = true;
        }
    }

    public void setNoForWin(int noForWin) {
        this.noForWin = noForWin;
    }

    public boolean isMatchFinished() {
        return (isDraw() || isVictory());
    }

    public boolean isDraw() {
        return draw;
    }

    public boolean isVictory() {
        return victory;
    }
}
