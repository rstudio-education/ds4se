package it.unibas.piglatin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class US6Test {
	
	@Test
	public void testMoreWordsSplittedWithSpace() {
		PigLatin pigLatin = new PigLatin("stay alive");
		assertEquals("aystay aliveyay", pigLatin.translate());
	}
	
	@Test
	public void testMoreWordsSplittedWithDash() {
		PigLatin pigLatin = new PigLatin("stay-alive");
		assertEquals("aystay-aliveyay", pigLatin.translate());
	}

}
