/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name Game
 * @description this is an abstract class which one template method and 3 abstract methods whose implementation is done in its derived classes 
 */
package com.games;

public abstract class Game {
	abstract public void initialize();

	abstract public void startPlay();

	abstract public void endPlay();

	// template method
	public final void play() {

		// initialize the game
		initialize();

		// start game
		startPlay();
	}

	public final void endGame() {
		endPlay();
	}
}
