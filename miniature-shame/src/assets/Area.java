package assets;

import org.newdawn.slick.Graphics;

public class Area {
	private Tile[][] tiles;
	private int tileWidth, tileHeight;

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public void draw(Graphics g) {
		for (int i=0; i<tiles.length; i++) {
			for (int j=0; j<tiles[i].length; j++) {
				tiles[i][j].draw(g, i*tileWidth, j*tileHeight);
			}
		}
	}
	
	public Area(int width, int height, int tileWidth, int tileHeight) {
		tiles = new Tile[width][height];
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
}
