package game;

import configuration.Mark;
import configuration.Player;
import inout.InputReader;
import inout.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Game {

    OutputWriter ow = new OutputWriter();
    InputReader ir = new InputReader();

    List<Player> players = new ArrayList();
    {
        players.add(new Player(Mark.O));
        players.add(new Player(Mark.X));
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.start();
    }

    private void start() {
        printWelcomeMessage();
        setPlayersNames();
    }

    private void printWelcomeMessage() {
        ow.println("New Game Started!");
    }

    private void setPlayersNames() {
        int i=1;
        for(Player p: players) {
            System.out.println("Please enter player "+ i + " name:");
            p.setName(ir.getString());
        }
    }
}
