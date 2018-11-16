package entity.tools;

import java.util.List;
import java.util.Scanner;

/**
 * Class Utils
 * Created by Alexis on 30 oct. 2018.
 * 
 * This class represent a Utils
 * 
 */
public final class Utils {

	public static int random(int max) {
		int min = 1;
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	public static int choice(Scanner scanner, List<Integer> choices) {

		Integer nombre = null;
		
		do {
			if (scanner.hasNextInt()) {
				Integer value = scanner.nextInt();
				if(choices.contains(value)) {
					nombre = value;
					continue;
				}
			} else {
				scanner.next();
			}
			System.err.println("Choix non valide");
		} while (nombre == null);

		return nombre;
	}
	
	public static String showTime(int secondes) {
		
		int hours = secondes / 3600;
		int minutes = (secondes % 3600) / 60;
		int seconds = secondes % 60;

		return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);

	}

}
