package edu.winona.cs.gamelogic;

import edu.winona.cs.log.Log;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveLogic {

    private static final Log LOG = new Log("WinCondition");

    private List<Point> keyList = new ArrayList<Point>();
    private List<Point> userList = new ArrayList<Point>();

    //keyList  = PLACEHOLDER.getImageCoordinateList();
    //userList = PLACEHOLDER.getRandomizedCoordinateList();

    public List<Point> move(Space moveFrom, Space moveTo){
        
        Space temp = moveFrom;
        
        int indexMoveFrom = userList.indexOf(moveFrom);
        int indexMoveTwo = userList.indexOf(moveTo);
        
        //check for 
        
        //check adjacency
        if(temp.isAdjacent(moveTo)){
            Collections.swap(userList, indexMoveFrom, indexMoveTwo);
        }
        
        //compareLists(keyList, userList) ???
       
        return userList;
    }
    
    public boolean compareLists(List<Point> keyList, List<Point> userList) {
        if(keyList == userList) {
            return true;
            //insert code for game over, display win screen GUI
        } else {
            return false;
        }
    }
}
