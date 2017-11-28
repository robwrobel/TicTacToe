package game;

import configuration.Mark;
import configuration.Player;
import inout.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Game {

    private static final int NO_OF_MATCHES_IN_GAME = 3;
    public static ResourceBundle resourceBundle ;
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
        setLanguage();
        printWelcomeMessage();
        setPlayersNames();
        for (int i=1; i <= NO_OF_MATCHES_IN_GAME; i++) {
            new Match(ow,ir,players, arbiter).start();
            ow.println(scores.toString());
            if (!isNewMatch()) break;
        }
        displayGameResults();
    }

    private void setLanguage() {
        ow.println("For English type 1 <<<<>>>>> Język polski wpisz 2");
        int i = ir.getInt(1,2);
        Locale locale;
        switch (i) {
            case 1:
                locale = new Locale("en", "US");
                break;
            case 2:
                locale = new Locale("pl", "PL");
                break;
            default:
                locale = new Locale("en", "US");
        }

        resourceBundle = ResourceBundle.getBundle("Messages", locale);

    }

    private boolean isNewMatch() {
        ow.println(resourceBundle.getString("AnotherMatch"));
        String s;
        s = ir.getYesNoString();

        if (s.equals(resourceBundle.getString("yes")))
            return true;
        else
            return false;
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
                ir = new SystemInInputReader(ow instanceof SystemOutOutputWriter ? new DummyOutputWriter():ow);
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
        ow.println(resourceBundle.getString("TheMatchIsOver"));
        if (arbiter.isGameResultDraw()) {
            ow.println(resourceBundle.getString("FinalMatchResultDraw"));
        } else {
            ow.println(resourceBundle.getString("AndTheWinnerIs")+": " + arbiter.whoWinsTheGame());
        }
    }

    private void printWelcomeMessage() {
        ow.println(resourceBundle.getString("NewGameStarted"));
    }

    private void setPlayersNames() {
        int i=1;
        for(Player p: players) {
            ow.println(resourceBundle.getString("NameOfPlayer") + " "+  i );
            p.setName(ir.getNonEmptyString());
            i++;
        }
    }
}
