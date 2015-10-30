/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name CricketFactory
 * @description this is a class which provides the instance of either TestMatch class or OneDayMatch class based on input cricket type
 */
package com.games.cricket;

import com.games.Cricket;

public class CricketFactory {
	/**
	 * @name getCricket()
	 * @description it provides the instance of either TestMatch class or
	 *              OneDayMatch class based on input cricket type
	 * @param cricketType
	 *            (either TestMatch or OneDayMatch)
	 * @return getCricket(instance of either TestMatch class or OneDayMatch
	 *         class)
	 */
	public static Cricket getCricket(CricketType cricketType) {
		Cricket cricket;
		if (cricketType.equals(CricketType.oneDay)) {
			cricket = new OneDayMatch();
		} else if (cricketType.equals(CricketType.testMatch)) {
			cricket = new TestMatch();
		} else {
			cricket = null;
		}
		return cricket;
	}
}
