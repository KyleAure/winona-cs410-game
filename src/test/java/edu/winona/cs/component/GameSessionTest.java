package edu.winona.cs.component;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.junit.Test;

import edu.winona.cs.gamelogic.DifficultyLevel;

public class GameSessionTest {
	
	private GameSession session;
	
	@Test
	public void testJSONBinding() {
		//Create session
		session = new GameSession(DifficultyLevel.HARD, 0, "");
		System.out.println(session);
		
		//Bind to JSON
		Jsonb jsonb = JsonbBuilder.create();
    	String result = jsonb.toJson(session);
    	System.out.println(result);
    	
    	//Get session
    	GameSession sessionBack = jsonb.fromJson(result, GameSession.class);
    	System.out.println(sessionBack);
	}

}
