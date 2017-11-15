package game;

import configuration.MoveObserver;

import java.util.ArrayList;
import java.util.List;

public class Archive implements MoveObserver {
    List<Move> moveList = new ArrayList<>();

    @Override
    public void update(Move m) {
        moveList.add(m);
    }
}
