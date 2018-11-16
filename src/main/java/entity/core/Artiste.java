package entity.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Artiste
 * Created by Alexis on 30 oct. 2018.
 * 
 * This class represent ...
 * 
 */
public class Artiste {
	
	private String nom;

	private List<Composition> compositions;

	/**
	 * @param nom
	 */
	public Artiste(String nom) {
		super();
		this.nom = nom;
		this.compositions = new ArrayList<>();
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * set @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	public void ajouterComposition(Composition composition) {
		if(!this.compositions.contains(composition)) {
			this.compositions.add(composition);
			composition.setArtiste(this);
		}
	}
	
	/**
	 * @return the compositions
	 */
	public List<Composition> getCompositions() {
		return compositions;
	}
	
	/**
	 * @return the compositions
	 */
	public List<Album> getAlbums() {
		List<Album> albums = new ArrayList<>();
		
		for( Composition composition : compositions) {
			if (composition instanceof Album) {
				albums.add((Album)composition);
			}
		}
		return albums;
	}
	
	/* Override method
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.nom;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Artiste artiste = (Artiste) o;
		return Objects.equals(nom, artiste.nom);
	}

	@Override
	public int hashCode() {

		return Objects.hash(nom);
	}
}
