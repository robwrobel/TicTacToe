package game;

import configuration.Board;
import configuration.Mark;
import configuration.MoveObserver;

public class Arbiter implements MoveObserver{

    private final Board board;
    private final Archive archive;
    private int noForWin;
    private boolean draw;
    private boolean victory;
    private int tmpCounter = 0;

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

        checkForWEVictory(m);
    }

    private void checkForWEVictory(Move m) {
        Mark mark = m.getMark();
        int id = m.getId();
        tmpCounter = 0;
        int eastNo = goEast(id , mark);
        tmpCounter = 0;
        int westNo = goWest(id , mark);
        int totalNo = eastNo + westNo + 1;
        if (totalNo >= noForWin) {
            victory = true;
        }
    }

    private int goWest(int id, Mark mark) {
        int colNo = board.getBd().getColumns();

        Integer nextId = ((id > 0) && (id/colNo == (id - 1)/colNo)
                && (board.getMark(id - 1) == mark)) ? id - 1 : null;
        if (nextId == null) {
            return tmpCounter;
        } else {
            tmpCounter ++;
            return goWest(nextId,mark);
        }
    }

    private int goEast(int id, Mark mark) {
        int colNo = board.getBd().getColumns();
        int rowNo = board.getBd().getRows();
        Integer nextId = ((id < board.getMaxId()) && (id/colNo == (id + 1)/colNo)
                        && (board.getMark(id + 1) == mark)) ? id + 1 : null;
        if (nextId == null) {
            return tmpCounter;
        } else {
            tmpCounter ++;
            return goEast(nextId,mark);
        }
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
