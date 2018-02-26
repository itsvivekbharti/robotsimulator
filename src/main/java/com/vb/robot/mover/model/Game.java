package com.vb.robot.mover.model;


/**
 * vivek.bharti
 *
 */
public class Game {

	Board board;
    Robot robot;

    public Game(Board squareBoard, Robot robot) {
        this.board = squareBoard;
        this.robot = robot;
    }
    
    public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}
}
