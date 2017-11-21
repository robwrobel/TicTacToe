package game;

import configuration.Board;
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
        checkForVictory(m);
        checkForDraw();
    }

    private void checkForVictory(Move m) {

    }

    private void checkForDraw() {

        int noOfFieldsInBoard = board.getBd().getRows()*board.getBd().getColumns();
        int noOfMovesInArchive = archive.getNoOfMoves();
        if ((noOfMovesInArchive == noOfFieldsInBoard) && (victory == false)) {
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
