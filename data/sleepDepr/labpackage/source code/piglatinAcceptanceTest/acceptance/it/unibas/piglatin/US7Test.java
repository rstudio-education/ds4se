package it.unibas.piglatin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class US7Test {
	
	@Test
	public void testUpperWithOneWord() {
		PigLatin pigLatin = new PigLatin("Bob");
		assertEquals("Obbay", pigLatin.translate());
		
	}
	
	@Test
	public void testUpperWithMoreWord() {
		PigLatin pigLatin = new PigLatin("Bob Rob");
		assertEquals("Obbay Obray", pigLatin.translate());
	}

}
