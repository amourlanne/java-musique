package entity.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class Album
 * Created by Alexis on 30 oct. 2018.
 * 
 * This class represent ...
 * 
 */
public class Album extends Composition {
	
	private int annee;
	
	private List<Piste> pistes;
	
	/**
	 * @param titre
	 * @param annee
	 */
	public Album(String titre, int annee) {
		super(titre);
		this.annee = annee;
		this.pistes = new ArrayList<>();
	}
	
	public void ajouterPiste( Piste piste ) {
		if(!this.pistes.contains(piste)) {
			this.pistes.add(piste);
			piste.setAlbum(this);
		}
	}

	/**
	 * @return the annee
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * set @param annee
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	public int getDuree() {
		int duree = 0;
		for (Piste piste : this.pistes) {
			duree += piste.getDuree();
		}
		return duree;
	}

	/**
	 * @return the pistes
	 */
	public List<Piste> getPistes() {
		return pistes;
	}

	public int getNumberOfPiste() {
		return this.pistes.size();
	}

	/* Override method
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " " + this.annee;
	}
	
}
