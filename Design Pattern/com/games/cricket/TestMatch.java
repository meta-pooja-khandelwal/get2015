/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name TestMatch
 * @description this is a class which inherits the Cricket class 
 */
package com.games.cricket;

import com.games.Cricket;
import com.games.Game;

public class TestMatch extends Cricket {
	private static TestMatch testMatch;

	/**
	 * @name getInstance()
	 * @description it will create and return the instance of TestMatch class
	 *              whenever required
	 * @param
	 * @return testMatch(object/instance of TestMatch class)
	 */
	public static Game getInstance() {
		if (testMatch == null) {
			testMatch = new TestMatch();
		}
		return testMatch;

	}

	// initialize testMatch match
	@Override
	public void initialize() {
		System.out.println("Test match Initialized! Start playing.");
	}

	// start testMatch match
	@Override
	public void startPlay() {
		System.out.println("Test match Started. Enjoy the game!");
	}

	// end testMatch match
	@Override
	public void endPlay() {
		System.out.println("Test match Finished!");
	}
}
