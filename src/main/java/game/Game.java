package game;

import configuration.Mark;
import configuration.Player;
import inout.*;

import java.io.FileNotFoundException;
import java.io.IOException;
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
            ow.println(scores.toString());
        }
        displayGameResults();
    }

    private void setInOut() {

        String In = System.getProperty("In","system");
        String Out = System.getProperty("Out","system");

        switch (Out.toLowerCase()) {
            case "system" :
                ow = new SystemOutOutputWriter();
                break;
            case "file" :
                try {
                    ow = new FileOutputWriter();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

        switch (In.toLowerCase()) {
            case "system" :
                ir = new SystemInInputReader();
                break;
            case "file" :
                try {
                    ir = new FileInputReader(ow);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            ow.println("Please enter player "+ i + " name:");
            p.setName(ir.getString());
            i++;
        }
    }
}
