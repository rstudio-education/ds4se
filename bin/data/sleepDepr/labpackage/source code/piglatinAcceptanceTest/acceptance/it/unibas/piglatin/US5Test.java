package it.unibas.piglatin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class US5Test {
		
	@Test
	public void testWordStartingWithMultipleConsonant() {
		PigLatin pigLatin = new PigLatin("brown");
		assertEquals("ownbray", pigLatin.translate());
	}

}
