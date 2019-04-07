package edu.winona.cs.gamelogic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Randomize {

    //private static final Log LOG = new Log(Randomize.class.getName());
    public List<BufferedImage> userList = new ArrayList<>();

    public Randomize() {
    }
    
    public List<BufferedImage> randomize(List<BufferedImage> userList) {
        //working randomize to return solvable list

        //temporary randomize logic
        Collections.shuffle(userList);
        return userList;
    }
}
