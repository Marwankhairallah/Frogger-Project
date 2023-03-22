package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;

import frog.Frog;
import environment.Environment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import infinite.InfiniteFrog;
import infinite.InfiniteEnv;
import infinite.IInfiniteFrog;
import infinite.IInfiniteEnv;

public class Main {

	private static int width, height, minSpeedInTimerLoops;
	private static double defaultDensity;
	private static IFroggerGraphics graphic;

	public static Game start(int gameMode) {
		//Création de l'interface graphique
		graphic = new FroggerGraphic(width, height);
		//Création de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		switch (gameMode) {
			case 0:
				//Création et liason de la grenouille
				IFrog frog = new Frog(game);
				game.setFrog(frog);
				graphic.setFrog(frog);
				//Création et liaison de l'environnement
				//IEnvironment env = new Environment(game, frog);
				Environment env = new Environment(game);
				game.setEnvironment(env);
				break;
			case 1:
				//Création et liason de la grenouille
				IInfiniteFrog InfiniteFrog = new InfiniteFrog(game);
				game.setFrog(InfiniteFrog);
				graphic.setFrog(InfiniteFrog);
				//Création et liaison de l'environnement
				//IEnvironment env = new Environment(game, frog);
				IInfiniteEnv InfiniteEnv = new InfiniteEnv(game);
				game.setEnvironment(InfiniteEnv);
				break;
			case 2:
				// Création et liaison de la grenouille des 2 joueurs
				IFrog frog1 = new Frog(game);
				IFrog frog2 = new Frog(game);
				game.setFrog(frog1);
				game.setFrog(frog2);
				graphic.setFrog(frog1);
				graphic.setFrog(frog2);
				//Création et liaison de l'environnement
				//IEnvironment env = new Environment(game, frog);
				IEnvironment twoPEnv = new InfiniteEnv(game);
				game.setEnvironment(twoPEnv);
				break;
			case 3:
				// Création et liaison de la grenouille des 2 joueurs
				IInfiniteFrog InfiniteFrog1 = new InfiniteFrog(game);
				IInfiniteFrog InfiniteFrog2 = new InfiniteFrog(game);
				game.setFrog(InfiniteFrog1);
				game.setFrog(InfiniteFrog2);
				graphic.setFrog(InfiniteFrog1);
				graphic.setFrog(InfiniteFrog2);
				//Création et liaison de l'environnement
				//IEnvironment env = new Environment(game, frog);
				IInfiniteEnv twoPEnvInf = new InfiniteEnv(game);
				game.setEnvironment(twoPEnvInf);
				break;
			default:
				break;
		}
		return game;
	}

	public static void main(String[] args) {

		//Caractéristiques du jeu
		width = 20;
		height = 32;
		minSpeedInTimerLoops = 3;
		defaultDensity = 0.05;
		int tempo = 100;


		int gameMode;
		System.out.println(
				"pour jouer la version:    taper:\n" +
						"standard                   '0'\n" +
						"infinie                    '1'\n" +
						"standard 2 joueurs         '2'\n" +
						"infinie 2 joueurs          '3'\n");
		Scanner scanner = new Scanner (System.in);
		try {
			gameMode = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println(e);
			return;
		}

		Game game = start(gameMode);

		//Boucle principale : l'environnement s'actualise tous les tempo milisecondes
		Timer timer = new Timer(tempo, e -> {
			game.update();
			graphic.repaint();
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
