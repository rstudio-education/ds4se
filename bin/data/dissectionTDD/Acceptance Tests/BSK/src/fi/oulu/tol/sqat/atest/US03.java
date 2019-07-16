package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.oulu.tol.sqat.BowlingException;
import tol.sqat.session6.*;
public class US03 {

	@Test
	public void testGameWithFramesIsCreated() throws InvalidInput{
		BowlingGame game = new BowlingGame();
		for (int i = 1; i < 11; i++) {
			game.addFrame(new Frame(1,1));
		}
		
		assertNotNull(game);
	}
	
	@Test
	public void testEmptyGameIsCreated(){
		BowlingGame game = new BowlingGame();
		assertNotNull(game);
	}
	@Ignore
	@Test 
	public void testGameObjectIsCreated(){
		BowlingGame game = new BowlingGame();
		assertEquals("BowlingGame", game.getClass().getSimpleName());
	
	}
}
