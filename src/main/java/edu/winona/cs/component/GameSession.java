package edu.winona.cs.component;

import java.io.Serializable;

import edu.winona.cs.gamelogic.DifficultyLevel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Variables here match those stored in the SaveStateTable. fileURL (String) -
 * Location of serialized file
 *
 * Within serialized file difficulty (DifficultyLevel) clickCount (number of
 * clicks)
 *
 * @author Kyle Aure
 * @version 1.0
 *
 */
public class GameSession implements Serializable {
    //Used to serialize the GameSession to be loaded again later

    private static final long serialVersionUID = -5135624122335249008L;

    //Logger
    //private static final Log LOG = new Log(GameSession.class.getName());

    //Variables 
    private DifficultyLevel difficulty;
    private int clickCount;
    public List<BufferedImage> imageList = new ArrayList<>();
    public List<BufferedImage> keyList = imageList;

    public GameSession(String username, DifficultyLevel level, int clickCount, List<BufferedImage> imageList, List<BufferedImage> keyList) {
        this.difficulty = level;
        this.clickCount = 0;
        this.imageList = imageList;
        this.keyList = keyList;
    }

    public GameSession(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
    
    public void incrementClick() {
    	clickCount++;
    }

    public void setImageList(List<BufferedImage> imageList) {
        this.imageList = imageList;
    }
    
    public List<BufferedImage> getImageList() {
    	return imageList;
    }

    public void setKeyList(List<BufferedImage> keyList) {
        this.keyList = keyList;
    }
    
    public List<BufferedImage> getKeyList() {
    	return keyList;
    }

    @Override
    public String toString() {
        return "Difficulty: " + difficulty;
    }

}
