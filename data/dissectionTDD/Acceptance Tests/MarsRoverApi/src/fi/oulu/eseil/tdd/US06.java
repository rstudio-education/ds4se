package fi.oulu.eseil.tdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdd.training.mars.*;



public class US06 {
	MarsRover rover;
	@Before 
	public void setUp(){
		this.rover = new MarsRover(100, 100, "");
	}
	@Test
	public void theRoverShouldSpawnOnTheBottomSideWhenCrossingNorth(){
		MarsRover rover  = new MarsRover(100,100,"");
		rover.executeCommand(generateCommand(99, "f"));
		assertThat("(0,0,N)", is(equalToIgnoringCase( rover.executeCommand("f"))));
	}
	@Test
	public void roverShouldSpawnOnThebottomWhenCrossingNorthBackwards(){
		MarsRover rover2  = new MarsRover(100,100,"");
		rover2.executeCommand(generateCommand(99, "f"));
		rover2.executeCommand("ll");
		assertThat("(0,0,S)", is(equalToIgnoringCase( rover2.executeCommand("b"))));
		
	}
	
	private String generateCommand(int times,String symbol) {
		String command  = "";
		for (int i = 0; i < times; i++) {
			command += symbol;
		}
		return command;
	}
	@Test
	public void theRoverShouldSpawnOnTheNorthSideWhenCrossingSouth(){
		MarsRover rover  = new MarsRover(100,100,"");
		rover.executeCommand("ll");
		assertThat("(0,99,S)", is(equalToIgnoringCase( rover.executeCommand("f"))));
	}
	@Test
	public void theRoverShouldSpawnOnTheNorthSideWhenCrossingSouthBackwards(){
		MarsRover rover2  = new MarsRover(100,100,"");
		assertThat("(0,99,N)", is(equalToIgnoringCase( rover2.executeCommand("b"))));
	}
	
	@Test
	public void theRoverShouldSpawnOnTheRightSideWhenCrossingLeft(){
		MarsRover rover = new MarsRover(100, 100, "");
		rover.executeCommand("l");
		assertThat("(99,0,W)", is(equalToIgnoringCase( rover.executeCommand("f"))));
	}
	@Test
	public void theRoverShouldSpawnOnTheRightSideWhenCrossingLeftBackward(){
		MarsRover rover2 = new MarsRover(100, 100,"");
		rover2.executeCommand("r");
		assertThat("(99,0,E)", is(equalToIgnoringCase( rover2.executeCommand("b"))));
	}
	@Test
	public void theRoverShouldSpawnOnTheLeftSideWhenCrossingRight(){
		MarsRover rover = new MarsRover(100, 100, "");
		rover.executeCommand("r");
		rover.executeCommand(Commons.generateCommand(99, "f"));
		assertThat("(0,0,E)", is(equalToIgnoringCase(rover.executeCommand("f"))));
	}
	@Test
	public void theRoverShouldSpawnOnTheLeftSideWhenCrossingRightBackwards(){
		MarsRover rover2 = new MarsRover(100, 100, "");
		rover2.executeCommand("r");
		rover2.executeCommand(Commons.generateCommand(99, "f"));
		rover2.executeCommand("ll");
		assertThat("(0,0,W)", is(equalToIgnoringCase(rover2.executeCommand("b"))));
	}
	

}
