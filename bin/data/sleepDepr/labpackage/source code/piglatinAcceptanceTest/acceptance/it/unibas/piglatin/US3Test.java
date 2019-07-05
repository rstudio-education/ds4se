package it.unibas.piglatin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class US3Test {
	
	@Test
	public void testWordStartingWithVowelEndingWithConsonant() {
		PigLatin pigLatin = new PigLatin("architect");
		assertEquals("architectay", pigLatin.translate());		
	}
	
	@Test
	public void testWordStartingWithVowelEndingWithVowel() {
		PigLatin pigLatin = new PigLatin("orange");
		assertEquals("orangeyay", pigLatin.translate());
	}
	
	@Test
	public void testWordStartingWithVowelEndingWithY() {
		PigLatin pigLatin = new PigLatin("enemy");
		assertEquals("enemynay", pigLatin.translate());
	}

}
