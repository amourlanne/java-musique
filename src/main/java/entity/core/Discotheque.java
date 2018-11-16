package entity.core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exception.ErreurFormatFichierException;

/**
 * Class Discotheque
 * Created by Alexis on 30 oct. 2018.
 * <p>
 * This class represent the database
 */
public class Discotheque {

    private static List<Album> albums;

    private static List<Artiste> artistes;

    public static List<Album> getAlbums() {
        return Discotheque.albums;
    }

    public static List<Artiste> getArtistes() {
        return Discotheque.artistes;
    }

    public static void charger() throws ErreurFormatFichierException {


        Discotheque.albums = new ArrayList<>();
        Discotheque.artistes = new ArrayList<>();

        String fichier = "src/main/resources/assets/data.csv";
        String ligne;
        Artiste artiste = null;
        Album album = null;

        int numeroDeLigne = 0;

        try {
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(fichier), "UTF-8");
            LineNumberReader reader = new LineNumberReader(inputStreamReader);

            while ((ligne = reader.readLine()) != null) {

                numeroDeLigne++;

                String resultat[] = ligne.split(";");

                String name = resultat[0];

                Optional<Artiste> optionalArtiste = Discotheque.getArtistes().stream().filter(a -> a.getNom().equals(name)).findFirst();

                artiste = optionalArtiste.orElseGet(() -> {
                    Artiste a = new Artiste(name);
                    Discotheque.artistes.add(a);
                    return a;
                });

                String titre = resultat[1];
                int annee = Integer.parseInt(resultat[2]);

                Optional<Album> optionalAlbum = artiste.getAlbums().stream().filter(a -> a.getTitre().equals(titre) && (a.getAnnee() == annee)).findFirst();

                Artiste finalArtiste = artiste;

                album = optionalAlbum.orElseGet(() -> {
                    Album a = new Album(titre, annee);
                    finalArtiste.ajouterComposition(a);
                    Discotheque.albums.add(a);
                    return a;
                });

                String titrePiste = resultat[3];
                int duree = Integer.parseInt(resultat[4]);
                String path = resultat[5];

                Piste piste = new Piste(titrePiste, duree, path);

                album.ajouterPiste(piste);
                finalArtiste.ajouterComposition(piste);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            throw new ErreurFormatFichierException(numeroDeLigne);
        }
    }

}
