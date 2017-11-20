package game;

import configuration.Board;
import configuration.BoardBuilder;
import configuration.BoardDimensions;
import configuration.Player;
import inout.InputReader;
import inout.OutputWriter;

import java.util.List;

public class Match {
    private OutputWriter ow;
    private InputReader ir;
    private Archive archive;
    private Arbiter arbiter;


    private Board board;
    private MoveValidator moveValidator;

    private List<Player> playerList;
    private Tour tour;

    public Match(OutputWriter ow, InputReader ir, List<Player> playerList) {
        this.ow = ow;
        this.ir = ir;
        this.playerList = playerList;
    }

    public void start() {
        whoBegins();
        initializeBoard();
        initializeArchive();
        initializeArbiter();
        noForWin();
        do {
            displayBoard();
            keepAskingPlayerForNewMove();
            board.update(tour.move);
            if (arbiter.isMatchFinished()) break;
            switchPlayer();
        } while (true);
        displayBoard();
    }

    private void switchPlayer() {
        if (tour.currentPlayer == playerList.get(0)) {
            tour.currentPlayer = playerList.get(1);
        } else {
            tour.currentPlayer = playerList.get(0);
        }
    }

    private void displayBoard() {
        ow.println(board.toString());
    }

    private void noForWin() {
        ow.println("Please enter number of consecutive marks for win");
        arbiter.setNoForWin(ir.getInt());
    }

    private void initializeBoard() {

        ow.println("Please enter number of columns");
        int col = ir.getInt();

        ow.println("Please enter number of rows");
        int row = ir.getInt();

        BoardDimensions bd = new BoardDimensions(col,row);
        BoardBuilder bb = new BoardBuilder(bd);
        board = bb.viaArrayList().build();

    }

    private void initializeArchive() {
        archive = new Archive();
        board.addObserver(archive);
    }

    private void initializeArbiter() {
        arbiter = new Arbiter(archive,board);
        board.addObserver(arbiter);
    }

    private void whoBegins() {
        ow.println("Please select who begins (1 - Player1: " + playerList.get(0).getName() +
                   " , 2 - Player2: "+playerList.get(1).getName()+ ")");
        int input;
        do {
            input = ir.getInt();
        } while (input != 1 && input != 2);
        if ( input == 1 ) {
            tour = new Tour(playerList.get(0),null);
        } else {
            tour = new Tour(playerList.get(1),null);
        }
    }

    private void keepAskingPlayerForNewMove() {
        do {
            askUserForNewMove();
            moveValidator = new MoveValidator(board);
        } while (!moveValidator.isMoveValid(tour.move));
    }

    private void askUserForNewMove() {
        ow.println("Now player: " + tour.currentPlayer.getName() + " move");
        ow.println("Please enter id");
        int id = ir.getInt();
        Move move = new Move(tour.currentPlayer.getMark(),id);
        tour.setMove(move);
    }

}
