package com.vb.robot.mover.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vb.robot.mover.service.RobotService;
import com.vb.robot.mover.service.impl.RobotServiceImpl;

/**
 * @author vivek.bharti
 * Class to init the services
 *
 */
@Configuration
public class AppConfig {

@Bean
public RobotService userService() {
    System.out.println("service from bean");
    return new RobotServiceImpl();
}
}
