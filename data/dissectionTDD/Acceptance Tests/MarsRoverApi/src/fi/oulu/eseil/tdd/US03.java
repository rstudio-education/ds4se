package fi.oulu.eseil.tdd;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import tdd.training.mars.*;


public class US03 {
	MarsRover rover;
	@Before
	public void startUp(){
		this.rover=new MarsRover(100, 100, "");
	}
	@Test
	public void theRoverMovesForwardNorthBoundedOnceLanding() {
		String command = generateCommand(10, "f");
		assertThat("(0,10,N)", is(equalToIgnoringCase(this.rover.executeCommand(command))));
		assertThat("(0,11,N)", is(equalToIgnoringCase(this.rover.executeCommand("f"))));
	}
	
	@Test
	public void theRoverMovesForwardSouthBoundedOnceLanding() {
		String command = generateCommand(10, "f");
		 this.rover.executeCommand(command);
		 this.rover.executeCommand("ll");
		 assertThat("(0,5,S)", is(equalToIgnoringCase( this.rover.executeCommand("fffff"))));
		 assertThat("(0,4,S)", is(equalToIgnoringCase( this.rover.executeCommand("f"))));
	}
	
	@Test
	public void theRoverMovesForwardWestBoundedOnceLanding() {
		String command = generateCommand(10, "f");
		 this.rover.executeCommand(command);
		 this.rover.executeCommand("r");
		 this.rover.executeCommand("fffff");
		 this.rover.executeCommand("ll");
		 //this.rover.executeCommand("ff");
		 assertThat("(3,10,W)", is(equalToIgnoringCase( this.rover.executeCommand("ff"))));
		 assertThat("(2,10,W)", is(equalToIgnoringCase( this.rover.executeCommand("f"))));
	}
	
	@Test
	public void theRoverMovesForwardEastBoundedOnceLanding() {
		String command = generateCommand(10, "f");
		 this.rover.executeCommand(command);
		 this.rover.executeCommand("r");
		this.rover.executeCommand("fffff");
		 assertThat("(10,10,E)", is(equalToIgnoringCase( this.rover.executeCommand("fffff"))));
		 assertThat("(11,10,E)", is(equalToIgnoringCase( this.rover.executeCommand("f"))));
	}
	
	private String generateCommand(int times,String symbol) {
		String command  = "";
		for (int i = 0; i < times; i++) {
			 command += symbol;
		}
		return command;
	}

}
