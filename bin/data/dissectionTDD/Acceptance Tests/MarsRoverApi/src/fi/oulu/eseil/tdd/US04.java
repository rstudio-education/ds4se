package fi.oulu.eseil.tdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdd.training.mars.*;



public class US04 {

	MarsRover rover;
	@Before
	public void startUp(){
		this.rover=new MarsRover(100, 100, "");
	}
	@Test
	public void theRoverMovesBackwardNorthBoundedOnceLanding() {
		String command = generateCommand(10, "f");
		this.rover.executeCommand(command);
		this.rover.executeCommand("b");
		assertThat("(0,6,N)", is(equalToIgnoringCase( this.rover.executeCommand("bbb"))));
		assertThat("(0,5,N)", is(equalToIgnoringCase( this.rover.executeCommand("b"))));
	}
	
	@Test
	public void theRoverMovesBackwardSouthBoundedOnceLanding() {
		String command = generateCommand(10, "f");
		 this.rover.executeCommand(command);
		 this.rover.executeCommand("ll");
		 assertThat("(0,15,S)", is(equalToIgnoringCase( this.rover.executeCommand("bbbbb"))));
		 assertThat("(0,16,S)", is(equalToIgnoringCase( this.rover.executeCommand("b"))));
	}
	
	@Test
	public void theRoverMovesBackwardWestBoundedOnceLanding() {
		String command = generateCommand(10, "f");
		 this.rover.executeCommand(command);
		 this.rover.executeCommand("l");
		 assertThat("(3,10,W)", is(equalToIgnoringCase( this.rover.executeCommand("bbb"))));
	}
	
	@Test
	public void theRoverMovesBackwardEastBoundedOnceLanding() {
		String command = generateCommand(10, "f");
		 this.rover.executeCommand(command);
		 this.rover.executeCommand("r");
		 this.rover.executeCommand("fffff");
		 assertThat("(2,10,E)", is(equalToIgnoringCase( this.rover.executeCommand("bbb"))));
		 assertThat("(1,10,E)", is(equalToIgnoringCase( this.rover.executeCommand("b"))));
	}
	
	private String generateCommand(int times,String symbol) {
		String command  = "";
		for (int i = 0; i < times; i++) {
			 command += symbol;
		}
		return command;
	}

}
