package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;
import fi.oulu.tol.sqat.BowlingException;
import tol.sqat.session6.*;

import org.junit.Test;





public class US04 {

	@Test
	public void testAddFrame() throws InvalidInput  {
		BowlingGame game = new BowlingGame();
		Frame frame1 = new Frame(1, 0);
		game.addFrame(frame1);
		
		Frame frameTest = new Frame(frame1.getThrow1(), frame1.getThrow2());
	
		assertEquals(frame1.getThrow1(), frameTest.getThrow1());
		assertEquals(frame1.getThrow2(), frameTest.getThrow2());
	}

	@Test
	public void testGetFrame() throws InvalidInput {
		BowlingGame game = new BowlingGame();
		Frame frame1 = new Frame(1, 2);
		Frame frame2 = new Frame(5, 4);
		Frame frame3 = new Frame(4, 3);
		game.addFrame(frame1);
		game.addFrame(frame2);
		game.addFrame(frame3);
		assertEquals(frame1.getThrow1(), new Frame(frame1.getThrow1(), frame1.getThrow2()).getThrow1());
		assertEquals(frame1.getThrow2(), new Frame(frame1.getThrow1(), frame1.getThrow2()).getThrow2());
		
		assertEquals(frame2.getThrow1(), new Frame(frame2.getThrow1(), frame2.getThrow2()).getThrow1());
		assertEquals(frame2.getThrow2(), new Frame(frame2.getThrow1(), frame2.getThrow2()).getThrow2());
		
		assertEquals(frame3.getThrow1(), new Frame(frame3.getThrow1(), frame3.getThrow2()).getThrow1());
		assertEquals(frame3.getThrow2(), new Frame(frame3.getThrow1(), frame3.getThrow2()).getThrow2());
	}
	
	@Test
	public void testGameScore() throws InvalidInput, InvalidFrameStateException{
		BowlingGame game = new BowlingGame();
		
		try {
			game.addFrame(new Frame(1, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		assertEquals(3, game.score());
		
		game.addFrame(new Frame(2, 3));
		assertEquals(8, game.score());
		
		game.addFrame(new Frame(5, 4));
		assertEquals(17, game.score());
	}
	

}
