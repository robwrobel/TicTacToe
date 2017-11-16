package game;

import configuration.Player;

public class Tour {
    Player currentPlayer;
    Move move;

    public Tour(Player player, Move move) {
        this.currentPlayer = player;
        this.move = move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
