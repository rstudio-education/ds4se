package fi.oulu.eseil.tdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static fi.oulu.eseil.tdd.Commons.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import tdd.training.mars.*;

public class US11 {

	@Test
	public void roverEncountersAnObstacleWhenTriesToSpawnNorthWhileGoingForward(){
		MarsRover rover = new MarsRover(100, 100, "(0,99)");

		assertThat("(0,0,S)(0,99)", is(equalToIgnoringCase(( rover.executeCommand("llf")))));
	}
	@Test
	public void roverEncountersAnObstacleWhenTriesToSpawnSouthWhileGoingForward(){
		MarsRover rover = new MarsRover(100, 100, "(1,0)");
		rover.executeCommand(goToTopLeftCornerFromSouth());
		rover.executeCommand("rfl");
		assertThat("(1,99,N)(1,0)", is(equalToIgnoringCase(( rover.executeCommand("f")))));

	}
	
	@Test
	public void roverEncountersAnObstacleWhenTriesToSpawnNorthWhileGoingBackward(){
		MarsRover rover = new MarsRover(100, 100, "(0,99)");
		assertThat("(0,0,N)(0,99)", is(equalToIgnoringCase(( rover.executeCommand("b")))));
	}
	@Test
	public void roverEncountersAnObstacleWhenTriesToSpawnSouthWhileGoingBackward(){
		MarsRover rover = new MarsRover(100, 100, "(1,0)");
		rover.executeCommand(goToTopLeftCornerFromSouth());
		rover.executeCommand("rfl");
		assertThat("(1,99,S)(1,0)", is(equalToIgnoringCase(( rover.executeCommand("llb")))));
	}
	@Test
	public void roverEncountersAnObstacleWhenTriesToSpawnWestGoingForward(){
		MarsRover rover = new MarsRover(100, 100, "(0,30)");
		rover.executeCommand("r");
		rover.executeCommand(generateCommand(99, "f"));
		rover.executeCommand("l");
		rover.executeCommand(generateCommand(30, "f"));
		rover.executeCommand("r");
		assertThat("(99,30,E)(0,30)", is(equalToIgnoringCase(( rover.executeCommand("f")))));
		//assertThat("(99,30,W)(0,30)()", is(equalToIgnoringCase(( rover.executeCommand("llb")))));

	}
	
	@Test
	public void roverEncountersAnObstacleWhenTriesToSpawnWestGoingBackward(){
		MarsRover rover = new MarsRover(100, 100, "(0,30)");
		rover.executeCommand("r");
		rover.executeCommand(generateCommand(99, "f"));
		rover.executeCommand("l");
		rover.executeCommand(generateCommand(30, "f"));
		rover.executeCommand("r");
		assertThat("(99,30,W)(0,30)", is(equalToIgnoringCase(( rover.executeCommand("llb")))));
	}
	@Test
	public void roverEncountersAnObstacleWhenTriesToSpawnEastGoingForward(){
		MarsRover rover = new MarsRover(100, 100, "(99,30)");
		rover.executeCommand(generateCommand(30, "f"));
		rover.executeCommand("l");
		assertThat("(0,30,W)(99,30)", is(equalToIgnoringCase( rover.executeCommand("f"))));

	}
	@Test
	public void roverEncountersAnObstacleWhenTriesToSpawnEastGoingBackward(){
		MarsRover rover = new MarsRover(100, 100, "(99,30)");
		rover.executeCommand(generateCommand(30, "f"));
		rover.executeCommand("l");
		assertThat("(0,30,E)(99,30)", is(equalToIgnoringCase( rover.executeCommand("llb"))));
	}
	
//	@Test
//	public void roverEncountersAnObstacleWhenTriesToSpawnWestGoingForward(){
//		MarsRover rover = new MarsRover(100, 100, "(0,30)");
//		rover.executeCommand("r");
//		rover.executeCommand(generateCommand(99, "f"));
//		rover.executeCommand("l");
//		rover.executeCommand(generateCommand(29, "f"));
//		rover.executeCommand("r");
//		assertThat("(99,30, E)(0,30)", is(equalToIgnoringCase(( rover.executeCommand("f")))));
//		assertThat("(99,30,W)(0,30)", is(equalToIgnoringCase(( rover.executeCommand("llb")))));
//	}
//	
//	@Test
//	public void roverEncountersAnObstacleWhenTriesToSpawnEastGoingForward(){
//		MarsRover rover = new MarsRover(100, 100, "(99,30)");
//		rover.executeCommand(generateCommand(29, "f"));
//		rover.executeCommand("l");
//		assertThat("(0,30, W)(99,30)", is(equalToIgnoringCase( rover.executeCommand("f"))));
//
//	}

}
