package game;

import configuration.Mark;
import configuration.Player;
import inout.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int NO_OF_MATCHES_IN_GAME = 3;
    OutputWriter ow;
    InputReader ir;

    List<Player> players = new ArrayList<>();
    {
        players.add(new Player(Mark.O));
        players.add(new Player(Mark.X));
    }

    Scores scores = new Scores(players);
    Arbiter arbiter = new Arbiter(scores, players);

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    private void start() {
        setInOut();
        printWelcomeMessage();

        setPlayersNames();
        for (int i=1; i <= NO_OF_MATCHES_IN_GAME; i++) {
            new Match(ow,ir,players, arbiter).start();
            scores.display();
        }
        displayGameResults();
    }

    private void setInOut() {

        String InOut = System.getProperty("InOut","system");

        switch (InOut.toLowerCase()) {
            case "system" :
                ow = new SystemOutOutputWriter();
                ir = new SystemInInputReader();
                break;
            case "file" :
                ow = new FileOutputWriter();
                ir = new FileInputReader();
                break;
        }

    }

    private void displayGameResults() {
        ow.println("The match is over!");
        if (arbiter.isGameResultDraw()) {
            ow.println("Final match result: DRAW");
        } else {
            ow.println("And the WINNER is: " + arbiter.whoWinsTheGame());
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
