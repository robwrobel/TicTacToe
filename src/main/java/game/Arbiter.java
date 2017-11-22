package game;

import configuration.Board;
import configuration.Mark;
import configuration.MoveObserver;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arbiter {

    private int noForWin;
    private boolean victory;

    public void setNoForWin(int noForWin) {
        this.noForWin = noForWin;
    }

    public boolean isVictory() {

        return victory;
    }

    public void update(List<String> sequences, String mark) {
        checkForVictory(sequences, mark);
    }

    private void checkForVictory(List<String> sequences, String mark) {
        for (String sequence: sequences) {
            if (isSequenceVictory(sequence, mark)) {
                victory = true;
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
        for (int i=1; i < noForWin; i++) {
            sb.append("\\1");
        }
        return sb.toString();
    }
}
