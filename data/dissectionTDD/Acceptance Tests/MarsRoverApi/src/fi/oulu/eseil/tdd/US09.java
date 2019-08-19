package fi.oulu.eseil.tdd;

import static fi.oulu.eseil.tdd.Commons.generateObstacleInPosition;
import static fi.oulu.eseil.tdd.Commons.goToBottomRightCornerFromNorth;
import static fi.oulu.eseil.tdd.Commons.goToBottomRightCornerFromNorthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToBottomRightCornerFromWest;
import static fi.oulu.eseil.tdd.Commons.goToBottomRightCornerFromWestBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromEast;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromEastBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromNorth;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromNorthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromWest;
import static fi.oulu.eseil.tdd.Commons.goToMiddleBottomBorderFromWestBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleOfPlanetFromEastBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleOfPlanetFromNorthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleOfPlanetFromSouthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleOfPlanetFromWestBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleRightBorderFromNorth;
import static fi.oulu.eseil.tdd.Commons.goToMiddleRightBorderFromNorthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleRightBorderFromSouth;
import static fi.oulu.eseil.tdd.Commons.goToMiddleRightBorderFromSouthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleRightBorderFromWest;
import static fi.oulu.eseil.tdd.Commons.goToMiddleRightBorderFromWestBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromEast;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromEastBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromSouth;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromSouthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromWest;
import static fi.oulu.eseil.tdd.Commons.goToMiddleTopBorderFromWestBackwards;
import static fi.oulu.eseil.tdd.Commons.goToTopLeftCornerFromEast;
import static fi.oulu.eseil.tdd.Commons.goToTopLeftCornerFromSouth;
import static fi.oulu.eseil.tdd.Commons.goToTopRightCornerFromSouth;
import static fi.oulu.eseil.tdd.Commons.goToTopRightCornerFromSouthBackwards;
import static fi.oulu.eseil.tdd.Commons.goToTopRightCornerFromWest;
import static fi.oulu.eseil.tdd.Commons.goToTopRightCornerFromWestBackwards;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.anyOf;
import static org.junit.Assert.*;

import org.junit.Test;

import tdd.training.mars.MarsRover;





public class US09 {

	// Changed by Oscar
	@Test
	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingForwardRight(){
		String rightEdgeObstacle = generateObstacleInPosition(99, 50);
		MarsRover roverR = new MarsRover(100, 100, rightEdgeObstacle);
		assertThat(roverR.executeCommand(goToMiddleRightBorderFromWest()), anyOf(equalToIgnoringCase("(98,50,N)(99,50)"), equalToIgnoringCase("(98,50,E)(99,50)")));
	
		String topEdgeObstacle = generateObstacleInPosition(50, 99);
		MarsRover roverT = new MarsRover(100, 100, topEdgeObstacle);
		assertThat(roverT.executeCommand(goToMiddleTopBorderFromWest()), anyOf(equalToIgnoringCase("(49,99,N)(50,99)"), equalToIgnoringCase("(49,99,E)(50,99)")));

	
		String bottomEdgeObstacle = generateObstacleInPosition(50, 0);
		MarsRover roverB = new MarsRover(100, 100, bottomEdgeObstacle);
		assertThat(roverB.executeCommand(goToMiddleBottomBorderFromWest()), anyOf(equalToIgnoringCase("(49,0,N)(50,0)"), equalToIgnoringCase("(49,0,E)(50,0)")));
	}
	
//	@Test
//	public void theRoverEncountersAnObstacleOnTheCornersOfThePlanetWhileMovingForwardLeft(){
//		String topRightCornerObstacle = generateObstacleInPosition(99, 99);
//		MarsRover roverR = new MarsRover(100, 100, topRightCornerObstacle);
//		assertThat("(98,99,E)(99,99)", is(equalToIgnoringCase( roverR.executeCommand(goToTopRightCornerFromWest()))));
//	
//
//		String bottomRightCornerObstacle = generateObstacleInPosition(99, 0);
//		MarsRover roverT = new MarsRover(100, 100, bottomRightCornerObstacle);
//		assertThat("(98,0,E)(99,0)", is(equalToIgnoringCase( roverT.executeCommand(goToBottomRightCornerFromWest()))));
//
//	}
	
	@Test
	public void theRoverEncountersAnObstacleOnTheCornersOfThePlanetWhileMovingBackwardsRight(){
		String topRightCornerObstacle = generateObstacleInPosition(99, 99);
		String bottomRightCornerObstacle = generateObstacleInPosition(99, 0);

		MarsRover roverR = new MarsRover(100, 100, topRightCornerObstacle);
		assertThat("(98,99,W)(99,99)", is(equalToIgnoringCase( roverR.executeCommand(goToTopRightCornerFromWestBackwards()))));

		MarsRover roverT = new MarsRover(100, 100, bottomRightCornerObstacle);
		assertThat("(98,0,W)(99,0)", is(equalToIgnoringCase( roverT.executeCommand(goToBottomRightCornerFromWestBackwards()))));

	}
	
	@Test
	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingBackwardRight(){
		String rightEdgeObstacle = generateObstacleInPosition(99, 50);

		MarsRover roverR = new MarsRover(100, 100, rightEdgeObstacle);
		assertThat("(98,50,W)(99,50)", is(equalToIgnoringCase( roverR.executeCommand(goToMiddleRightBorderFromWestBackwards()))));

		String topEdgeObstacle = generateObstacleInPosition(50, 99);
		MarsRover roverT = new MarsRover(100, 100, topEdgeObstacle);
		assertThat("(49,99,W)(50,99)", is(equalToIgnoringCase( roverT.executeCommand(goToMiddleTopBorderFromWestBackwards()))));


		String bottomEdgeObstacle = generateObstacleInPosition(50, 0);
		MarsRover roverB = new MarsRover(100, 100, bottomEdgeObstacle);
		assertThat("(49,0,W)(50,0)", is(equalToIgnoringCase( roverB.executeCommand(goToMiddleBottomBorderFromWestBackwards()))));

	}
//	@Test
//	public void theRoverEncountersAnObstacleInTheMiddleOfThePlanetWhileMovingBackward(){
//		String middleObstacle = generateObstacleInPosition(50, 50);
//		MarsRover rover = new MarsRover(100, 100, middleObstacle);
//
//		assertThat("(49,50,W)(50,50)", is(equalToIgnoringCase( rover.executeCommand(goToMiddleOfPlanetFromWestBackwards()))));
//		
//	}
	

}
