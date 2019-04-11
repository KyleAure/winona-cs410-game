package edu.winona.cs.gamelogic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of the image data structure which holds the Points that
 * represent image location on board, and the key to check if user has solved
 * the puzzle.
 */
@Deprecated
public class MoveLogic {
    //private static final Log LOG = new Log(MoveLogic.class.getName());

    private List<BufferedImage> keyList = new ArrayList<BufferedImage>();
    private List<BufferedImage> userList = new ArrayList<BufferedImage>();

    public List<BufferedImage> move(List<BufferedImage> userList, Space moveFrom, Space moveTo) {
        
//        Space temp = moveFrom;
//
//        //resolve these errors
//        int indexMoveFrom = userList.indexOf(moveFrom);
//        int indexMoveTwo = userList.indexOf(moveTo);
//
//        //check adjacency
//        if (temp.isAdjacent(moveTo)) {
//            Collections.swap(userList, indexMoveFrom, indexMoveTwo);
//        }
//
//        hasWon();

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
