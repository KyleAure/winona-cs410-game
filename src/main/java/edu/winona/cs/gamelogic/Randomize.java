package edu.winona.cs.gamelogic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Randomize {
    //private static final Log LOG = new Log(Randomize.class.getName());

    //TODO change from Point to BufferedImage
    public List<Point> userList = new ArrayList<>();

    public List<Point> randomize() {
        //working randomize to return solvable list
        
        //temporary randomize logic
        Collections.shuffle(userList);
        return userList;
    }
}
