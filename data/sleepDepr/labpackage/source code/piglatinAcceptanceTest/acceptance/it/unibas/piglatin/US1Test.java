package it.unibas.piglatin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class US1Test {
	
	@Test
	public void testInitPhrase() {
		PigLatin pigLatin = new PigLatin("First test");
		assertEquals("First test", pigLatin.phrase());
	}

}
