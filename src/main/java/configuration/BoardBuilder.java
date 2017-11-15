package configuration;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {
    List<Mark> list;
    BoardDimensions bd;

    public BoardBuilder (BoardDimensions bd) {
        this.bd = bd;
    }

    public BoardBuilder viaArrayList() {
        list = new ArrayList<>();
        for (int i = 0; i < bd.columns*bd.rows; i++) {
            list.add(Mark.EMPTY);
        }
        return this;
    }

    public Board build() {
        return new Board(list,bd);
    }
}
