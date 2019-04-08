package edu.winona.cs.gamelogic;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DifficultyLevel {
         TEST(2),
	 EASY(3),
	 MEDIUM(4),
	 HARD(5);

	 private static final Map<Integer,DifficultyLevel> lookup = new HashMap<Integer,DifficultyLevel>();

	 static {
	      for(DifficultyLevel level : EnumSet.allOf(DifficultyLevel.class))
	           lookup.put(level.getDifficulty(), level);
	 }

	 private int difficulty;

	 private DifficultyLevel(int difficulty) {
	      this.difficulty = difficulty;
	 }

	 public int getDifficulty() { return difficulty; }

	 public static DifficultyLevel get(int difficulty) { 
	      return lookup.get(difficulty); 
	 }
}
