package edu.winona.cs.db;

public class SaveStateTable implements Table{

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCreated() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//TODO Create a Save State table which is generated any time the program is closed mid-game.
	//TODO TableName SaveState
	//TODO Columns Username - String, Location of Serialized File - String, clickCount - int
}
