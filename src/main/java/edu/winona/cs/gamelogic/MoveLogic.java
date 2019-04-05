package edu.winona.cs.gamelogic;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representation of the image data structure which holds the Points that
 * represent image location on board, and the key to check if user has solved
 * the puzzle.
 */
public class MoveLogic {
    //private static final Log LOG = new Log(MoveLogic.class.getName());

    private List<BufferedImage> keyList = new ArrayList<BufferedImage>();
    private List<BufferedImage> userList = new ArrayList<BufferedImage>();

    //keyList  = PLACEHOLDER.getImageCoordinateList();
    //userList = PLACEHOLDER.getRandomizedCoordinateList();
    public List<BufferedImage> move(Space moveFrom, Space moveTo) {

        Space temp = moveFrom;

        int indexMoveFrom = userList.indexOf(moveFrom);
        int indexMoveTwo = userList.indexOf(moveTo);
        
        //check adjacency
        if (temp.isAdjacent(moveTo)) {
            Collections.swap(userList, indexMoveFrom, indexMoveTwo);
        }

        //TODO check win
        hasWon();

        return userList;
    }

    public boolean hasWon() {
        if (keyList == userList) {
            return true;
            //insert code for game over, display win screen GUI
        } else {
            return false;
        }
    }
}
