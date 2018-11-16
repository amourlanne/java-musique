package entity.tools;

import entity.core.Album;
import entity.core.Artiste;

import java.util.ArrayList;
import java.util.List;

/**
 * Class RechercheArtiste
 * Created by Alexis on 15/11/2018
 */
public class RechercheAlbum {

    public static List<Album> findByTitle(List<Album> list , String patern) {
        List<Album> result = new ArrayList<>(list);
        result.removeIf(album -> !album.getTitre().toLowerCase().contains(patern.toLowerCase()));
        return result;
    }

    public static List<Album> findByYear(List<Album> list , String patern) {
        List<Album> result = new ArrayList<>(list);
        result.removeIf(album -> !(album.getAnnee() == (Integer.parseInt(patern.toLowerCase()))));
        return result;
    }

    public static List<Album> findByArtist(List<Album> list , String patern) {
        List<Album> result = new ArrayList<>(list);
        result.removeIf(album -> !album.getArtiste().getNom().toLowerCase().contains(patern.toLowerCase()));
        return result;
    }
}
