package game;

import configuration.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arbiter {

    private final List<Player> players;
    private int winSequenceLength;
    private boolean matchVictory;
    private Scores scores;
    
    public Arbiter(Scores scores, List<Player> players) {
        this.scores = scores;
        this.players = players;
    } 
    public void setWinSequenceLength(int winSequenceLength) {
        this.winSequenceLength = winSequenceLength;
    }

    public boolean isMatchVictory() {

        return matchVictory;
    }

    public void clearMatchVictory() {
        matchVictory = false;
    }

    public void update(List<String> sequences, String mark) {
        checkForVictory(sequences, mark);
    }

    private void checkForVictory(List<String> sequences, String mark) {
        for (String sequence: sequences) {
            if (isSequenceVictory(sequence, mark)) {
                matchVictory = true;
                break;
            }
        }
    }

    private boolean isSequenceVictory(String sequence, String mark) {
        return areFoundConsecutiveMarks(sequence,mark);
    }

    private boolean areFoundConsecutiveMarks(String sequence, String mark) {
        String regex = createRegex(mark);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sequence);
        return matcher.find();
    }

    private String createRegex(String mark) {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + mark + ")");
        for (int i = 1; i < winSequenceLength; i++) {
            sb.append("\\1");
        }
        return sb.toString();
    }

    public int getWinSequenceLength() {
        return winSequenceLength;
    }

    public Scores getScores() {
        return scores;
    }


    public boolean isGameResultDraw() {

        int highestScore = scores.getHighestScore();
        boolean isDraw=true;

        for(Player p: players) {
            if ( scores.getScoreForPlayer(p) < highestScore ) {
                return false;
            }
        }
        return true;
    }

    public Player whoWinsTheGame() {
        int highestScore = scores.getHighestScore();
        for (Player p: players) {
            if (scores.getScoreForPlayer(p) == highestScore) {
                return p;
            }
        }
        return null;
    }
}
