package it.unibas.piglatin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class US4Test {
	
	@Test
	public void testWordStartingWithSingleConsonant() {
		PigLatin pigLatin = new PigLatin("say");
		assertEquals("aysay", pigLatin.translate());
	}

}
