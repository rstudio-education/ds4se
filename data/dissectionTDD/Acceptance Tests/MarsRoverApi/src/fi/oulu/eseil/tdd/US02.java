package fi.oulu.eseil.tdd;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import tdd.training.mars.*;



public class US02 {
	MarsRover rover;
	@Before
	public void setUp(){
		rover = new MarsRover(100, 100, "");
	}
	
	@Test
	public void theRoverMovesForwardOnceLanding() {
		assertThat("(0,1,N)", is(equalToIgnoringCase(this.rover.executeCommand("f"))));
		assertThat("(0,2,N)", is(equalToIgnoringCase(this.rover.executeCommand("f"))));
	}
	
	@Test
	public void theRoverMovesBackwardsnceLanding() {
		this.rover.executeCommand("f");
		assertThat("(0,0,N)", is(equalToIgnoringCase(this.rover.executeCommand("b"))));
	}
	
	@Test
	public void theRoverShouldChangeFacingTWhenGivenATurnCommandAfterLanding(){
		assertThat("(0,0,W)", is(equalToIgnoringCase( this.rover.executeCommand("l"))));
		assertThat("(0,0,E)", is(equalToIgnoringCase( this.rover.executeCommand("rr"))));
	}
	
	@Test
	public void theRoverFacesSouthAfterLandingAndExecutingATurnCommandToTimesInARow(){
		assertThat("(0,0,S)", is(equalToIgnoringCase( this.rover.executeCommand("rr"))));
		assertThat("(0,0,N)", is(equalToIgnoringCase( this.rover.executeCommand("ll"))));
	}
	
	@Test
	public void theRoverFacesWestAfterLandingAndExecutingALeftCommandThreeTimesInARow(){
		assertThat("(0,0,E)", is(equalToIgnoringCase(this.rover.executeCommand("lll"))));	
	}
	
	@Test
	public void theRoverFacesEastAfterLandingAndExecutingARightCommandThreeTimesInARow(){
		assertThat("(0,0,W)", is(equalToIgnoringCase(this.rover.executeCommand("rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommand(){
		assertThat("(0,0,N)", is(equalToIgnoringCase(this.rover.executeCommand("llll"))));
		assertThat("(0,0,N)", is(equalToIgnoringCase(this.rover.executeCommand("rrrr"))));
	}

}
