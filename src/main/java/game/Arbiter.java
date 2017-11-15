package game;

import configuration.MoveObserver;

public class Arbiter implements MoveObserver{

    int noForWin;
    @Override
    public void update(Move m) {

    }

    public void setNoForWin(int noForWin) {
        this.noForWin = noForWin;
    }
}
