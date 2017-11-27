package game;

import configuration.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scores {
    private static final int SCORE_FOR_WIN = 3;
    private static final int SCORE_FOR_DRAW = 1;

    Map<Player,Integer> map = new HashMap<>();

    Scores(List<Player> players) {
        players.forEach((Player p) -> map.put(p,0));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        map.forEach((Player p, Integer score) -> sb.append(p).append(" scored: ").append(score).append("\n"));
        return sb.toString();
    }

    public void updateScoreForDraw() {
        for (Player p : map.keySet()) {
            map.merge(p, SCORE_FOR_DRAW , (i,j)-> i + j);
        }
    }

    public void updateScoreForWinner(Player currentPlayer) {
        map.merge(currentPlayer , SCORE_FOR_WIN , ( i,j ) -> i + j);
    }

    public int getScoreForPlayer(Player player) {
        return map.get(player);
    }

    public int getHighestScore() {
        return map.keySet().stream().mapToInt((Player p) -> getScoreForPlayer(p)).max().getAsInt();
    }
}
