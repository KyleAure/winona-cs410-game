package edu.winona.cs.gamelogic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eu3035jm
 */
public class Space {
    
    public int spaceNumber;
    public List<Space> adjacencyList = new ArrayList<Space>();
    public Boolean isOpen = false;
    
    public Space() {
    }

    public Space(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public List<Space> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(List<Space> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public Boolean isAdjacent(Space moveTo) {
        if(adjacencyList.contains(moveTo)){
            return true;
        }
        return false;
    }

    public Boolean isOpen(Space moveTo) {
        return isOpen;
    }

}
