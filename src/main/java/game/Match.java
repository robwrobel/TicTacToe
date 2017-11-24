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

    private Arbiter arbiter;
    private Board board;
    private MoveValidator moveValidator;
    private SequenceGeneratorManager sequenceGeneratorManager;
    private List<Player> playerList;
    private Tour tour;
    private Scores scores;

    public Match(OutputWriter ow, InputReader ir, List<Player> playerList, Scores scores) {
        this.ow = ow;
        this.ir = ir;
        this.playerList = playerList;
        this.scores = scores;
    }

    public void start() {

        whoBegins();

        initializeBoard();

        moveValidator = new MoveValidator(board);
        arbiter = new Arbiter();
        sequenceGeneratorManager = new SequenceGeneratorManager(board, arbiter);

        initializeObservers();

        getNoOfConsecutiveMarksToWin();

        int maxNoOfMoves = board.getMaxId() + 1;
        for(int i = 1; i <= maxNoOfMoves;i++) {
            displayBoard();
            keepAskingPlayerForNewMove();
            if (arbiter.isVictory()) {
                break;
            }
            switchPlayer();
        }
        displayBoard();
        if (arbiter.isVictory()) {
            scores.updateScoreForWinner(tour.currentPlayer);
            announceWhoWins();
        }  else {
            scores.updateScoreForDraw();
            announceDraw();
        }

    }

    private void announceDraw() {
        ow.println("We have a draw");
    }

    private void announceWhoWins() {
        ow.println("The winner of the match is:" + tour.currentPlayer.getName());
    }

    private void initializeObservers() {
        moveValidator.addObserver(board);
        moveValidator.addObserver(sequenceGeneratorManager);
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

    private void getNoOfConsecutiveMarksToWin() {
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
