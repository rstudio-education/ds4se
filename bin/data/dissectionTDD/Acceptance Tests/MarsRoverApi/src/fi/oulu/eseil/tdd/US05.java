package fi.oulu.eseil.tdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tdd.training.mars.*;



public class US05 {
	MarsRover rover;
	@Before 
	public void setUp(){
		this.rover = new MarsRover(100, 100, "");
	}
	@Test
	public void moveLeftInsideGridOnClearPathNorthBounded() {
		this.rover.executeCommand("fffffrffffffffff");
		this.rover.executeCommand("ll");
		assertThat("(9,5,W)", is(equalToIgnoringCase( this.rover.executeCommand("f"))));
		assertThat("(10,5,W)", is(equalToIgnoringCase( this.rover.executeCommand("b"))));	
	}
	@Test
	public void moveLeftInsideGridOnClearPathSouthBounded() {
		this.rover.executeCommand("fffffrffffffffff");
		this.rover.executeCommand("rr");
		assertThat("(9,5,W)", is(equalToIgnoringCase( this.rover.executeCommand("f"))));
		assertThat("(10,5,W)", is(equalToIgnoringCase( this.rover.executeCommand("b"))));	
	}
	
	@Test
	public void moveRightInsideGridOnClearPathNorthBounded() {
		this.rover.executeCommand("fffffr");
		assertThat("(5,5,E)", is(equalToIgnoringCase( this.rover.executeCommand("fffff"))));
		assertThat("(4,5,E)", is(equalToIgnoringCase( this.rover.executeCommand("b"))));	
	}
	@Test
	public void moveRightInsideGridOnClearPathSouthBounded() {
		this.rover.executeCommand("fffffrffffffffff");
		this.rover.executeCommand("rf");
		this.rover.executeCommand("l");
		assertThat("(11,4,E)", is(equalToIgnoringCase( this.rover.executeCommand("f"))));
		assertThat("(10,4,E)", is(equalToIgnoringCase( this.rover.executeCommand("b"))));	
	}

}
