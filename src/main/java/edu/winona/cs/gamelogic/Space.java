
package edu.winona.cs.gamelogic;

/**
 *
 * @author eu3035jm
 */ 
public abstract interface Space {
    
    /**
     * Is adjacent
     *
     * @param moveTo This is the space the user wants the square to move to.   
     * @require The space to be empty.
     * @ensure Verifies if the square can move to the empty space.
     * @return Returns a boolean value if the square can move to the space.
     */
    public Boolean isAdjacent(Space moveTo);
    
}
