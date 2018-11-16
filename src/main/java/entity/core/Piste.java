package entity.core;


import entity.tools.Player;

/**
 * Class Piste
 * Created by Alexis on 30 oct. 2018.
 * 
 * This class represent ...
 * 
 */
public class Piste extends Composition {
	
	private int duree;
	
	private Album album;

	private Player player;

	private String path;

	/**
	 * @param titre
	 * @param duree
	 * @param path
	 */
	public Piste(String titre, int duree, String path ) {
		super(titre);
		this.duree = duree;
		this.path = path;
	}

	/**
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * set @param duree
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * set @param album
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void play() {
		// lunch Thread
		this.player = new Player(this.path);
	    this.player.start();
    }

    public void stop() {
        this.player.stop();
        this.player.interrupt();
    }
}
