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

    public void display() {
        map.forEach((Player p, Integer score) -> System.out.println(p.getName() + " scored: " + score));
    }

    public void updateScoreForDraw() {
        for (Player p : map.keySet()) {
            map.merge(p, SCORE_FOR_DRAW , (i,j)-> i + j);
        }
    }

    public void updateScoreForWinner(Player currentPlayer) {
        map.merge(currentPlayer , SCORE_FOR_WIN , ( i,j ) -> i + j);
    }
}
