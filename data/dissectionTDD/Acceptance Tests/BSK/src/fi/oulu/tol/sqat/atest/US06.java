package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;

import org.junit.Test;

import tol.sqat.session6.*;


public class US06 {

	@Test
	public void testStartWithASpareForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(5,5));
		game.addFrame(new Frame(2,5));
		game.addFrame(new Frame(1,1));
		game.addFrame(new Frame(4,2));
		game.addFrame(new Frame(8,0));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(59, game.score());
	}
	@Test
	public void testStartWithASpareContent() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(4,6));
		game.addFrame(new Frame(2,5));
		game.addFrame(new Frame(0,0));
		game.addFrame(new Frame(4,2));
		game.addFrame(new Frame(8,0));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(57, game.score());
	}
	@Test
	public void testSpareWithFirstThrowZeroForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(0,10));
		game.addFrame(new Frame(2,4));
		game.addFrame(new Frame(5,5));
		game.addFrame(new Frame(1,8));
		game.addFrame(new Frame(0,5));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(67, game.score());
	}
	@Test
	public void testSpareInTheMiddleForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(4,5));
		game.addFrame(new Frame(2,4));
		game.addFrame(new Frame(5,5));
		game.addFrame(new Frame(1,8));
		game.addFrame(new Frame(0,5));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
	
		assertEquals(64, game.score());
	}
	@Test
	public void testSpareInTheMiddleContent() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(5,1));
		game.addFrame(new Frame(7,2));
		game.addFrame(new Frame(0,10));
		game.addFrame(new Frame(0,1));
		game.addFrame(new Frame(0,5));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(55, game.score());
	}

	@Test
	public void testSingleSpareZeroBonusForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(5,5));
		game.addFrame(new Frame(0,0));
		game.addFrame(new Frame(1,1));
		game.addFrame(new Frame(4,2));
		game.addFrame(new Frame(8,0));
		game.addFrame(new Frame(2,3));
		game.addFrame(new Frame(1,3));
		game.addFrame(new Frame(1,6));
		game.addFrame(new Frame(2,0));
		game.addFrame(new Frame(5,1));
		
		assertEquals(50, game.score());
	}



}
