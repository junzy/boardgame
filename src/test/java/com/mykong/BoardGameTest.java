package com.mykong;

import static org.junit.Assert.assertEquals;

import com.bol.model.BoardGame;
import com.bol.model.Pit;
import com.bol.model.PitType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BoardGameTest {

	BoardGame boardGame;

	@Test
	public void toggleActivePlayerTest() {
		boardGame = new BoardGame("P1", "P2");
		assertEquals("P1", boardGame.getCurrentActivePlayer().getName());
		boardGame.toggleActivePlayer();
		assertEquals("P2", boardGame.getCurrentActivePlayer().getName());
	}
	
	// ensure that all pits are created with the proper starting count
	@Test
	public void createPitsTest() {
		boardGame = new BoardGame("P1", "P2");
		boardGame.createPits();
		List<Pit> pits = boardGame.getPits();
		for (Pit pit: pits) {
			if(pit.getType() == PitType.NORMAL) {
				assertEquals((Integer)boardGame.NORMAL_PIT_STARTING_COUNT, pit.getStoneCount());
			}
		}
	}
	
	@Test
	public void makeMoveTest() {
		boardGame = new BoardGame("P1", "P2");
		boardGame.createPits();
		boardGame.makeMove("0"); // player1's move on the first pit
		//validate game state
		List<Pit> pits = boardGame.getPits();
		// validate that after first move the state of the game is valid
		assertEquals((Integer)0, (Integer)pits.get(0).getStoneCount());
		assertEquals((Integer)7, (Integer)pits.get(1).getStoneCount());
		assertEquals((Integer)7, (Integer)pits.get(2).getStoneCount());
		assertEquals((Integer)7, (Integer)pits.get(3).getStoneCount());
		assertEquals((Integer)7, (Integer)pits.get(4).getStoneCount());
		assertEquals((Integer)7, (Integer)pits.get(5).getStoneCount());
		assertEquals((Integer)1, (Integer)pits.get(6).getStoneCount());
	}
	
	// Tests if the current active player is set properly at game start
	@Test
	public void currentActivePlayerSetTest() {
		boardGame = new BoardGame("P1", "P2");
		assertEquals("P1", boardGame.getCurrentActivePlayer().getName());
	} 
	
	
	
	
	
}
