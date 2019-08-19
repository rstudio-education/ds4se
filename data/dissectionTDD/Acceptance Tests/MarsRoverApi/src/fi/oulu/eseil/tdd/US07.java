package fi.oulu.eseil.tdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.anyOf;

import org.junit.Test;






import tdd.training.mars.MarsRover;
import static fi.oulu.eseil.tdd.Commons.*;
public class US07 {

//	@Test
//	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingForwardLeft(){
//		String leftEdgeObstacle = generateObstacleInPosition(0, 50);
//
//		MarsRover roverL = new MarsRover(100, 100, leftEdgeObstacle);
//		assertThat("(0,49,N)(0,50)", is(equalToIgnoringCase( roverL.executeCommand(goToMiddleLeftBorderCommandFromSouth()))));
//		assertThat("(0,51,N)(0,50)", is(equalToIgnoringCase( roverL.executeCommand(goToMiddleLeftBorderCommandNorth()))));
//	//	assertThat("(1,50,N)(0,50)", is(equalToIgnoringCase( roverL.executeCommand(goToMiddleLeftBorderCommandFromEast()))));
//		}
	
//	@Test
//	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingForwardRight(){
//		String rightEdgeObstacle = generateObstacleInPosition(99, 50);
//		MarsRover roverR = new MarsRover(100, 100, rightEdgeObstacle);
//		//assertThat("(98,50,N)(99,50)", is(equalToIgnoringCase( roverR.executeCommand(goToMiddleRightBorderFromWest()))));
//		assertThat("(99,51,N)(99,50)", is(equalToIgnoringCase( roverR.executeCommand(goToMiddleRightBorderFromNorth()))));
//		assertThat("(99,41,N)(99,50)", is(equalToIgnoringCase( roverR.executeCommand(goToMiddleRightBorderFromSouth()))));
//	
//	}
	// Changed by Oscar
	@Test
	public void theRoverEncountersAnObstacleOnTheEdgesOfThePlanetWhileMovingForward(){
		String topEdgeObstacle = generateObstacleInPosition(50, 99);

		MarsRover roverT = new MarsRover(100, 100, topEdgeObstacle);
		//assertThat("(49,99,N)(50,99)", is(equalToIgnoringCase( roverT.executeCommand(goToMiddleTopBorderFromWest()))));
		//assertThat("(51,99,N)(50,99)", is(equalToIgnoringCase( roverT.executeCommand(goToMiddleTopBorderFromEast()))));
		assertThat("(50,98,N)(50,99)", is(equalToIgnoringCase( roverT.executeCommand(goToMiddleTopBorderFromSouth()))));

		String bottomEdgeObstacle = generateObstacleInPosition(50, 0);
		MarsRover roverB = new MarsRover(100, 100, bottomEdgeObstacle);
		//assertThat("(49,0,N)(50,0)", is(equalToIgnoringCase( roverB.executeCommand(goToMiddleBottomBorderFromWest()))));
		//assertThat("(51,0,N)(50,0)", is(equalToIgnoringCase( roverB.executeCommand(goToMiddleBottomBorderFromEast()))));
		assertThat(roverB.executeCommand(goToMiddleBottomBorderFromNorth()), anyOf(equalToIgnoringCase("(50,1,N)(50,0)"), equalToIgnoringCase("(50,1,S)(50,0)")));
	}
	
	
	@Test
	public void theRoverEncountersAnObstacleOnTheCornersOfThePlanetWhileMovingForward(){
		String topLeftCornerObstacle = generateObstacleInPosition(0, 99);

		MarsRover roverL = new MarsRover(100, 100, topLeftCornerObstacle);
		assertThat("(0,98,N)(0,99)", is(equalToIgnoringCase( roverL.executeCommand(goToTopLeftCornerFromSouth()))));
		//assertThat("(1,99,W)(0,99)", is(equalToIgnoringCase( roverL.executeCommand(goToTopLeftCornerFromEast()))));

		String topRightCornerObstacle = generateObstacleInPosition(99, 99);
		MarsRover roverR = new MarsRover(100, 100, topRightCornerObstacle);
		//assertThat("(98,99,E)(99,99)", is(equalToIgnoringCase( roverR.executeCommand(goToTopRightCornerFromWest()))));
		assertThat("(99,98,N)(99,99)", is(equalToIgnoringCase( roverR.executeCommand(goToTopRightCornerFromSouth()))));

		String bottomRightCornerObstacle = generateObstacleInPosition(99, 0);
		MarsRover roverT = new MarsRover(100, 100, bottomRightCornerObstacle);
		//assertThat("(98,0,E)(99,0)", is(equalToIgnoringCase( roverT.executeCommand(goToBottomRightCornerFromWest()))));
		assertThat("(99,1,S)(99,0)", is(equalToIgnoringCase( roverT.executeCommand(goToBottomRightCornerFromNorth()))));

	}
	
	@Test
	public void theRoverEncountersAnObstacleInTheMiddleOfThePlanetWhileMovingForward(){
		String middleObstacle = generateObstacleInPosition(50, 50);
		MarsRover rover = new MarsRover(100, 100, middleObstacle);
		MarsRover rover2 = new MarsRover(100, 100, middleObstacle);
		MarsRover rover3 = new MarsRover(100, 100, middleObstacle);
		MarsRover rover4 = new MarsRover(100, 100, middleObstacle);
		assertThat("(49,50,E)(50,50)", is(equalToIgnoringCase( rover.executeCommand(goToMiddleOfPlanetFromWest()))));
		assertThat("(50,51,S)(50,50)", is(equalToIgnoringCase( rover2.executeCommand(goToMiddleOfPlanetFromNorth()))));
		assertThat("(51,50,W)(50,50)", is(equalToIgnoringCase( rover3.executeCommand(goToMiddleOfPlanetFromEast()))));
		assertThat("(50,49,N)(50,50)", is(equalToIgnoringCase( rover4.executeCommand(goToMiddleOfPlanetFromSouth()))));
	}
	// Changed by Oscar
	@Test 
	public void theRoverEncounters2ObstaclesInARowFromLanding(){
		String firstObstacle = generateObstacleInPosition(0, 1);
		String secondObstacle = generateObstacleInPosition(1,0);

		MarsRover rover = new MarsRover(100, 100, firstObstacle+secondObstacle);
		assertThat(rover.executeCommand("frfl"), anyOf(equalToIgnoringCase("(0,0,N)(0,1)(1,0)"), equalToIgnoringCase("(0,0,N)(0,1)")));
	}

	// Changed by Oscar
	@Test
	public void theRoverEncounters2ObstaclesInARow(){
		String firstHorizontalObstacle = generateObstacleInPosition(1, 1);
		String secondHorizontalObstacle = generateObstacleInPosition(3, 1);
		String firstVerticalObstacle = generateObstacleInPosition(1, 0);
		String secondVerticalObstacle = generateObstacleInPosition(1, 2);

		MarsRover roverH = new MarsRover(100, 100, firstHorizontalObstacle+secondHorizontalObstacle);
		assertThat(roverH.executeCommand("rflfrflfrflf"), anyOf(equalToIgnoringCase("(2,2,N)(1,1)(3,1)"), equalToIgnoringCase("(1,0,N)(1,1)")));

		MarsRover roverV = new MarsRover(100, 100, firstVerticalObstacle+secondVerticalObstacle);
		assertThat(roverV.executeCommand("rflfrflfrfl"), anyOf(equalToIgnoringCase("(2,1,N)(1,0)(1,2)"), equalToIgnoringCase("(0,0,E)(1,0)")));
	}

	// Changed by Oscar
	@Test
	public void theRoverEncounters3ObstaclesInARow(){
		String obstacle11 = Commons.generateObstacleInPosition(1, 1);
		String obstacle22 = Commons.generateObstacleInPosition(2, 2);
		String obstacle31 = Commons.generateObstacleInPosition(3, 1);
		String obstacle20 = Commons.generateObstacleInPosition(2, 0);

		MarsRover rover1 = new MarsRover(100, 100, obstacle11+obstacle22 + obstacle31);
		assertThat(rover1.executeCommand("rfflfrflflfr"), anyOf(equalToIgnoringCase("(2,1,N)(3,1)(2,2)(1,1)"), equalToIgnoringCase("(2,1,E)(3,1)")));

		MarsRover rover2 = new MarsRover(100, 100, obstacle20+obstacle22 + obstacle31);
		assertThat(rover2.executeCommand("rflfrfrflflf"), anyOf(equalToIgnoringCase("(2,1,N)(2,0)(3,1)(2,2)"), equalToIgnoringCase("(2,1,S)(2,0)")));

		MarsRover rover3 = new MarsRover(100, 100, obstacle11+obstacle20 + obstacle31);
		assertThat(rover3.executeCommand("ffrffrfrflflfl"), anyOf(equalToIgnoringCase("(2,1,N)(1,1)(2,0)(3,1)"), equalToIgnoringCase("(2,1,W)(1,1)")));
	}

//	@Test
//	public void theRoverEncountersSevenObstaclesOnATourAroundThePlanet(){
//		String obstacle1 = Commons.generateObstacleInPosition(0, 50);
//		String obstacle2 = Commons.generateObstacleInPosition(0, 99);
//		String obstacle3 = Commons.generateObstacleInPosition(50,99);
//		String obstacle4 = Commons.generateObstacleInPosition(99, 99);
//		String obstacle5 = Commons.generateObstacleInPosition(99, 50);
//		String obstacle6 = Commons.generateObstacleInPosition(99, 0);
//		String obstacle7 = Commons.generateObstacleInPosition(50, 0);
//
//		MarsRover rover = new MarsRover(100, 100, obstacle1+obstacle2+obstacle3+obstacle4+obstacle5+obstacle6+obstacle7);
//		assertThat("(0,0,N)(0,50)(0,99)(50,99)(99,99)(99,50)(99,0)(50,0)", is(equalToIgnoringCase( rover.executeCommand(
//				Commons.goToMiddleLeftBorderCommandFromSouth() + "rflfflfr" + Commons.generateCommand(50, "f") +
//				"rflfr" + Commons.generateCommand(50, "f") + "rflfflfr" + Commons.generateCommand(50, "f") +
//				"rflfr" + Commons.generateCommand(50, "f") + "rflfflfr" + Commons.generateCommand(50, "f") +
//				"rflfr" + Commons.generateCommand(50, "f") + "rflfflfr" + Commons.generateCommand(50, "f") + "r"
//				))));
//
////		String obstacle1 = Commons.generateObstacleInPosition(0, 50);
////		String obstacle2 = Commons.generateObstacleInPosition(0, 99);
////		String obstacle3 = Commons.generateObstacleInPosition(50,99);
////		String obstacle4 = Commons.generateObstacleInPosition(99, 99);
////		String obstacle5 = Commons.generateObstacleInPosition(99, 50);
////		String obstacle6 = Commons.generateObstacleInPosition(99, 0);
////		String obstacle7 = Commons.generateObstacleInPosition(50, 0);
//
//		MarsRover rover2 = new MarsRover(100, 100, obstacle7+obstacle6+obstacle5+obstacle4+obstacle3+obstacle2+obstacle1);
//		assertThat("(0,0,N)(50,0)(99,0)(99,50)(99,99)(50,99)(0.99)(0,50)", is(equalToIgnoringCase(rover2.executeCommand(Commons.goToMiddleBottomBorderFromWest() +
//				"lfrffrfl" + Commons.generateCommand(50, "f") + "lfrfl" + Commons.generateCommand(50, "f") + "lfrffrfl" + Commons.generateCommand(50, "f") +
//				"lfrfl" + Commons.generateCommand(50, "f") + "lfrffrfl" + Commons.generateCommand(50, "f") + "lfrfl" + Commons.generateCommand(50, "f") +
//				"lfrffrfl" + Commons.generateCommand(50, "f") + "rr"
//				))));
//	}


}
