package com.vb.robot.mover.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vb.robot.mover.model.Board;
import com.vb.robot.mover.model.Game;
import com.vb.robot.mover.service.RobotService;
import com.vb.robot.mover.service.impl.RobotServiceImpl;

/**
 * @author vivek.bharti
 *Controller class for all the services for Moving Robot 
 */
@RestController
public class RobotController {
	
	private Game game = new Game(new Board(4,4), null);
	
	//@Autowired
	RobotService robotService = new RobotServiceImpl();
		 
	 /**
	  * Service to command robot
	 * @param command: VALID Commands: <PLACE 1,1,EAST>, MOVE, LEFT, RIGHT, REPORT
	 * @return
	 */
	@PutMapping("/command")
	    public Object commadRobot(@RequestBody final String command ) {
	        try {
             return robotService.evalCommand(command, game);
         } catch (Exception e) {
        	 return e.getMessage();
         }
	    }

}
