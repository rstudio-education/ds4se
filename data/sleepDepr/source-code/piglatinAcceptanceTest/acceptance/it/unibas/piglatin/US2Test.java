package it.unibas.piglatin;

import static org.junit.Assert.*;

import org.junit.Test;

public class US2Test {
	
	@Test
	public void testEmptyString() {
		PigLatin pigLatin = new PigLatin("");
		assertNull(pigLatin.translate());
	}
	
//	@Test
//	public void testNonEmptyString() {
//		PigLatin pigLatin = new PigLatin("hey");
//		assertNotNull(pigLatin.translate());
//	}

}
