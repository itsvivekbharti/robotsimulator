package com.vb.robot.mover.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import com.vb.robot.mover.exception.RobotException;
import com.vb.robot.mover.model.Command;
import com.vb.robot.mover.model.Direction;
import com.vb.robot.mover.model.Game;
import com.vb.robot.mover.model.Robot;
import com.vb.robot.mover.service.RobotService;

@Service
public class RobotServiceImpl implements RobotService{

@PostConstruct
	public void init() {
	    System.out.println("service from @service");
	}

    /**
     * Evaluates and executes a command provided.
     *
     * @param commandInput command string
     * @param game 
     * @return string value of the executed command
     * @throws com.RobotException.trb.exception.RobotException
     *
     */
    public String evalCommand(String commandInput, Game game) throws RobotException {
        Command command;
        int xPos = 0,yPos = 0;
        Direction direction = null;
        try {
        	String[] commandArgs = commandInput.split(" ");
			if (commandArgs[0].equalsIgnoreCase("PLACE") && commandArgs.length < 2) {
        		throw new RobotException("Invalid PLACE command: Robot placement arguments missing.");
        	}else if(commandArgs[0].equalsIgnoreCase("PLACE")){
        	try{
        		String[] commandParams = commandArgs[1].split(",");
        		xPos = Integer.parseInt(commandParams[0]);
        		yPos = Integer.parseInt(commandParams[1]);
        		direction= Direction.valueOf(commandParams[2]);
        	}catch(IllegalArgumentException ex){
        		throw new RobotException("Invalid PLACE command: Robot placement parameters invalid.");
        	}
        	}else{
        		if(game.getRobot()==null){
        			throw new RobotException("Invalid command: Kindly place your Robot first");
        		}
        	}
            command = Command.valueOf(commandArgs[0]);
        executeCommand(game, command, xPos, yPos, direction);
        } catch (RobotException e) {
            throw e;
        } catch (Exception e) {
            throw new RobotException("Invalid command: Please try again");
        }
        return report(game);
    }

	/**
	 * Execute command for Robot
	 * @param game
	 * @param command
	 * @param xPos
	 * @param yPos
	 * @param direction
	 * @throws RobotException
	 */
    private void executeCommand(Game game, Command command, int xPos, int yPos,
			Direction direction) throws RobotException {
		try{
		switch (command) {
        	case PLACE:
        		if(!isValidPosition(game, xPos, yPos)){
        			throw new RobotException("Invalid PLACE command: Robot placement arguments for x/y should be between 0-4.");
        		}
        		game.setRobot(new Robot(xPos,yPos,direction));
        		break;
            case MOVE:
                 game = this.moveRobot(game);
                break;
            case LEFT:
                this.rotateLeft(game);
                break;
            case RIGHT:
                this.rotateRight(game);
                break;
            case REPORT:
               // this.report(game);
                break;
            default:
                throw new RobotException("Invalid command: Please try again");
        }
		} catch (RobotException e) {
            throw e;
        } catch (Exception e) {
            throw new RobotException("Invalid command: Please try again");
        }
	}
	
	   /**
     * Validate the xPosition and yPosition on the board
     * @param game
     * @param xPos
     * @param yPos
     * @return
     */
    private boolean isValidPosition(Game game, int xPos, int yPos) {
        return !(
        		xPos > game.getBoard().getColumns() || xPos < 0 ||
        				yPos > game.getBoard().getRows() || yPos < 0
        );
    }
    
    /**
     * Moves Robot 1 unit on the Board
     * @param game
     * @return
     * @throws RobotException
     */
    private Game moveRobot(Game game) throws RobotException {
    	int changedXpos = game.getRobot().getX();
    	int changedYpos = game.getRobot().getY();
        switch (game.getRobot().getDirection()) {
            case NORTH:
            	changedYpos+=1;
                break;
            case EAST:
            	changedXpos+=1;
                break;
            case SOUTH:
            	changedYpos+=-1;
                break;
            case WEST:
            	changedXpos+=-1;
                break;
        }
        if(!isValidPosition(game,changedXpos, changedYpos)){
        	return game;
        }
        game.getRobot().setX(changedXpos);
    	game.getRobot().setY(changedYpos);
    	return game;
    }

	
    /**
     * Rotates the robot to LEFT
     *
     * @return true if rotated successfully
     */
    private boolean rotateLeft(Game game) {
        game.getRobot().setDirection(game.getRobot().getDirection().leftDirection());
        return true;
    }

    /**
     * Rotates the robot 90 degrees RIGHT
     *
     * @return true if rotated successfully
     */
    private boolean rotateRight(Game game) {
        game.getRobot().setDirection(game.getRobot().getDirection().rightDirection());
        return true;
    }

    /**
     * Returns the X,Y and Direction of the robot
     */
    private String report(Game game) {
        return game.getRobot().getX() + "," + game.getRobot().getY() + "," + game.getRobot().getDirection().toString();
    }
 }
