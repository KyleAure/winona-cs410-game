package edu.winona.cs.gamelogic;

import java.util.Collections;
import java.util.List;

public final class Randomize {

    public Randomize() {
    }
    
    public List<Integer> randomize(List<Integer> orderedList) {
        //working randomize to return solvable list

        //temporary randomize logic
        Collections.shuffle(orderedList);
        return orderedList;
    }
}
