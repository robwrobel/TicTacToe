package game;

import configuration.Mark;
import configuration.Player;
import inout.InputReader;
import inout.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int NO_OF_MATCHES_IN_GAME = 3;
    OutputWriter ow = new OutputWriter();
    InputReader ir = new InputReader();

    List<Player> players = new ArrayList<>();
    {
        players.add(new Player(Mark.O));
        players.add(new Player(Mark.X));
    }

    Scores scores = new Scores(players);

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    private void start() {
        printWelcomeMessage();
        setPlayersNames();
        for (int i=1; i <= NO_OF_MATCHES_IN_GAME; i++) {
            new Match(ow,ir,players, scores).start();
        }
    }

    private void printWelcomeMessage() {
        ow.println("New Game Started!");
    }

    private void setPlayersNames() {
        int i=1;
        for(Player p: players) {
            System.out.println("Please enter player "+ i + " name:");
            p.setName(ir.getString());
            i++;
        }
    }
}
