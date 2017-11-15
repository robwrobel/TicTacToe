package game;

import configuration.Mark;

public class Move {
    Mark mark;
    int id;

    public Move(Mark mark, int id) {
        this.mark = mark;
        this.id = id;
    }

    public Mark getMark() {
        return mark;
    }

    public int getId() {
        return id;
    }
}
