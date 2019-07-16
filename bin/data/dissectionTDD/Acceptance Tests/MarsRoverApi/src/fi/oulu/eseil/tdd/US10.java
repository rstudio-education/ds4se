package fi.oulu.eseil.tdd;

import static fi.oulu.eseil.tdd.Commons.generateObstacleInPosition;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromEast;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromEastBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromNorth;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromNorthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleLeftBorderCommandFromEast;
import static fi.oulu.eseil.tdd.Commons.goToMiddleLeftBorderCommandFromEastBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleLeftBorderCommandFromSouth;
import static fi.oulu.eseil.tdd.Commons.goToMiddleLeftBorderCommandFromSouthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleLeftBorderCommandNorth;
import static fi.oulu.eseil.tdd.Commons.goToMiddleLeftBorderCommandNorthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleOfPlanetFromEastBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleOfPlanetFromNorthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleOfPlanetFromSouthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromEast;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromEastBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromSouth;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromSouthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToTopLeftCornerFromEast;
import static fi.oulu.eseil.tdd.Commons.goToTopLeftCornerFromSouth;
import static fi.oulu.eseil.tdd.Commons.goToTopRightCornerFromSouth;
import static fi.oulu.eseil.tdd.Commons.generateCommand;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.anyOf;
import static org.junit.Assert.*;

import org.junit.Test;

import tdd.training.mars.MarsRover;




public class US10 {

	// Changed by Oscar
	@Test
	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingForwardLeft(){
		String leftEdgeObstacle = generateObstacleInPosition(0, 50);
		MarsRover roverL = new MarsRover(100, 100, leftEdgeObstacle);
		assertThat(roverL.executeCommand(goToMiddleLeftBorderCommandFromEast()), anyOf(equalToIgnoringCase("(1,50,N)(0,50)"), equalToIgnoringCase("(1,50,W)(0,50)")));
		
		String topEdgeObstacle = generateObstacleInPosition(50, 99);
		MarsRover roverT = new MarsRover(100, 100, topEdgeObstacle);
		assertThat(roverT.executeCommand(goToMiddleTopBorderFromEast()), anyOf(equalToIgnoringCase("(51,99,N)(50,99)"), equalToIgnoringCase("(51,99,W)(50,99)")));

		String bottomEdgeObstacle = generateObstacleInPosition(50, 0);
		MarsRover roverB = new MarsRover(100, 100, bottomEdgeObstacle);
		assertThat(roverB.executeCommand(goToMiddleBottomBorderFromEast()), anyOf(equalToIgnoringCase("(51,0,N)(50,0)"), equalToIgnoringCase("(51,0,W)(50,0)")));
	}
	
	@Test
	public void theRoverEncountersAnObstacleOnTheCornersOfThePlanetWhileMovingForwardLeft(){
		String topLeftCornerObstacle = generateObstacleInPosition(0, 99);
		
		MarsRover roverL = new MarsRover(100, 100, topLeftCornerObstacle);
		assertThat("(1,99,W)(0,99)", is(equalToIgnoringCase( roverL.executeCommand(goToTopLeftCornerFromEast()))));

	}
	
	@Test
	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingBackwardLeft(){
		String leftEdgeObstacle = generateObstacleInPosition(0, 50);
		MarsRover roverL = new MarsRover(100, 100, leftEdgeObstacle);
		assertThat("(1,50,E)(0,50)", is(equalToIgnoringCase( roverL.executeCommand(goToMiddleLeftBorderCommandFromEastBackwards()))));

		String topEdgeObstacle = generateObstacleInPosition(50, 99);
		MarsRover roverT = new MarsRover(100, 100, topEdgeObstacle);
		assertThat("(51,99,E)(50,99)", is(equalToIgnoringCase( roverT.executeCommand(goToTopRightCornerFromSouth() +"r" + generateCommand(50, "b")))));

		String bottomEdgeObstacle = generateObstacleInPosition(50, 0);
		MarsRover roverB = new MarsRover(100, 100, bottomEdgeObstacle);
		assertThat("(51,0,E)(50,0)", is(equalToIgnoringCase( roverB.executeCommand(goToMiddleBottomBorderFromEastBackwards()))));

	}
	
//	@Test
//	public void theRoverEncountersAnObstacleInTheMiddleOfThePlanetWhileMovingBackward(){
//		String middleObstacle = generateObstacleInPosition(50, 50);
//		MarsRover rover = new MarsRover(100, 100, middleObstacle);
//		assertThat("(51,50,E)(50,50)", is(equalToIgnoringCase( rover.executeCommand(goToMiddleOfPlanetFromEastBackwards()))));
//
//	}

}
