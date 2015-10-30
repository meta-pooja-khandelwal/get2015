/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name OneDayMatch
 * @description this is a class which inherits the Cricket class 
 */
package com.games.cricket;

import com.games.Cricket;
import com.games.Game;

public class OneDayMatch extends Cricket {
	private static OneDayMatch oneDayMatch;

	/**
	 * @name getInstance()
	 * @description it will create and return the instance of OneDayMatch class
	 *              whenever required
	 * @param
	 * @return oneDayMatch(object/instance of OneDayMatch class)
	 */
	public static Game getInstance() {
		if (oneDayMatch == null) {
			oneDayMatch = new OneDayMatch();
		}
		return oneDayMatch;

	}

	// initialize oneDayMatch match
	@Override
	public void initialize() {
		System.out.println("One day match Initialized! Start playing.");
	}

	// start oneDayMatch match

	@Override
	public void startPlay() {
		System.out.println("One Day match Started. Enjoy the game!");
	}

	// end oneDayMatch match

	@Override
	public void endPlay() {
		System.out.println("One Day match Finished!");
	}
}
