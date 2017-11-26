package game;

import configuration.Board;
import configuration.MoveObserver;

import java.util.*;

public class SequenceGeneratorManager implements MoveObserver {

    Arbiter arbiter;

    List<SequenceGenerator> sequenceGeneratorList = new ArrayList<>();


    public SequenceGeneratorManager(Board board, Arbiter arbiter) {
        this.arbiter =  arbiter;

        sequenceGeneratorList.add(new HorizontalSequenceGenerator(board, arbiter));
        sequenceGeneratorList.add(new VerticalSequenceGenerator(board, arbiter));
        sequenceGeneratorList.add(new DiagonalSequenceGenerator(board, arbiter));
        sequenceGeneratorList.add(new AntiDiagonalSequenceGenerator(board, arbiter));
    }

    public void update(Move m) {
        List<String> sequences = generateSequences(m.getId());
        arbiter.update(sequences,m.getMark().toString());
    }

    private List<String> generateSequences(int id) {
        List<String> sequenceList = new ArrayList<>();

        for(SequenceGenerator sequenceGenerator: sequenceGeneratorList)
            sequenceList.add(sequenceGenerator.generate(id));

        return sequenceList;
    }












}
