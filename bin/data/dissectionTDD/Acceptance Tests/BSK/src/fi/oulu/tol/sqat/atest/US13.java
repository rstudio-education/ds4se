package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.oulu.tol.sqat.BowlingException;
import tol.sqat.session6.*;
public class US13 {

	@Test
	public void testBestScoresAllSparesContent() throws InvalidInput, BowlingException   {
		BowlingGame game1 = new BowlingGame();
		BowlingGame game2 = new BowlingGame();
		
		Frame f1 = new Frame(9, 1); //1
		Frame f2 = new Frame(9, 1); //2
		Frame f3 = new Frame(9, 1); //3
		Frame f4 = new Frame(9, 1); //4
		Frame f5 = new Frame(9, 1); //5
		Frame f6 = new Frame(9, 1); //6
		Frame f7 = new Frame(9, 1); //7
		Frame f8 = new Frame(9, 1); //8
		Frame f9 = new Frame(9, 1); //9
		Frame f10 = new Frame(9, 1);//,10,0); //10
		
		game1.addFrame(f1);
		game1.addFrame(f2);
		game1.addFrame(f3);
		game1.addFrame(f4);
		game1.addFrame(f5);
		game1.addFrame(f6);
		game1.addFrame(f7);
		game1.addFrame(f8);
		game1.addFrame(f9);
		game1.addFrame( new Frame(9,1));
		game1.addFrame(new Frame(10,0));

		game2.addFrame(f1);
		game2.addFrame(f2);
		game2.addFrame(f3);
		game2.addFrame(f4);
		game2.addFrame(f5);
		game2.addFrame(f6);
		game2.addFrame(f7);
		game2.addFrame(f8);
		game2.addFrame(f9);
		game2.addFrame( new Frame(9,1));
		game2.setBonus(10,0);
		
		if(game1.score() == 191 || game2.score() == 191)
			assertTrue(true);
		else
			fail();
	}
	
	@Test
	public void testBestScoresAllStrikesContent() throws InvalidInput, BowlingException   {
		BowlingGame game1 = new BowlingGame();
		BowlingGame game2 = new BowlingGame();
		
		Frame f1 = new Frame(10,0); //1
		Frame f2 = new Frame(10,0); //2
		Frame f3 = new Frame(10,0); //3
		Frame f4 = new Frame(10,0); //4
		Frame f5 = new Frame(10,0); //5
		Frame f6 = new Frame(10,0); //6
		Frame f7 = new Frame(10,0); //7
		Frame f8 = new Frame(10,0); //8
		Frame f9 = new Frame(10,0); //9
		Frame f10 = new Frame(10,0);//, 10,0); //10
		
		game1.addFrame(f1);
		game1.addFrame(f2);
		game1.addFrame(f3);
		game1.addFrame(f4);
		game1.addFrame(f5);
		game1.addFrame(f6);
		game1.addFrame(f7);
		game1.addFrame(f8);
		game1.addFrame(f9);

		game1.addFrame(new Frame(10,0));
		game1.addFrame(new Frame(10,10));
		
		game2.addFrame(f1);
		game2.addFrame(f2);
		game2.addFrame(f3);
		game2.addFrame(f4);
		game2.addFrame(f5);
		game2.addFrame(f6);
		game2.addFrame(f7);
		game2.addFrame(f8);
		game2.addFrame(f9);

		game2.addFrame(new Frame(10,0));
		
		game2.setBonus(10,10);
		
		if(game1.score() == 300 || game2.score() == 300)
			assertTrue(true);
		else
			fail();
	}
	

}
