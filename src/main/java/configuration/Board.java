package configuration;

import game.Move;

import java.util.List;

public class Board {
    List<Mark> list;
    List<MoveObserver> observerList;

    Board(List<Mark> list) {
        this.list = list;
    }

    public void updateMark(Move move) {
        list.set(move.getId(),move.getMark());
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
}
