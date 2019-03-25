package edu.winona.cs.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdjacencyListMaker {
	
	List<Space> tempList = new ArrayList<Space>();
	
	Space new1;
	Space new2;
	Space new3;
	Space new4;
	
	public void makeEasyAdjacencyList(Space s) { //creates adjacency list for spaces in easy mode
		
		int temp = s.getSpaceNumber();
		
		switch(temp) { //uses index number of each space to determine which case to build an adjacency list with
		case 1:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(4);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 2:
			new1.setSpaceNumber(1);
			new2.setSpaceNumber(3);
			new3.setSpaceNumber(5);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 3:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(6);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 4:
			new1.setSpaceNumber(1);
			new2.setSpaceNumber(5);
			new3.setSpaceNumber(7);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 5:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(4);
			new3.setSpaceNumber(6);
			new4.setSpaceNumber(8);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 6:
			new1.setSpaceNumber(3);
			new2.setSpaceNumber(5);
			new3.setSpaceNumber(9);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 7:
			new1.setSpaceNumber(4);
			new2.setSpaceNumber(8);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 8:
			new1.setSpaceNumber(5);
			new2.setSpaceNumber(7);
			new3.setSpaceNumber(9);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 9:
			new1.setSpaceNumber(6);
			new2.setSpaceNumber(8);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		default:
			break;
		}
		
	}
	
	public void makeMediumAdjacencyList(Space s) { //creates adjacency list for spaces in medium mode
		
		int temp = s.getSpaceNumber();
		
		switch(temp) { //uses index number of each space to determine which case to build an adjacency list with
		case 1:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(5);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 2:
			new1.setSpaceNumber(1);
			new2.setSpaceNumber(3);
			new3.setSpaceNumber(6);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 3:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(4);
			new3.setSpaceNumber(7);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 4:
			new1.setSpaceNumber(3);
			new2.setSpaceNumber(8);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 5:
			new1.setSpaceNumber(1);
			new2.setSpaceNumber(6);
			new3.setSpaceNumber(9);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 6:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(5);
			new3.setSpaceNumber(7);
			new4.setSpaceNumber(10);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 7:
			new1.setSpaceNumber(3);
			new2.setSpaceNumber(6);
			new3.setSpaceNumber(8);
			new4.setSpaceNumber(11);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 8:
			new1.setSpaceNumber(4);
			new2.setSpaceNumber(7);
			new3.setSpaceNumber(12);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 9:
			new1.setSpaceNumber(5);
			new2.setSpaceNumber(10);
			new3.setSpaceNumber(13);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 10:
			new1.setSpaceNumber(6);
			new2.setSpaceNumber(9);
			new3.setSpaceNumber(11);
			new4.setSpaceNumber(14);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 11:
			new1.setSpaceNumber(7);
			new2.setSpaceNumber(10);
			new3.setSpaceNumber(12);
			new4.setSpaceNumber(15);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 12:
			new1.setSpaceNumber(8);
			new2.setSpaceNumber(11);
			new3.setSpaceNumber(16);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 13:
			new1.setSpaceNumber(9);
			new2.setSpaceNumber(14);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 14:
			new1.setSpaceNumber(10);
			new2.setSpaceNumber(13);
			new3.setSpaceNumber(15);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 15:
			new1.setSpaceNumber(11);
			new2.setSpaceNumber(14);
			new3.setSpaceNumber(16);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 16:
			new1.setSpaceNumber(12);
			new2.setSpaceNumber(15);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		default:
			break;
		}
		
	}

	public void makeHardAdjacencyList(Space s) { //creates adjacency list for spaces in hard mode
	
		int temp = s.getSpaceNumber();
		
		switch(temp) { //uses index number of each space to determine which case to build an adjacency list with
		case 1:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(6);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 2:
			new1.setSpaceNumber(1);
			new2.setSpaceNumber(3);
			new3.setSpaceNumber(7);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 3:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(4);
			new3.setSpaceNumber(8);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 4:
			new1.setSpaceNumber(3);
			new2.setSpaceNumber(5);
			new3.setSpaceNumber(9);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 5:
			new1.setSpaceNumber(4);
			new2.setSpaceNumber(10);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 6:
			new1.setSpaceNumber(1);
			new2.setSpaceNumber(7);
			new3.setSpaceNumber(11);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 7:
			new1.setSpaceNumber(2);
			new2.setSpaceNumber(6);
			new3.setSpaceNumber(8);
			new4.setSpaceNumber(12);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 8:
			new1.setSpaceNumber(3);
			new2.setSpaceNumber(7);
			new3.setSpaceNumber(9);
			new4.setSpaceNumber(13);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 9:
			new1.setSpaceNumber(4);
			new2.setSpaceNumber(8);
			new3.setSpaceNumber(10);
			new4.setSpaceNumber(14);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 10:
			new1.setSpaceNumber(5);
			new2.setSpaceNumber(9);
			new3.setSpaceNumber(15);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 11:
			new1.setSpaceNumber(6);
			new2.setSpaceNumber(12);
			new3.setSpaceNumber(16);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 12:
			new1.setSpaceNumber(7);
			new2.setSpaceNumber(11);
			new3.setSpaceNumber(13);
			new4.setSpaceNumber(17);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 13:
			new1.setSpaceNumber(8);
			new2.setSpaceNumber(12);
			new3.setSpaceNumber(14);
			new4.setSpaceNumber(18);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 14:
			new1.setSpaceNumber(9);
			new2.setSpaceNumber(13);
			new3.setSpaceNumber(15);
			new4.setSpaceNumber(19);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 15:
			new1.setSpaceNumber(10);
			new2.setSpaceNumber(14);
			new3.setSpaceNumber(20);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 16:
			new1.setSpaceNumber(11);
			new2.setSpaceNumber(17);
			new3.setSpaceNumber(21);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 17:
			new1.setSpaceNumber(12);
			new2.setSpaceNumber(16);
			new3.setSpaceNumber(18);
			new4.setSpaceNumber(22);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 18:
			new1.setSpaceNumber(13);
			new2.setSpaceNumber(17);
			new3.setSpaceNumber(19);
			new4.setSpaceNumber(23);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 19:
			new1.setSpaceNumber(14);
			new2.setSpaceNumber(18);
			new3.setSpaceNumber(20);
			new4.setSpaceNumber(24);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			tempList.add(new4);
			
			s.setAdjacencyList(tempList);
			break;
		case 20:
			new1.setSpaceNumber(15);
			new2.setSpaceNumber(19);
			new3.setSpaceNumber(25);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 21:
			new1.setSpaceNumber(16);
			new2.setSpaceNumber(22);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		case 22:
			new1.setSpaceNumber(17);
			new2.setSpaceNumber(21);
			new3.setSpaceNumber(23);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 23:
			new1.setSpaceNumber(18);
			new2.setSpaceNumber(22);
			new3.setSpaceNumber(24);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 24:
			new1.setSpaceNumber(19);
			new2.setSpaceNumber(23);
			new3.setSpaceNumber(25);
			
			tempList.add(new1);
			tempList.add(new2);
			tempList.add(new3);
			
			s.setAdjacencyList(tempList);
			break;
		case 25:
			new1.setSpaceNumber(20);
			new2.setSpaceNumber(24);
			
			tempList.add(new1);
			tempList.add(new2);
			
			s.setAdjacencyList(tempList);
			break;
		default:
			break;
		}
	
	}
	
}
