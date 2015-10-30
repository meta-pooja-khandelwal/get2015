/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name GamesFactory
 * @description this is a class which provides the instance of either Cricket class or Football class based on input game type
 */
package com.games;

public class GamesFactory {
	/**
	 * @name getGame()
	 * @description it provides the instance of either Cricket class or Football
	 *              class based on input game type
	 * @param gameType
	 *            (either cricket or football)
	 * @return game(instance of either Cricket class or Football class)
	 */
	public static Game getGame(GamesType gameType) {
		Game game;
		if (gameType.equals(GamesType.Cricket)) {
			game = Cricket.getInstance();
		} else if (gameType.equals(GamesType.Football)) {
			game = Football.getInstance();
		} else {
			game = null;
		}
		return game;
	}

}
