package fi.oulu.tol.sqat.atest;

import static org.junit.Assert.*;
import tol.sqat.session6.*;

import org.junit.Test;

import fi.oulu.tol.sqat.BowlingException;


public class US01 {

	@Test
	public void testEmptyFrameIsCreated() {
		Frame f = new Frame();
		assertNotNull(f);
	}

	@Test
	public void testFrameWithScoreIsCreated() throws InvalidInput {
		Frame f = new Frame(1,2);
		assertNotNull(f);
	}
	
	@Test
	public void testFrameIsCreatedWithCorrectName(){
		Frame f  = new Frame();
		assertEquals("Frame", f.getClass().getSimpleName());
	}
}
