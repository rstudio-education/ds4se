package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;

import org.junit.Test;

import tol.sqat.session6.*;

public class US09 {

	@Test
	public void testStartWithMultipleSpareForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(5, 5)); //1
		game.addFrame(new Frame(1, 9)); //2
		game.addFrame(new Frame(1, 1)); //3
		game.addFrame(new Frame(4, 2)); //4
		game.addFrame(new Frame(8, 0)); //5
		game.addFrame(new Frame(2, 3)); //6
		game.addFrame(new Frame(1, 3)); //7
		game.addFrame(new Frame(1, 6)); //8
		game.addFrame(new Frame(2, 0)); //9
		game.addFrame(new Frame(5, 1)); //10
		
		assertEquals(62, game.score());
	}
	@Test
	public void testStartWithMultipleSpareContent() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(4, 6)); //1
		game.addFrame(new Frame(3, 7)); //2
		game.addFrame(new Frame(0, 10)); //3
		game.addFrame(new Frame(4, 2)); //4
		game.addFrame(new Frame(8, 0)); //5
		game.addFrame(new Frame(2, 3)); //6
		game.addFrame(new Frame(1, 3)); //7
		game.addFrame(new Frame(1, 6)); //8
		game.addFrame(new Frame(2, 0)); //9
		game.addFrame(new Frame(5, 1)); //10
		
		assertEquals(75, game.score());
	}
	@Test
	public void testMultipleSpareInTheMiddle() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(1, 5)); //1
		game.addFrame(new Frame(1, 4)); //2
		game.addFrame(new Frame(4, 6)); //3
		game.addFrame(new Frame(4, 6)); //4
		game.addFrame(new Frame(0, 10)); //5
		game.addFrame(new Frame(1, 9)); //6
		game.addFrame(new Frame(1, 3)); //7
		game.addFrame(new Frame(1, 6)); //8
		game.addFrame(new Frame(2, 0)); //9
		game.addFrame(new Frame(5, 1)); //10
		
		assertEquals(76, game.score());
	}
	@Test
	public void testMultipleSpareInTheMiddleForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(5, 5)); //1
		game.addFrame(new Frame(1, 9)); //2
		game.addFrame(new Frame(1, 1)); //3
		game.addFrame(new Frame(4, 2)); //4
		game.addFrame(new Frame(8, 0)); //5
		game.addFrame(new Frame(2, 3)); //6
		game.addFrame(new Frame(1, 3)); //7
		game.addFrame(new Frame(1, 6)); //8
		game.addFrame(new Frame(2, 0)); //9
		game.addFrame(new Frame(5, 1)); //10
		
		assertEquals(62, game.score());
	}
	@Test
	public void testMultipleSpareInTheMiddleContent() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(5, 1)); //1
		game.addFrame(new Frame(7, 2)); //2
		game.addFrame(new Frame(0, 10)); //3
		game.addFrame(new Frame(4, 6)); //4
		game.addFrame(new Frame(0, 5)); //5
		game.addFrame(new Frame(2, 3)); //6
		game.addFrame(new Frame(1, 3)); //7
		game.addFrame(new Frame(1, 6)); //8
		game.addFrame(new Frame(2, 0)); //9
		game.addFrame(new Frame(5, 1)); //10
		
		assertEquals(68, game.score());
	}

}
