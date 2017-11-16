package configuration;

import game.Move;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Mark> markList;
    List<MoveObserver> observerList = new ArrayList<>();
    BoardDimensions bd;

    Board(List<Mark> markList, BoardDimensions bd) {
        this.markList = markList;
        this.bd = bd;
    }

    public void update(Move move) {
        markList.set(move.getId(),move.getMark());
        notifyObservers(move);
    }

    public void notifyObservers(Move move) {
        for (MoveObserver mo : observerList) {
            mo.update(move);
        }
    }

    public void addObserver(MoveObserver moveObserver) {
        observerList.add(moveObserver);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        int id = 0;
        for (int row = 0; row < bd.rows; row++) {
            for (int col = 0; col < bd.columns; col++) {
                String mark = markList.get(id).toString();
                sb.append(toFieldFormat(mark.equals(" ") ? ""+id : mark));
                id++;
            }
            sb.append(System.getProperty("line.separator").toString());
        }

        return sb.toString();    }


    private String toFieldFormat(String field) {
        final int finalStringLength = 4;
        int idLength = field.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < finalStringLength - idLength; i++) {
            sb.append(" ");
        }
        sb.append(field);
        return sb.toString();
    }

    public BoardDimensions getBd() {
        return bd;
    }

    public int getMaxId() {
        return bd.columns*bd.rows;
    }

    public boolean isMarked(int id) {
        return !markList.get(id).equals(Mark.EMPTY);
    }

}
