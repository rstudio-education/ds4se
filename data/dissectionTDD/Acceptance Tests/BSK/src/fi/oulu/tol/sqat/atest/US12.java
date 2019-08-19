package fi.oulu.tol.sqat.atest;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import tol.sqat.session6.*;



public class US12 {
	@Ignore
	@Test
	public void testBonusIsStrikeFormWhenLastIsSpareForm() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		Frame f1 = new Frame(1,5);
		Frame f2 = new Frame(2,5);
		Frame f3 = new Frame(1,1);
		Frame f4 = new Frame(4,2);
		Frame f5 = new Frame(8,0);
		Frame f6 = new Frame(2,3);
		Frame f7 = new Frame(1,3);
		Frame f8 = new Frame(1,6);
		Frame f9 = new Frame(2,0);
		Frame f10 = new Frame(5,5);
	

		game.addFrame(f1);
		game.addFrame(f2);
		game.addFrame(f3);
		game.addFrame(f4);
		game.addFrame(f5);
		game.addFrame(f6);
		game.addFrame(f7);
		game.addFrame(f8);
		game.addFrame(f9);
	
		game.addFrame(f10);
		game.addFrame(new Frame(10,0));

		//game.setBonusFrame(new Frame(10,0));


		
		assertEquals(67, game.score());
	}
	
	@Test
	public void testBonusIsStrikeWhenLastIsSpareContent() throws InvalidInput, BowlingException   {
		BowlingGame game1 = new BowlingGame();
		BowlingGame game2 = new BowlingGame();
		
		Frame f1 = new Frame(1,5);
		Frame f2 = new Frame(2,5);
		Frame f3 = new Frame(1,1);
		Frame f4 = new Frame(4,2);
		Frame f5 = new Frame(8,0);
		Frame f6 = new Frame(2,3);
		Frame f7 = new Frame(1,3);
		Frame f8 = new Frame(1,6);
		Frame f9 = new Frame(2,0);
		Frame f10 = new Frame(4,6);
		
		game1.addFrame(f1);
		game1.addFrame(f2);
		game1.addFrame(f3);
		game1.addFrame(f4);
		game1.addFrame(f5);
		game1.addFrame(f6);
		game1.addFrame(f7);
		game1.addFrame(f8);
		game1.addFrame(f9);
		game1.addFrame(new Frame(4,6));
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
		game2.addFrame(new Frame(4,6));
		game2.setBonus(10, 0);

		if(game1.score() == 67 || game2.score() == 67)
			assertTrue(true);
		else
			fail();	
	}

	@Ignore
	@Test
	public void testBonusIsStrikeWhenLastIsStrike() throws InvalidInput, InvalidFrameStateException   {
		BowlingGame game = new BowlingGame();
		
		Frame f1= new Frame(1, 5); //1
		Frame f2= new Frame(2, 5); //2
		Frame f3= new Frame(1, 1); //3
		Frame f4= new Frame(4, 2); //4
		Frame f5= new Frame(8, 0); //5
		Frame f6= new Frame(2, 3); //6
		Frame f7= new Frame(1, 3); //7
		Frame f8= new Frame(1, 6); //8
		Frame f9= new Frame(2, 0); //9
		Frame f10= new Frame(10,0);//,10,0); //10
		game.addFrame(f1);
		game.addFrame(f2);
		game.addFrame(f3);
		game.addFrame(f4);
		game.addFrame(f5);
		game.addFrame(f6);
		game.addFrame(f7);
		game.addFrame(f8);
		game.addFrame(f9);
		
	
		game.addFrame(new Frame(10,0));
		game.addFrame(new Frame(10,0));

		//game.setBonusFrame(new Frame(10,0));
	
		
	
		assertEquals(77, game.score());
	}
	
	@Test
	public void testBonusIsStrikeWhenLastIsStrikeContent() throws InvalidInput, BowlingException   {
		
		BowlingGame game1 = new BowlingGame();
		BowlingGame game2 = new BowlingGame();
		
		Frame f1 = new Frame(1, 5); //1
		Frame f2 = new Frame(2, 5); //2
		Frame f3 = new Frame(1, 1); //3
		Frame f4 = new Frame(4, 2); //4
		Frame f5 = new Frame(8, 0); //5
		Frame f6 = new Frame(2, 3); //6
		Frame f7 = new Frame(1, 3); //7
		Frame f8 = new Frame(1, 6); //8
		Frame f9 = new Frame(2, 0); //9
		Frame f10 = new Frame(10,0);//,10,0); //10
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
		game2.addFrame(new Frame(10,0));
		game2.setBonus(10, 0);
	
		if(game1.score() == 67 || game2.score() == 67)
			assertTrue(true);
		else
			fail();	
	}
	
	@Test
	public void testBonusIsStrikeWhenLastIsStrike_PlusAdditionalFrame_WithoutBonusThrows() throws InvalidInput, BowlingException   {
		BowlingGame game = new BowlingGame();
		Frame f1 = new Frame(1, 5); //1
		Frame f2 = new Frame(2, 5); //2
		Frame f3 = new Frame(1, 1); //3
		Frame f4 = new Frame(4, 2); //4
		Frame f5 = new Frame(8, 0); //5
		Frame f6 = new Frame(2, 3); //6
		Frame f7 = new Frame(1, 3); //7
		Frame f8 = new Frame(1, 6); //8
		Frame f9 = new Frame(2, 0); //9
		Frame f10 = new Frame(10,0);//,10,0); //10
		game.addFrame(f1);
		game.addFrame(f2);
		game.addFrame(f3);
		game.addFrame(f4);
		game.addFrame(f5);
		game.addFrame(f6);
		game.addFrame(f7);
		game.addFrame(f8);
		game.addFrame(f9);


		game.addFrame(new Frame(10,0));
		game.addFrame(new Frame(10,0));
				
		assertThat(game.score(), anyOf(equalTo(57), equalTo(67))); 
	}
}

}
