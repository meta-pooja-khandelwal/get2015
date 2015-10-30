/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name GameMain
 * @description this is a class which will handle all the games 
 */
package com.main;

import java.util.Scanner;

import com.games.Cricket;
import com.games.Game;
import com.games.GamesFactory;
import com.games.GamesType;
import com.games.cricket.CricketFactory;
import com.games.cricket.CricketType;

public class GameMain {
	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		int choice = -1;
		char toBeContinued = 'y';

		do {
			while (toBeContinued == 'y') {
				System.out.println("Enter game which you want to play:-\n");
				System.out.println("press 1 to play Cricket");
				System.out.println("press 2 to play Football");
				System.out.println("press 0 to exit");
				Game game = null;

				while (!scanner.hasNextInt()) {
					System.out.println("Please Enter only Integer!");
					scanner.next();
				}
				choice = scanner.nextInt();
				while (choice < 0 || choice > 2) {
					System.out.println("Please enter valid integer!");
					while (!scanner.hasNextInt()) {
						System.out.println("Please Enter only Integer!");
						scanner.next();
					}
					choice = scanner.nextInt();
				}
				switch (choice) {
				case 1:
					game = GamesFactory.getGame(GamesType.Cricket);
					int cricketType = -1;

					System.out
							.println("Enter cricket type which you want to play:-\n");
					System.out.println("press 1 to play Test match");
					System.out.println("press 2 to play One day amtch");
					Cricket cricket = null;
					while (!scanner.hasNextInt()) {
						System.out.println("Please Enter only Integer!");
						scanner.next();
					}
					cricketType = scanner.nextInt();
					while (cricketType < 1 || cricketType > 2) {
						System.out.println("Please enter valid integer!");
						while (!scanner.hasNextInt()) {
							System.out.println("Please Enter only Integer!");
							scanner.next();
						}
						cricketType = scanner.nextInt();
					}
					game.play();
					switch (choice) {
					case 1:
						cricket = CricketFactory
								.getCricket(CricketType.testMatch);
						break;
					case 2:
						cricket = CricketFactory.getCricket(CricketType.oneDay);
						break;
					}
					System.out.println();
					cricket.playCricket();
					break;
				case 2:
					game = GamesFactory.getGame(GamesType.Football);
					game.play();
					break;

				case 0:
					toBeContinued = 'n';
					scanner.close();
					System.exit(0);
					System.out.println("*----Exit----*");
					break;
				}
				System.out.println();
				game.endGame();
				System.out.println();
				System.out
						.println("press 'y' to continue\npress any other key to exit");
				toBeContinued = scanner.next(".").charAt(0);
				if (toBeContinued != 'y') {
					System.out.println("*----Exit----*");
				}

			}
		} while (choice != 0);

	}
}
