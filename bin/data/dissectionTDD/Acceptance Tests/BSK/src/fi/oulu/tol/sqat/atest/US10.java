package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;

import org.junit.Test;

import tol.sqat.session6.*;



public class US10 {

		@Test
	public void testLastSpareForm() throws InvalidInput, BowlingException   {
		BowlingGame game1 = new BowlingGame();
		BowlingGame game2 = new BowlingGame();
		
		game1.addFrame(new Frame(1, 5)); //1
		game1.addFrame(new Frame(2, 5)); //2
		game1.addFrame(new Frame(1, 1)); //3
		game1.addFrame(new Frame(4, 2)); //4
		game1.addFrame(new Frame(8, 0)); //5
		game1.addFrame(new Frame(2, 3)); //6
		game1.addFrame(new Frame(1, 3)); //7
		game1.addFrame(new Frame(1, 6)); //8
		game1.addFrame(new Frame(2, 0)); //9
		game1.addFrame(new Frame(5,5));
		game1.addFrame(new Frame(4,0));
		
		game2.addFrame(new Frame(1, 5)); //1
		game2.addFrame(new Frame(2, 5)); //2
		game2.addFrame(new Frame(1, 1)); //3
		game2.addFrame(new Frame(4, 2)); //4
		game2.addFrame(new Frame(8, 0)); //5
		game2.addFrame(new Frame(2, 3)); //6
		game2.addFrame(new Frame(1, 3)); //7
		game2.addFrame(new Frame(1, 6)); //8
		game2.addFrame(new Frame(2, 0)); //9
		game2.addFrame(new Frame(5,5));
		
		game2.setBonus(4,0);
		
		if(game1.score() == 61 || game2.score() == 61)
			assertTrue(true);
		else
			fail();
	}
	@Ignore
	@Test
	public void testLastSpareBonusForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(1, 5)); //1
		game.addFrame(new Frame(2, 5)); //2
		game.addFrame(new Frame(1, 1)); //3
		game.addFrame(new Frame(4, 2)); //4
		game.addFrame(new Frame(8, 0)); //5
		game.addFrame(new Frame(2, 3)); //6
		game.addFrame(new Frame(1, 3)); //7
		game.addFrame(new Frame(1, 6)); //8
		game.addFrame(new Frame(2, 0)); //9
		game.addFrame(new Frame(5,5));
		game.addFrame(new Frame(4,4));
		//game.setBonusFrame(new Frame(4,4));
		
		

		assertEquals(61, game.score());
	}
	@Ignore
	@Test
	public void testLastSpareZeroBonus() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(1, 5)); //1
		game.addFrame(new Frame(2, 5)); //2
		game.addFrame(new Frame(1, 1)); //3
		game.addFrame(new Frame(4, 2)); //4
		game.addFrame(new Frame(8, 0)); //5
		game.addFrame(new Frame(2, 3)); //6
		game.addFrame(new Frame(1, 3)); //7
		game.addFrame(new Frame(1, 6)); //8
		game.addFrame(new Frame(2, 0)); //9
		game.addFrame(new Frame(5,5));
		game.addFrame(new Frame(0,0));

		//game.setBonusFrame(new Frame(0,0));

	
		assertEquals(57, game.score());
	}
	
		@Test
	public void testLastSpareZeroBonusContent() throws InvalidInput, BowlingException   {
		BowlingGame game1 = new BowlingGame();
		BowlingGame game2 = new BowlingGame();
		
		game1.addFrame(new Frame(1, 5)); //1
		game1.addFrame(new Frame(2, 5)); //2
		game1.addFrame(new Frame(1, 1)); //3
		game1.addFrame(new Frame(4, 2)); //4
		game1.addFrame(new Frame(8, 0)); //5
		game1.addFrame(new Frame(2, 3)); //6
		game1.addFrame(new Frame(1, 3)); //7
		game1.addFrame(new Frame(1, 6)); //8
		game1.addFrame(new Frame(2, 0)); //9
		game1.addFrame( new Frame(4,6));
		game1.addFrame(new Frame(0,0));
		
		game2.addFrame(new Frame(1, 5)); //1
		game2.addFrame(new Frame(2, 5)); //2
		game2.addFrame(new Frame(1, 1)); //3
		game2.addFrame(new Frame(4, 2)); //4
		game2.addFrame(new Frame(8, 0)); //5
		game2.addFrame(new Frame(2, 3)); //6
		game2.addFrame(new Frame(1, 3)); //7
		game2.addFrame(new Frame(1, 6)); //8
		game2.addFrame(new Frame(2, 0)); //9
		game2.addFrame( new Frame(4,6));

		game2.setBonus(0,0);

		if(game1.score() == 57 || game2.score() == 57)
			assertTrue(true);
		else
			fail();
	}
	
	@Ignore
	@Test
	public void testLastSpareZeroBonusForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		game.addFrame(new Frame(1, 5)); //1
		game.addFrame(new Frame(2, 5)); //2
		game.addFrame(new Frame(1, 1)); //3
		game.addFrame(new Frame(4, 2)); //4
		game.addFrame(new Frame(8, 0)); //5
		game.addFrame(new Frame(2, 3)); //6
		game.addFrame(new Frame(1, 3)); //7
		game.addFrame(new Frame(1, 6)); //8
		game.addFrame(new Frame(2, 0)); //9
		game.addFrame( new Frame(3,7));
		game.addFrame(new Frame(0,0));

		//game.setBonusFrame(new Frame(0,0));


		assertEquals(57, game.score());
	}

}
