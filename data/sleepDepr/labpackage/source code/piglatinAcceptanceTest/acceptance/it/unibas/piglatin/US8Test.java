package it.unibas.piglatin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class US8Test {
	
	@Test
	public void testKeepPunctuationOneWord() {
		PigLatin pigLatin = new PigLatin("Great!");
		assertEquals("Eatgray!", pigLatin.translate());
	}
	
	@Test
	public void testKeepPunctuationMoreWord() {
		PigLatin pigLatin = new PigLatin("Two ordinal numbers: first, second.");
		assertEquals("Otway ordinalay umbersnay: irstfay, econdsay.", pigLatin.translate());
	}

}
