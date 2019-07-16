package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;

import org.junit.Test;

import tol.sqat.session6.*;


public class US08 {

	@Test
	public void testStartWithAMultipleStrikesForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(10,0)); //2
		game.addFrame(new Frame(0,0)); //1
		game.addFrame(new Frame(4,2)); //1
		game.addFrame(new Frame(8,0)); //1
		game.addFrame(new Frame(2,3)); //1
		game.addFrame(new Frame(1,3)); //1
		game.addFrame(new Frame(1,6)); //1
		game.addFrame(new Frame(2,0)); //1
		game.addFrame(new Frame(5,1)); //1
		assertEquals(68	,game.score());
	}
	@Test
	public void testStartWithAMultipleStrikesContent() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(10,0)); //2
		game.addFrame(new Frame(1,2)); //1
		game.addFrame(new Frame(4,2)); //1
		game.addFrame(new Frame(8,0)); //1
		game.addFrame(new Frame(2,3)); //1
		game.addFrame(new Frame(1,3)); //1
		game.addFrame(new Frame(1,6)); //1
		game.addFrame(new Frame(2,0)); //1
		game.addFrame(new Frame(5,1)); //1
		
		assertEquals(75, game.score());
	}
	@Test
	public void testMultipleStrikesInTheMiddleForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(4,5)); //1
		game.addFrame(new Frame(2,4)); //2
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(2,3)); //1
		game.addFrame(new Frame(1,3)); //1
		game.addFrame(new Frame(1,6)); //1
		game.addFrame(new Frame(2,0)); //1
		game.addFrame(new Frame(5,1)); //1
	
		assertEquals(106, game.score());
	}
	@Test
	public void testMultipleStrikeInTheMiddleContent() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(5,1)); //1
		game.addFrame(new Frame(7,2)); //2
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(2,3)); //1
		game.addFrame(new Frame(1,3)); //1
		game.addFrame(new Frame(1,6)); //1
		game.addFrame(new Frame(2,0)); //1
		game.addFrame(new Frame(5,1)); //1
		assertEquals(106, game.score());
	}
	@Test
	public void testMultipleStrikeInTheMiddleZeroBoniForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(1,5)); //1
		game.addFrame(new Frame(1,4)); //2
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(10,0)); //1
		game.addFrame(new Frame(0,0)); //1
		game.addFrame(new Frame(1,3)); //1
		game.addFrame(new Frame(1,6)); //1
		game.addFrame(new Frame(2,0)); //1
		game.addFrame(new Frame(5,1)); //1
		
		assertEquals(90, game.score()); //80
	}

	

}
