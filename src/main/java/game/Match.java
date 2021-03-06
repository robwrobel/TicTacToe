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

    public Match(OutputWriter ow, InputReader ir, List<Player> playerList, Arbiter arbiter) {
        this.ow = ow;
        this.ir = ir;
        this.playerList = playerList;
        this.arbiter = arbiter;
    }

    public void start() {

        whoBegins();

        initializeBoard();

        moveValidator = new MoveValidator(board);

        sequenceGeneratorManager = new SequenceGeneratorManager(board, arbiter);

        initializeObservers();

        getWinSequenceLength();

        int maxNoOfMoves = board.getMaxId() + 1;
        for(int i = 1; i <= maxNoOfMoves;i++) {
            displayBoard();
            keepAskingPlayerForNewMove();
            if (arbiter.isMatchVictory()) {
                break;
            }
            switchPlayer();
        }
        displayBoard();
        if (arbiter.isMatchVictory()) {
            arbiter.getScores().updateScoreForWinner(tour.currentPlayer);
            announceWhoWins();
            arbiter.clearMatchVictory();
        }  else {
            arbiter.getScores().updateScoreForDraw();
            announceDraw();
        }

    }

    private void announceDraw() {
        ow.println(Game.resourceBundle.getString("WeHaveADraw"));
    }

    private void announceWhoWins() {
        ow.println(Game.resourceBundle.getString("TheWinnerOfTheMatch") + ": " + tour.currentPlayer);
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

    private void getWinSequenceLength() {

        int maxWinSequenceLength = Math.max(board.getBd().getColumns()
                                            , board.getBd().getRows());
        ow.println(Game.resourceBundle.getString("WinSequenceLength")+": (3-"+ maxWinSequenceLength + ")");
        arbiter.setWinSequenceLength(ir.getInt(3, maxWinSequenceLength));
    }
    private void initializeBoard() {

        ow.println(Game.resourceBundle.getString("NoOfColumn")+": ("
                + BoardDimensions.MIN_NO_OF_COLUMNS
                + "-"
                + BoardDimensions.MAX_NO_OF_COLUMNS
                + ")");
        int col = ir.getInt(BoardDimensions.MIN_NO_OF_COLUMNS, BoardDimensions.MAX_NO_OF_COLUMNS );

        ow.println(Game.resourceBundle.getString("NoOfRows")+": ("
                + BoardDimensions.MIN_NO_OF_ROWS
                + "-"
                + BoardDimensions.MAX_NO_OF_ROWS
                + ")");
        int row = ir.getInt(BoardDimensions.MIN_NO_OF_ROWS, BoardDimensions.MAX_NO_OF_ROWS);

        BoardDimensions bd = new BoardDimensions(col,row);
        BoardBuilder bb = new BoardBuilder(bd);
        board = bb.viaArrayList().build();

    }

    private void whoBegins() {
        ow.println(Game.resourceBundle.getString("WhoBegins")
                +" (1 - "
                + Game.resourceBundle.getString("Player")
                +"1: " + playerList.get(0)
                + " , 2 - "
                + Game.resourceBundle.getString("Player")
                +"2: "+playerList.get(1)+ ")");
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
        ow.println( Game.resourceBundle.getString("Player")
                +" "
                + tour.currentPlayer
                + " "
                + Game.resourceBundle.getString("move"));
        ow.println(Game.resourceBundle.getString("EnterId"));
        int id = ir.getInt();
        Move move = new Move(tour.currentPlayer.getMark(),id);
        tour.setMove(move);
    }

}
