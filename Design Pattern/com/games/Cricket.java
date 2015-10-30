/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name Cricket
 * @description this is a singleton class which inherits the Game class and implements its abstract methods and have one template method playCricket()
 */
package com.games;

public class Cricket extends Game {
	private static Cricket cricket;

	/**
	 * @name Cricket()
	 * @description it is a private constructor of Cricket class so that its
	 *              instance can't be created by outSide world
	 * @param
	 */
	protected Cricket() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if instance is null and return the instance
	 *              of Cricket class whenever required
	 * @param
	 * @return cricket(object/instance of Cricket class)
	 */
	public static Game getInstance() {
		if (cricket == null) {
			cricket = new Cricket();
		}
		return cricket;

	}

	// initialize cricket match
	@Override
	public void initialize() {
		System.out.println("Cricket Game Initialized!");
	}

	// start cricket match
	@Override
	public void startPlay() {
		System.out.println("Cricket Game Started. Enjoy the game!");
	}

	// template method
	public final void playCricket() {

		// initialize the game
		initialize();

		// start game
		startPlay();

		// end game
		endPlay();
	}

	// end cricket match
	@Override
	public void endPlay() {
		System.out.println("Cricket Game Finished!");
	}
}
