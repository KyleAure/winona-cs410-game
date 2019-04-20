package edu.winona.cs.component;

import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.image.ImageProcessor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;

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
public class GameSession {
	//JSON Variables
    @JsonbProperty
    private DifficultyLevel difficulty;
    @JsonbProperty
    private int clickCount;
    @JsonbProperty
    private String imgURL;
    @JsonbProperty
    private List<Integer> state;
    @JsonbProperty
    private List<Integer> key;

    @Override
	public String toString() {
		return "GameSession [difficulty=" + difficulty + ", clickCount=" + clickCount + ", imgURL=" + imgURL
				+ ", state=" + state + ", key=" + key + "]";
	}

	public GameSession(DifficultyLevel level, int clickCount, String imgURL) {
        this.difficulty = level;
        this.clickCount = clickCount;
        this.imgURL = imgURL;
        key = new ArrayList<>();
        state = new ArrayList<>();
        
        for(int i=0; i<level.getSqure(); i++) {
        	key.add(i);
        	state.add(i);
        }
    }
    
    public GameSession() {
    	//intentionally left black for JSON-B deserialization
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
    
    @JsonbTransient
    public void incrementClick() {
    	clickCount++;
    }

    public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public List<Integer> getState() {
		return state;
	}

	public void setState(List<Integer> img) {
		this.state = img;
	}

	public List<Integer> getKey() {
		return key;
	}

	public void setKey(List<Integer> key) {
		this.key = key;
	}

	public boolean isVictorious() {
		for(int i=0; i < state.size(); i++) {
			if(state.get(i) != key.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns a list of buffered images in the key order.
	 * @return
	 */
	public List<BufferedImage> loadIcons() {
		ImageProcessor ip = new ImageProcessor();
		File f = new File(imgURL);
		List<BufferedImage> fromFile = null;
		if(f.exists()) {
			try {
				ip.assignImage(f);
				fromFile = ip.divideImage(difficulty.getSqure());
			} catch (IOException e) {
				return null;
			}
			
			return fromFile;
		} else {
			return null;
		}
	}
}
