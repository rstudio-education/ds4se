package fi.oulu.eseil.tdd;

public class Commons {
	static String goToMiddleOfPlanetFromSouthBackwards() {
		String command = goToMiddleBottomBorderFromWest() + "rr" + generateCommand(50, "b");
		return command;
	}

	static String goToMiddleOfPlanetFromEastBackwards() {
		String command = goToMiddleRightBorderFromSouth() + "r" + generateCommand(50, "b");
		return command; 
	}

	static String goToMiddleOfPlanetFromNorthBackwards() {
		String command  = goToMiddleTopBorderFromWest() + generateCommand(50, "b");
		return command;
	}

	static String goToMiddleOfPlanetFromWestBackwards() {
		String command = goToMiddleLeftBorderCommandFromSouth() + "l" + generateCommand(50, "b");
		return command;
	}

	static String goToMiddleOfPlanetFromSouth() {
		String command = goToMiddleBottomBorderFromWest() + generateCommand(50, "f");
		return command;
	}

	static String goToMiddleOfPlanetFromEast() {
		String command = goToMiddleRightBorderFromSouth() + "l" + generateCommand(50, "f");
		return command;
	}

	static String goToMiddleOfPlanetFromNorth() {
		String command = goToMiddleTopBorderFromWest() + "ll" + generateCommand(50, "f");
		return command;
	}

	static String goToBottomRightCornerFromNorthBackwards() {
		String command = goToTopRightCorner() + generateCommand(99, "b");
		return command;

	}

	static String goToBottomRightCornerFromWestBackwards() {
		String command = "l" + generateCommand(99, "b");
		return command;
	}

	static String goToTopRightCornerFromSouthBackwards() {
		String command = goToBottomRightCornerFromWest() + "r" + generateCommand(99, "b");
		return command;
	}

	static String goToTopRightCornerFromWestBackwards() {
		String command = goToTopLeftCornerFromSouth() + "l" + generateCommand(99, "b");
		return command;
	}

	static String goToTopLeftCornerFromEastBackwards() {
		String command = goToTopRightCornerFromSouth() +"r" + generateCommand(99, "b");
		return command;
	}

	static String goToTopLeftCornerFromSouthBackwards() {
		String command = "ll" + generateCommand(99, "b");
		return command;
	}

	static String goToBottomRightCornerFromNorth() {
		String command = "fr" + generateCommand(99, "f") + "rf";
		return command;
	}

	static String goToBottomRightCornerFromWest() {
		return goToBottomRightCorner() + "r";
	}

	static String goToTopRightCornerFromSouth() {
		String command = goToBottomRightCorner() + generateCommand(99, "f");
		return command;
	}

	static String goToTopRightCornerFromWest() {
		String command = goToTopRightCorner() +"r";
		return command;
	}

	static String goToTopLeftCornerFromEast() {
		String command = "rfl" + generateCommand(99, "f") + "lf"; 
		return command;
	}

	static String goToMiddleBottomBorderFromNorthBackwards() {
		String command = "fr" + generateCommand(50, "f") + "lb";
		return command;
	}

	static String goToMiddleBottomBorderFromEastBackwards() {
		String command = "fr" + generateCommand(99, "f") + "rfl" + generateCommand(50, "b"); 
		return command;
	}

	static String goToMiddleBottomBorderFromWestBackwards() {
		String command = "l" + generateCommand(50, "b");
		return command;
	}



	static String goToMiddleTopBorderFromSouthBackwards() {
		String command = goToMiddleBottomBorder() + "rr" + generateCommand(99, "b");
		return command;
	}

	static String goToMiddleTopBorderFromEastBackwards() {
		String command = goToTopRightCorner() + "r" + generateCommand(50, "b");
		return command;
	}

	static String goToMiddleTopBorderFromWestBackwards() {
		String command = goToTopLeftCornerFromSouth() + "l" + generateCommand(50, "b");
		return command;
	}

	static String goToMiddleTopBorderFromWest(){
		String command = generateCommand(99, "f") + "r" + generateCommand(50, "f") + "l";
		return command;
	}
	static String goToMiddleRightBorderFromSouthBackwards() {
		String command = goToBottomRightCorner() + "rr" + generateCommand(50, "b");
		return command;
	}

	static String goToMiddleRightBorderFromNorthBackwards() {
		String command = goToTopRightCorner() + generateCommand(50, "b");
		return command;
	}

	static String goToMiddleRightBorderFromWestBackwards() {
		String command = goToMiddleLeftBorderCommandFromSouth() + "l" + generateCommand(99, "b");
		return command;
	}

	static String goToMiddleLeftBorderCommandFromEastBackwards() {
		String command = "rfl" + generateCommand(50, "f") + "rb"; 
		return command;
	}

	static String goToMiddleBottomBorderFromNorth() {
		String command = "fr" + generateCommand(50, "f") + "rfll";
		return command;
	}

	static String goToMiddleBottomBorderFromEast() {
		String command = "fr" + generateCommand(99, "f") + "lbl" + generateCommand(50, "f") + "r";
		return command;
	}

	static String goToMiddleBottomBorderFromWest() {
		String command = "r" + generateCommand(50, "f") + "l";
		return command;
	}

	static String goToMiddleTopBorderFromSouth() {
		String command = goToMiddleBottomBorder() + generateCommand(99, "f");
		return command;
	}

	static String goToMiddleTopBorderFromEast() {
		String command  = goToBottomRightCorner() + generateCommand(99, "f") + "l" + generateCommand(50, "f") + "r";
		return command;
	}

	static String generateObstacleInPosition(int x, int y){
		String obstacle = "(" + x + "," + y + ")";
		return obstacle;
	}

	static String goToMiddleBottomBorder(){
		String command = "r" + generateCommand(50, "f") + "l";
		return command;
	}

	static String goToMiddleRightBorderFromWest(){
		String command = generateCommand(50, "f") + "r" + generateCommand(99, "f") + "l";
		return command;
	}

	static String goToMiddleRightBorderFromNorth(){
		String command = goToTopRightCorner() + "rr" + generateCommand(50, "f") + "ll";
		return command;
	}

	static  String goToMiddleRightBorderFromSouth(){
		String command = goToBottomRightCorner() + generateCommand(50, "f");
		return command;
	}


	static String goToBottomRightCorner(){
		String command =  "r" + generateCommand(99, "f") + "l";
		return command;
	}

	static String goToTopLeftCornerFromSouth(){
		String command = generateCommand(99, "f");
		return command;
	}

	


	static String goToTopRightCorner(){
		String command = generateCommand(99, "f") + "r" + generateCommand(99, "f") + "l";
		return command;
	}
	static String goToMiddleOfPlanetFromWest() {
		String command = generateCommand(50, "f") + "r" + generateCommand(50, "f") ;
		return command;
	}


	 static String generateCommand(int times,String symbol) {
		String command  = "";
		for (int i = 0; i < times; i++) {
			command += symbol;
		}
		return command;
	}

	 static String goToMiddleLeftBorderCommandFromSouth(){
		String command = generateCommand(50, "f");
		return command;
	}

	 static String goToMiddleLeftBorderCommandFromSouthBackwards(){
		String command ="rr" + generateCommand(50, "b");
		return command;
	}

	 static String goToMiddleLeftBorderCommandNorth(){
		String command = "rfl" + generateCommand(99, "f") + "lfl" + generateCommand(50, "f") + "rr";
		return command;
	}

	 static String goToMiddleLeftBorderCommandNorthBackwards(){
		String command = "rfl" + generateCommand(99, "f") + "lfr" + generateCommand(50, "b");
		return command;
	}

	 static String goToMiddleLeftBorderCommandFromEast(){
		String command = "rfl" + generateCommand(50, "f") + "lfr";
		return command;
	}

}
