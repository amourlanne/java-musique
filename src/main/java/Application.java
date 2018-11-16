
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.core.Album;
import entity.core.Artiste;
import entity.core.Discotheque;
import entity.core.Piste;
import entity.tools.RechercheAlbum;
import entity.tools.Utils;
import exception.ErreurFormatFichierException;

/**
 * Class Application
 * Created by Alexis on 30 oct. 2018.
 * 
 * This class represent the main application
 * 
 */
public class Application {

	public static void main(String[] args){

		try {
			Discotheque.charger();

			Scanner scanner = new Scanner(System.in);

			afficherAccueil(scanner);

		} catch (ErreurFormatFichierException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void afficherAccueil(Scanner scanner) {

		while(true) {

			System.out.println("Bienvenue sur l'application de gestion de musique.");
			System.out.println("Que voulez-vous faire ?\n");

			System.out.println("1. Lister les albums");
			System.out.println("2. Lister les artistes");
			System.out.println("3. Rechercher des albums");
			System.out.println("4. Quitter l'application");

			System.out.println("\nVotre choix ?");

			List<Integer> choices = new ArrayList<>();
			choices.add(1);
			choices.add(2);
			choices.add(3);
			choices.add(4);

			switch (Utils.choice(scanner,choices)) {
			case 1:
				afficherAlbums(scanner, Discotheque.getAlbums());
				break;
			case 2:
				afficherArtistes(scanner);
				break;
			case 3:
				afficherTypeDeRecherche(scanner);
				break;
			case 4:
				return;
			}
		}
	}

	private static void afficherTypeDeRecherche(Scanner scanner) {

		while(true) {

			System.out.println("Quel type de recherche souhaitez-vous ?\n");

			System.out.println("1. par nom d'artiste");
			System.out.println("2. par titre d'album");
			System.out.println("3. par année d'album");

			System.out.println("\nVotre sélection (0 pour retourner au menu precedent) ?");

			List<Integer> choices = new ArrayList<>();
			choices.add(0);
			choices.add(1);
			choices.add(2);
			choices.add(3);

			String patern;

			switch (Utils.choice(scanner,choices)) {
				case 0:
					return;
				case 1:
					System.out.println("Recherche par nom d'artiste.\n");
					System.out.println("Le nom à rechercher : ");

					patern = scanner.next();
					afficherAlbums(scanner, RechercheAlbum.findByArtist(Discotheque.getAlbums(), patern));
					break;
				case 2:
					System.out.println("Recherche par titre d'album.\n");
					System.out.println("Le titre à rechercher : ");

					patern = scanner.next();
					afficherAlbums(scanner, RechercheAlbum.findByTitle(Discotheque.getAlbums(), patern));
					break;
				case 3:
					System.out.println("Recherche par année d'album.\n");
					System.out.println("L'année à rechercher : ");

					patern = scanner.next();
					afficherAlbums(scanner, RechercheAlbum.findByYear(Discotheque.getAlbums(), patern));
					return;
			}
		}
	}

	public static void afficherArtistes(Scanner scanner) {

		while(true) {

			List<Artiste> artistes = Discotheque.getArtistes();

			System.out.println("Liste des artistes\n");

			List<Integer> choices = new ArrayList<>();
			choices.add(0);

			for(Artiste artiste : artistes) {

				int index = artistes.indexOf(artiste) + 1;
				choices.add(index);

				System.out.println( index + " " + artiste.toString());
			}

			System.out.println("\nVotre sélection (0 pour retourner au menu precedent) ?");

			int result = Utils.choice(scanner,choices);

			if(result == 0) {
				return;
			} else {
				afficherAlbums(artistes.get(result - 1), scanner);
			}
		}

	}

	public static void afficherAlbums(Scanner scanner , List<Album> albums) {

		while(true) {

			System.out.println("Liste des albums\n");

			List<Integer> choices = new ArrayList<>();
			choices.add(0);

			for(Album album : albums) {

				int index = albums.indexOf(album) + 1;
				choices.add(index);

				System.out.println( index + " " + album.toString());
			}

			System.out.println("\nVotre sélection (0 pour retourner au menu precedent) ?");

			int result = Utils.choice(scanner,choices);

			if(result == 0) {
				return;
			} else {
				afficherAlbum(albums.get(result - 1), scanner);
			}
		}

	}
	
	public static void afficherAlbums(Artiste artiste, Scanner scanner) {

		while(true) {

			List<Album> albums = artiste.getAlbums();

			System.out.println("Liste des albums de l'artiste " + artiste.toString() + "\n");

			List<Integer> choices = new ArrayList<>();
			choices.add(0);

			for(Album album : albums) {

				int index = albums.indexOf(album) + 1;
				choices.add(index);

				System.out.println( index + " " + album.toString());
			}

			System.out.println("\nVotre sélection (0 pour retourner au menu precedent) ?");

			int result = Utils.choice(scanner,choices);

			if(result == 0) {
				return;
			} else {
				afficherAlbum(albums.get(result -1), scanner);
			}
		}

	}
	
	public static void afficherAlbum(Album album, Scanner scanner) {

		Piste currentPiste = null;

		while(true) {

			List<Piste> pistes = album.getPistes();

			System.out.println("Titre : " + album.getTitre());
			System.out.println("Durée : " + Utils.showTime(album.getDuree()));
			System.out.println("Artiste : " + album.getArtiste() + "\n");

			List<Integer> choices = new ArrayList<>();
			choices.add(0);

			for(Piste piste : pistes) {

				int index = pistes.indexOf(piste) + 1;
				choices.add(index);

				System.out.println( index + " " + piste.toString() + " " + Utils.showTime(piste.getDuree()));
			}

			System.out.println("\n(0 pour retourner au menu precedent)");

			int result = Utils.choice(scanner,choices);

			if (currentPiste != null) currentPiste.stop();


			if(result == 0) {
				return;
			} else {
				currentPiste = pistes.get(result - 1);
				currentPiste.play();
			}
		}

	}
}
