package com.silkmanuel.spring.learn_spring_framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {
		// SpringApplication.run(LearnSpringFrameworkApplication.class, args);
		MarioGamer mario = new MarioGamer();
		GameRunner runner = new GameRunner(mario);
		runner.run();
		System.out.println("LEARN RUNNING");
	}

}
