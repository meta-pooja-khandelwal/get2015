/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name Football
 * @description this is a singleton class which inherits the Game class and implements its abstract methods 
 */
package com.games;

public class Football extends Game {
	private static Football football;

	/**
	 * @name Football()
	 * @description it is a private constructor of Football class so that its
	 *              instance can't be created by outSide world
	 * @param
	 */
	private Football() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if instance is null and return the instance
	 *              of Football class whenever required
	 * @param
	 * @return football(object/instance of Football class)
	 */
	public static Game getInstance() {
		if (football == null) {
			football = new Football();
		}
		return football;
	}

	// initialize football match
	@Override
	public void initialize() {
		System.out.println("Football Game Initialized!");
	}

	// start football match
	@Override
	public void startPlay() {
		System.out.println("Football Game Started. Enjoy the game!");
	}

	// end football match
	@Override
	public void endPlay() {
		System.out.println("Football Game Finished!");
	}
}
