package fi.oulu.eseil.tdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.anyOf;





import org.junit.Test;






import tdd.training.mars.MarsRover;
import static fi.oulu.eseil.tdd.Commons.*;
public class US08 {
	@Test
	public void theRoverEncountersAnObstacleOnTheCornersOfThePlanetWhileMovingBackwardsLeft(){
		String topLeftCornerObstacle = generateObstacleInPosition(0, 99);
		MarsRover roverL = new MarsRover(100, 100, topLeftCornerObstacle);
		assertThat("(0,98,S)(0,99)", is(equalToIgnoringCase( roverL.executeCommand(goToTopLeftCornerFromSouthBackwards()))));
		MarsRover roverR = new MarsRover(100, 100, topLeftCornerObstacle);
		assertThat("(1,99,E)(0,99)",is( roverR.executeCommand(goToTopRightCornerFromSouth()+"r" + generateCommand(99, "b"))));
	}
	
//	@Test
//	public void theRoverEncountersAnObstacleOnTheCornersOfThePlanetWhileMovingBackwardsRight(){
//		String topRightCornerObstacle = generateObstacleInPosition(99, 99);
//		String bottomRightCornerObstacle = generateObstacleInPosition(99, 0);
//
//		MarsRover roverR = new MarsRover(100, 100, topRightCornerObstacle);
//		//assertThat("(98,99,W)(99,99)", is(equalToIgnoringCase( roverR.executeCommand(goToTopRightCornerFromWestBackwards()))));
//		assertThat("(99,98,S)(99,99)", is(equalToIgnoringCase( roverR.executeCommand(goToTopRightCornerFromSouthBackwards()))));
//		MarsRover roverT = new MarsRover(100, 100, bottomRightCornerObstacle);
//		//assertThat("(98,0,W)(99,0)", is(equalToIgnoringCase( roverT.executeCommand(goToBottomRightCornerFromWestBackwards()))));
//		assertThat("(99,1,N)(99,0)", is(equalToIgnoringCase( roverT.executeCommand(goToBottomRightCornerFromNorthBackwards()))));
//
//	}
	
//	@Test
//	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingBackwardLeft(){
//		String leftEdgeObstacle = generateObstacleInPosition(0, 50);
//		
//
//		MarsRover roverL = new MarsRover(100, 100, leftEdgeObstacle);
//		assertThat("(0,49,S)(0,50)", is(equalToIgnoringCase( roverL.executeCommand(goToMiddleLeftBorderCommandFromSouthBackwards()))));
//		assertThat("(0,51,N)(0,50)", is(equalToIgnoringCase( roverL.executeCommand(goToMiddleLeftBorderCommandNorthBackwards()))));
//		//assertThat("(1,50,E)(0,50)", is(equalToIgnoringCase( roverL.executeCommand(goToMiddleLeftBorderCommandFromEastBackwards()))));
//
//		
//	}
//	
//	@Test
//	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingBackwardRight(){
//		String rightEdgeObstacle = generateObstacleInPosition(99, 50);
//
//		MarsRover roverR = new MarsRover(100, 100, rightEdgeObstacle);
//		//assertThat("(98,50,W)(99,50)", is(equalToIgnoringCase( roverR.executeCommand(goToMiddleRightBorderFromWestBackwards()))));
//		assertThat("(99,51,N)(99,50)", is(equalToIgnoringCase( roverR.executeCommand(goToMiddleRightBorderFromNorthBackwards()))));
//		assertThat("(99,41,N)(99,50)", is(equalToIgnoringCase( roverR.executeCommand(goToMiddleRightBorderFromSouthBackwards()))));
//
//	}
	@Test
	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingBackwardRighTop(){
		String topEdgeObstacle = generateObstacleInPosition(50, 99);
		MarsRover roverT = new MarsRover(100, 100, topEdgeObstacle);
		//assertThat("(49,99,W)(50,99)", is(equalToIgnoringCase( roverT.executeCommand(goToMiddleTopBorderFromWestBackwards()))));
		//assertThat("(51,99,E)(50,99)", is(equalToIgnoringCase( roverT.executeCommand(goToMiddleTopBorderFromEastBackwards()))));
		assertThat("(50,98,S)(50,99)", is(equalToIgnoringCase( roverT.executeCommand(goToMiddleTopBorderFromSouthBackwards()))));

		String bottomEdgeObstacle = generateObstacleInPosition(50, 0);
		MarsRover roverB = new MarsRover(100, 100, bottomEdgeObstacle);
		//assertThat("(49,0,W)(50,0)", is(equalToIgnoringCase( roverB.executeCommand(goToMiddleBottomBorderFromWestBackwards()))));
		//assertThat("(51,0,E)(50,0)", is(equalToIgnoringCase( roverB.executeCommand(goToMiddleBottomBorderFromEastBackwards()))));
		assertThat("(50,1,N)(50,0)", is(equalToIgnoringCase( roverB.executeCommand(goToMiddleBottomBorderFromNorthBackwards()))));
	}
	// Changed by Oscar
	// Why is this test case now uncommented??
	//
	// Was wrong --> In the 2nd assertion, the rover did not roll back to (0,0,N) before moving
	@Test
	public void theRoverEncountersAnObstacleInTheMiddleOfThePlanetWhileMovingBackward(){
		String middleObstacle = generateObstacleInPosition(50, 50);
		MarsRover rover1 = new MarsRover(100, 100, middleObstacle);
		MarsRover rover2 = new MarsRover(100, 100, middleObstacle);

		//assertThat("(49,50,W)(50,50)", is(equalToIgnoringCase( rover.executeCommand(goToMiddleOfPlanetFromWestBackwards()))));
		assertThat("(50,51,N)(50,50)", is(equalToIgnoringCase( rover1.executeCommand(goToMiddleOfPlanetFromNorthBackwards()))));
		//assertThat("(51,50,E)(50,50)", is(equalToIgnoringCase( rover.executeCommand(goToMiddleOfPlanetFromEastBackwards()))));
		assertThat("(50,49,S)(50,50)", is(equalToIgnoringCase( rover2.executeCommand(goToMiddleOfPlanetFromSouthBackwards()))));
	}

	// Changed by Oscar
	@Test
	public void theRoverEncountersSevenObstaclesOnATourAroundThePlanet(){
		String obstacle1 = generateObstacleInPosition(0, 50);
		String obstacle2 = generateObstacleInPosition(0, 99);
		String obstacle3 = generateObstacleInPosition(50,99);
		String obstacle4 = generateObstacleInPosition(99, 99);
		String obstacle5 = generateObstacleInPosition(99, 50);
		String obstacle6 = generateObstacleInPosition(99, 0);
		String obstacle7 = generateObstacleInPosition(50, 0);
		MarsRover rover3 = new MarsRover(100, 100, obstacle1+obstacle2+obstacle3+obstacle4+obstacle5+obstacle6+obstacle7);
		assertThat(rover3.executeCommand(
				goToMiddleLeftBorderCommandFromSouthBackwards() + "rblbblbr" + generateCommand(50, "b") +
				"rblbr" + generateCommand(50, "b") + "rblbblbr" + generateCommand(50, "b") + "rblbr" + generateCommand(50, "b") +
				"rblbblbr" + generateCommand(50, "b") + "rblbr" + generateCommand(50, "b") + "rblbblbr" + generateCommand(50, "b") + "l"),
				anyOf(equalToIgnoringCase("(0,0,N)(0,50)(0,99)(50,99)(99,99)(99,50)(99,0)(50,0)"), equalToIgnoringCase("(0,49,S)(0,50)")));
//		String obstacle1 = generateObstacleInPosition(0, 50);
//		String obstacle2 = generateObstacleInPosition(0, 99);
//		String obstacle3 = generateObstacleInPosition(50,99);
//		String obstacle4 = generateObstacleInPosition(99, 99);
//		String obstacle5 = generateObstacleInPosition(99, 50);
//		String obstacle6 = generateObstacleInPosition(99, 0);
//		String obstacle7 = generateObstacleInPosition(50, 0);
		MarsRover rover4 = new MarsRover(100, 100, obstacle7+obstacle6+obstacle5+obstacle4+obstacle3+obstacle2+obstacle1);
		assertThat(rover4.executeCommand(goToMiddleBottomBorderFromWestBackwards() + "lbrbbrbl" + generateCommand(50, "b") +
				"lbrbl" + generateCommand(50, "b") + "lbrbbrbl" + generateCommand(50, "b") + "lbrbl" + generateCommand(50, "b") +
				"lbrbbrbl" + generateCommand(50, "b") + "lbrbl" + generateCommand(50, "b") + "lbrbbrbl" + generateCommand(50, "b")),
				anyOf(equalToIgnoringCase("(0,0,N)(50,0)(99,0)(99,50)(99,99)(50,99)(0,99)(0,50)"), equalToIgnoringCase("(49,0,W)(50,0)")));
	}

}
