package com.vb.robot.mover.service;

import com.vb.robot.mover.exception.RobotException;
import com.vb.robot.mover.model.Game;

public interface RobotService {
    /**
     * Evals and executes a string command.
     *
     * @param inputString command string
     * @param yPosition 
     * @param xPosition 
     * @param direction 
     * @return string value of the executed command
     * @throws com.RobotException.trb.exception.ToyRobotException
     *
     */
    public String evalCommand(String commandInput, Game game) throws RobotException;
 
}
