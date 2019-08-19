package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.oulu.tol.sqat.BowlingException;
import tol.sqat.session6.*;

public class US05 {

	@Test
	public void testStartWithAStrikeForm() throws InvalidInput, InvalidFrameStateException {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(10,0));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(0,0));
		game.addFrame(new Frame(4,2));
		game.addFrame(new Frame(8,0));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(52, game.score());
	}
	@Test
	public void testStartWithAStrikeContent() throws InvalidInput, InvalidFrameStateException {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(10,0));
		game.addFrame(new Frame(2,5));
		game.addFrame(new Frame(0,0));
		game.addFrame(new Frame(4,2));
		game.addFrame(new Frame(8,0));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(62, game.score());
	}
	@Test
	public void testStrikeInTheMiddleForm() throws InvalidInput, InvalidFrameStateException {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(4,5));
		game.addFrame(new Frame(2,4));
		game.addFrame(new Frame(10,0));
		game.addFrame(new Frame(1,8));
		game.addFrame(new Frame(0,5));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(72, game.score());
	}
	@Test
	public void testStrikeInTheMiddleContent() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(5,1));
		game.addFrame(new Frame(7,2));
		game.addFrame(new Frame(10,0));
		game.addFrame(new Frame(9,0));
		game.addFrame(new Frame(0,5));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(72, game.score());
	}
	@Test
	public void testSingleStrikeZeroContentsForm() throws InvalidInput, InvalidFrameStateException  {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(10,0));
		game.addFrame(new Frame(0,0));
		game.addFrame(new Frame(0,0));
		game.addFrame(new Frame(4,2));
		game.addFrame(new Frame(8,0));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(48, game.score());
	}

	

}
