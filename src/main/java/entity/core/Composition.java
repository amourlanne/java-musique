package entity.core;

/**
 * Class Composition
 * Created by Alexis on 30 oct. 2018.
 * 
 * This class represent a Composition
 * 
 */
public abstract class Composition {
	
	private String titre;
	private Artiste artiste;

	/**
	 * @param titre
	 */
	public Composition(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * set @param titre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the artiste
	 */
	public Artiste getArtiste() {
		return artiste;
	}

	/**
	 * set @param artiste
	 */
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public abstract int getDuree();
	
	/* Override method
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.titre;
	}
	
	

}
