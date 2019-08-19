package fi.oulu.eseil.tdd;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;


import tdd.training.mars.MarsRover;




public class US01 {
	MarsRover rover;
/*	String goToMiddleOfGridCommand;
	String goToMiddleLeftBorderCommand;
	String goToTopLeftCornerCommand;
	String goToMiddleTopBorderCommand;
	String goToTopRightCornerCommand;
	String goToMiddleRightBorderCommand;
	String goToBottomRightCornerCommand;
	String goToMiddleBottomBorderCommand;*/

	@Before
	public void setup(){
		this.rover = new MarsRover(100, 100, "");
	/*	this.goToMiddleOfGridCommand = goToTheMiddleOfTheGridCommand();
		this.goToMiddleLeftBorderCommand = goToMiddleLeftBorderCommand();
		this.goToTopLeftCornerCommand = goToTopLeftCorner();
		this.goToMiddleTopBorderCommand = goToMiddleTopBorder();
		this.goToTopRightCornerCommand = goToTopRightCorner();
		this.goToMiddleRightBorderCommand = goToMiddleRightBorder();
		this.goToBottomRightCornerCommand = goToBottomRightCorner();
		this.goToMiddleBottomBorderCommand = goToMiddleBottomBorder();*/
	}
	
	

	@Test
	public void theRoverStaysAtOriginOnceLandedAndExecutedAnEmptyCommand() {
		assertThat("(0,0,N)", is(equalToIgnoringCase(this.rover.executeCommand(""))));
		
	}
	
	/*@Test
	public void theRoverMovesAt01OnceLandingAndExecutingAForwardCommand() {
		assertThat("(0,1,N)", is(equalToIgnoringCase(this.rover.executeCommand("f"))));
	}
	
	
	@Test
	public void theRoverMovesNOnceLandingAndExecutingA3ForwardCommand() {
		assertThat("(0,3,N)", is(equalToIgnoringCase(this.rover.executeCommand("fff"))));
	}
	
	@Test
	public void theRoverMovesAtTheEdgeOfTheGridOnceLandingAndExecutingA100ForwardCommand() {
		String command = generateCommand(99, "f");
		assertThat("(0,99,N)", is(equalToIgnoringCase( this.rover.executeCommand(command))));
	}
	
	@Test
	public void theRoverMovesToTheNextSquareNFromARandomPosition() {
		String command= generateCommand(26, "f") + "r" + generateCommand(56, "f") + "l";
		command += "f";
		assertThat("(56,27,N)", is(equalToIgnoringCase( this.rover.executeCommand(command))));
	}


	@Test
	public void theRoverShouldChangeFacingTWhenGivenATurnCommandAfterLanding(){
		assertThat("(0,0,E)", is(equalToIgnoringCase( this.rover.executeCommand("l"))));
		assertThat("(0,0,W)", is(equalToIgnoringCase( this.rover.executeCommand("r"))));
	}
	
	@Test
	public void theRoverFacesSouthAfterLandingAndExecutingATurnCommandToTimesInARow(){
		assertThat("(0,0,S)", is(equalToIgnoringCase( this.rover.executeCommand("rr"))));
		assertThat("(0,0,S)", is(equalToIgnoringCase( this.rover.executeCommand("ll"))));
	}
	
	@Test
	public void theRoverFacesWestAfterLandingAndExecutingALeftCommandThreeTimesInARow(){
		assertThat("(0,0,W)", is(equalToIgnoringCase(this.rover.executeCommand("lll"))));	
	}
	
	@Test
	public void theRoverFacesEastAfterLandingAndExecutingARightCommandThreeTimesInARow(){
		assertThat("(0,0,E)", is(equalToIgnoringCase(this.rover.executeCommand("rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommand(){
		assertThat("(0,0,N)", is(equalToIgnoringCase(this.rover.executeCommand("llll"))));
		assertThat("(0,0,N)", is(equalToIgnoringCase(this.rover.executeCommand("rrrr"))));
	}
	
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleOfTheGrid(){
		assertThat("(12,34,E)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleOfGridCommand+"l"))));
		assertThat("(12,34,W)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleOfGridCommand+"r"))));
	}

	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleOfTheGrid(){
		assertThat("(12,34,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleOfGridCommand+"rr"))));
		assertThat("(12,34,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleOfGridCommand+"ll"))));
	}
	
	@Test
	public void theRoverFacesWestAfterExecutingALeftCommandThreeTimesInARowInTheMiddleOfTheGrid(){
		assertThat("(12,34,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"lll"))));	
	}

	@Test
	public void theRoverFacesEastAfterExecutingARightCommandThreeTimesInARowInTheMiddleOfTheGrid(){
		assertThat("(12,34,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheMiddleOfTheGrid(){
		assertThat("(12,34,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"llll"))));
		assertThat("(12,34,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"rrrr"))));
	}
	
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleLeftBorderTurningLeft(){
		assertThat("(0,50,W)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleLeftBorderCommand+"l"))));
		
	}
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleLeftBorderTurningRight(){
		
		assertThat("(0,50,E)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleLeftBorderCommand+"r"))));
	}


	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleOfLeftBorderTurningRight(){
		assertThat("(0,50,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleLeftBorderCommand+"rr"))));
		
	}
	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleOfLeftBorderTurningLeft(){
		assertThat("(0,50,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"ll"))));
	}
	
	@Test
	public void theRoverFacesEastAfterExecutingALeftCommandThreeTimesInARowInTheMiddleLeftBorder(){
		assertThat("(0,50,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"lll"))));	
	}
	
	@Test
	public void theRoverFacesWestAfterExecutingARightCommandThreeTimesInARowInTheMiddleLeftBorder(){
		assertThat("(0,50,W)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleLeftBorderCommand+"rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheMiddleLeftBorderTurningLeft(){
		assertThat("(0,50,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"llll"))));

	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheMiddleLeftBorderTurningRight(){
	
		assertThat("(0,50,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"rrrr"))));
	}
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheTopLeftCornerTurningLeft(){
		assertThat("(0,99,W)", is(equalToIgnoringCase( this.rover.executeCommand(goToTopLeftCornerCommand+"l"))));
		
	}
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheTopLeftCornerTurningRight(){
		assertThat("(0,99,E)", is(equalToIgnoringCase( this.rover.executeCommand(goToTopLeftCornerCommand+"r"))));
	}


	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheTopLeftCornerTurningRight(){
		assertThat("(0,99,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToTopLeftCornerCommand+"rr"))));
	}
	
	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheTopLeftCornerTurningLeft(){
		assertThat("(0,99,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToTopLeftCornerCommand+"ll"))));
	}
	@Test
	public void theRoverFacesWestAfterExecutingALeftCommandThreeTimesInARowInTheTopLeftCorner(){
		assertThat("(0,99,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopLeftCornerCommand+"lll"))));	
	}
	
	@Test
	public void theRoverFacesEastAfterExecutingARightCommandThreeTimesInARowInTheTopLeftCorner(){
		assertThat("(0,99,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopLeftCornerCommand+"rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheTopLeftCornerTurningLeft(){
		assertThat("(0,99,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopLeftCornerCommand+"llll"))));
	}
	
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheTopLeftCornerTurningRight(){
		assertThat("(0,99,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopLeftCornerCommand+"rrrr"))));
	}
	
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleTopBorderTurningLeft(){
		assertThat("(50,99,W)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleTopBorderCommand+"l"))));
	}
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleTopBorderTurningRight(){
		assertThat("(50,99,E)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleTopBorderCommand+"r"))));
	}


	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleTopBorderTurningRight(){
		assertThat("(50,99,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"rr"))));
	}
	
	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleTopBorderTurningLeft(){
		assertThat("(50,99,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"ll"))));
	}
	
	@Test
	public void theRoverFacesWestAfterExecutingALeftCommandThreeTimesInARowInTheMiddleTopBorder(){
		assertThat("(50,99,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"lll"))));	
	}
	
	@Test
	public void theRoverFacesEastAfterExecutingARightCommandThreeTimesInARowInTheMiddleTopBorder(){
		assertThat("(50,99,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheMiddleTopBorderTurningLeft(){
		assertThat("(50,99,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"llll"))));
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheMiddleTopBorderTurningRight(){
		assertThat("(50,99,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"rrrr"))));
	}
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheTopRightCornerTurningLeft(){
		assertThat("(99,99,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"l"))));

	}
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheTopRightCornerTurningRight(){
		assertThat("(99,99,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"r"))));
	}
	

	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheTopRightCornerTurningRight(){
		assertThat("(99,99,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"rr"))));
	}
	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheTopRightCornerTurningLeft(){
		assertThat("(99,99,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"ll"))));
	}
	@Test
	public void theRoverFacesWestAfterExecutingALeftCommandThreeTimesInARowInTheTopRightCorner(){
		assertThat("(99,99,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"lll"))));	
	}
	
	@Test
	public void theRoverFacesEastAfterExecutingARightCommandThreeTimesInARowInTheTopRightCorner(){
		assertThat("(99,99,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheTopRightCornerTurningLeft(){
		assertThat("(99,99,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"llll"))));
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInTheTopRightCornerTurningRight(){
		assertThat("(99,99,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"rrrr"))));
	}
	
	
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleRightBorderTurningLeft(){
		assertThat("(99,50,W)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleRightBorderCommand+"l"))));
	}

	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleRightBorderTurningRight(){
		assertThat("(99,50,E)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleRightBorderCommand+"r"))));
	}
	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleRightBorderTurningRight(){
		assertThat("(99,50,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleRightBorderCommand+"rr"))));
	}
	
	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleRightBorderTurningLeft(){
		assertThat("(99,50,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleRightBorderCommand+"ll"))));
	}
	
	
	@Test
	public void theRoverFacesWestAfterExecutingALeftCommandThreeTimesInARowInTheMiddleRightBorder(){
		assertThat("(99,50,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"lll"))));	
	}
	
	@Test
	public void theRoverFacesEastAfterExecutingARightCommandThreeTimesInARowInTheMiddleRightBorder(){
		assertThat("(99,50,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"rrr"))));	
	}
	
	@Test
	public void theRoverrIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInMiddleRightBorderTurningLeft(){
		assertThat("(99,50,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"llll"))));
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInMiddleRightBorderTurningRight(){
		assertThat("(99,50,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"rrrr"))));
	}
	
	
	
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheBottomRightCornerTurningLeft(){
		assertThat("(99,0,W)", is(equalToIgnoringCase( this.rover.executeCommand(goToBottomRightCornerCommand+"l"))));
	}

	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheBottomRightCornerTurningRight(){
		assertThat("(99,0,E)", is(equalToIgnoringCase( this.rover.executeCommand(goToBottomRightCornerCommand+"r"))));
	}

	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheBottomRightCornerTurningRight(){
		assertThat("(99,0,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToBottomRightCornerCommand+"rr"))));
	}
	
	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheBottomRightCornerTurningLeft(){
		assertThat("(99,0,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToBottomRightCornerCommand+"ll"))));
	}
	
	@Test
	public void theRoverFacesWestAfterExecutingALeftCommandThreeTimesInARowInThBottomRightCorner(){
		assertThat("(99,0,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToBottomRightCornerCommand+"lll"))));	
	}
	
	@Test
	public void theRoverFacesEastAfterExecutingARightCommandThreeTimesInARowInTheBottomRightCorner(){
		assertThat("(99,0,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToBottomRightCornerCommand+"rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInBottomRightCornerTurningLeft(){
		assertThat("(99,0,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToBottomRightCornerCommand+"llll"))));
	}
	
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInBottomRightCornerTurningRight(){
		assertThat("(99,0,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToBottomRightCornerCommand+"rrrr"))));
	}
	
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleBottomBorderTurningLeft(){
		assertThat("(50,0,W)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleBottomBorderCommand+"l"))));
	}
	
	@Test
	public void theRoverShouldChangeFacingWhenGivenATurnCommandInTheMiddleBottomBorderTurningRight(){
		assertThat("(50,0,E)", is(equalToIgnoringCase (this.rover.executeCommand(goToMiddleBottomBorderCommand+"r"))));
	}

	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleBottomBorderTurningRight(){
		assertThat("(50,0,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleBottomBorderCommand+"rr"))));
	}
	
	@Test
	public void theRoverFacesSouthExecutingATurnCommandToTimesInARowInTheMiddleBottomBorderTurningLeft(){
		assertThat("(50,0,S)", is(equalToIgnoringCase( this.rover.executeCommand(goToMiddleBottomBorderCommand+"ll"))));
	}
	
	@Test
	public void theRoverFacesWestAfterExecutingALeftCommandThreeTimesInARowInTheMiddleBottomBorder(){
		assertThat("(50,0,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"lll"))));	
	}
	
	@Test
	public void theRoverFacesEastAfterExecutingARightCommandThreeTimesInARowInTheMiddleBottomBorder(){
		assertThat("(50,0,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"rrr"))));	
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInMiddleBottomBorderTurningLeft(){
		assertThat("(50,0,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"llll"))));
	}
	
	@Test
	public void theRoverIsFacingTheSameDirectionAfterExecutingFourTimesTheSameTurnCommandInMiddleBottomBorderTurningRight(){
		assertThat("(50,0,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"rrrr"))));
	}
	
	@Test
	public void roverCanMoveFromLandingPositionRightDirection(){
		assertThat("(1,0,E)", is(equalToIgnoringCase(this.rover.executeCommand("rf"))));
	}
	@Test
	public void roverCanMoveFromLandingPositionLeftDirection(){
		assertThat("(1,0,W)", is(equalToIgnoringCase(this.rover.executeCommand("lb"))));
	}
	@Test
	public void roverCanMoveFromLandingPositionForwardDirection(){
		assertThat("(0,1,N)", is(equalToIgnoringCase(this.rover.executeCommand("f"))));
	}
	@Test
	public void roverCanMoveFromLandingPositionBackwardDirection(){
		assertThat("(0,1,S)", is(equalToIgnoringCase(this.rover.executeCommand("llb"))));
	}
	
	@Test
	public void roverCanMoveFromMiddleLeftBorder(){
		assertThat("(0,49,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"b"))));
		assertThat("(0,51,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"f"))));
		assertThat("(1,50,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"rf"))));
		
		assertThat("(0,49,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"llf"))));
		assertThat("(0,51,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"llb"))));
		assertThat("(1,50,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleLeftBorderCommand+"lb"))));
	}
	
	@Test
	public void roverCanMoveFromTopLeftCorner(){
		assertThat("(0,98,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopLeftCornerCommand+"b"))));
		assertThat("(1,99,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopLeftCornerCommand+"rf"))));
		
		assertThat("(0,98,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopLeftCornerCommand+"llf"))));
		assertThat("(1,99,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopLeftCornerCommand+"lb"))));
	}
	
	@Test
	public void roverCanMoveFromMiddleTopBorder(){
		assertThat("(50,98,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"b"))));
		assertThat("(49,99,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"lf"))));
		assertThat("(51,99,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"rf"))));
		assertThat("(50,98,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"llb"))));
		assertThat("(49,99,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"rb"))));
		assertThat("(51,99,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleTopBorderCommand+"lb"))));
	}
	
	
	@Test
	public void roverCanMoveFromTopRightCorner(){
		assertThat("(99,98,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"b"))));
		assertThat("(98,99,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"lf"))));
		
		assertThat("(99,98,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"llf"))));
		assertThat("(98,99,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToTopRightCornerCommand+"rb"))));
	}
	
	@Test
	public void roverCanMoveFromMiddleRightBorder(){
		assertThat("(99,51,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"f"))));
		assertThat("(99,49,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"b"))));
		assertThat("(98,50,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"lf"))));
		
		assertThat("(99,51,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"llb"))));
		assertThat("(99,49,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"llf"))));
		assertThat("(98,50,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleRightBorderCommand+"rb"))));
	}
	
	@Test
	public void roverCanMoveFromBottomRightCorner(){
		assertThat("(99,1,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToBottomRightCornerCommand+"f"))));
		assertThat("(98,0,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToBottomRightCornerCommand+"lf"))));
		
		assertThat("(99,1,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToBottomRightCornerCommand+"llb"))));
		assertThat("(98,0,E)",is(equalToIgnoringCase(this.rover.executeCommand(goToBottomRightCornerCommand+"rb"))));
	}
	
	@Test
	public void roverCanMoveFromMiddleBottomBorder(){
		assertThat("(51,0,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"rf"))));
		assertThat("(49,0,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"lf"))));
		assertThat("(50,1,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"f"))));
		
		assertThat("(51,0,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"lb"))));
		assertThat("(49,0,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"rb"))));
		assertThat("(50,1,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleBottomBorderCommand+"llb"))));
	}
	
	@Test
	public void roverCanMoveFromMiddleOfTheGrid(){
		assertThat("(12,35,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"f"))));
		assertThat("(12,33,N)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"b"))));
		assertThat("(11,34,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"lf"))));
		assertThat("(13,34,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"rf"))));
		
		assertThat("(12,35,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"llb"))));
		assertThat("(12,33,S)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"rrf"))));
		assertThat("(11,34,E)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"rb"))));
		assertThat("(13,34,W)", is(equalToIgnoringCase(this.rover.executeCommand(goToMiddleOfGridCommand+"lb"))));
	}
	
	private String goToMiddleBottomBorder(){
		String command = "r" + generateCommand(50, "f") + "l";
		return command;
	}
	private String goToMiddleRightBorder(){
		String command = generateCommand(50, "f") + "r" + generateCommand(99, "f") + "l";
		return command;
	}	
	private String goToBottomRightCorner(){
		String command =  "r" + generateCommand(99, "f") + "l";
		return command;
	}
	private String goToTopLeftCorner(){
		String command = generateCommand(99, "f");
		return command;
	}
	private String goToMiddleTopBorder(){
		String command = generateCommand(99, "f") + "r" + generateCommand(50, "f") + "l";
		return command;
	}
	private String goToTopRightCorner(){
		String command = generateCommand(99, "f") + "r" + generateCommand(99, "f") + "l";
		return command;
	}
	private String goToTheMiddleOfTheGridCommand() {
		String command = generateCommand(34, "f") + "r" + generateCommand(12, "f") + "l";
		return command;
	}
	private String generateCommand(int times,String symbol) {
		String command  = "";
		for (int i = 0; i < times; i++) {
			 command += symbol;
		}
		return command;
	}
	private String goToMiddleLeftBorderCommand(){
		String command = generateCommand(50, "f");
		return command;
	}
*/
}
