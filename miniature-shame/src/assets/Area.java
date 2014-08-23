package assets;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

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
	
	public Position getTile(int x, int y) {
		int tileX = x / tileWidth;
		int tileY = y / tileHeight;
		return new Position(tileX, tileY);
	}
	
	public boolean isColliding(Rectangle actorHitbox) {
		Position pos = getTile((int) actorHitbox.getX(), (int) actorHitbox.getY());
		return !tiles[pos.getX()][pos.getY()].isWalkable();
	}
	
	public boolean collision(int x, int y) {
		Position pos = getTile(x, y);
		return false;
	}
	
	public Area(int width, int height, int tileWidth, int tileHeight) {
		tiles = new Tile[width][height];
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	
	/**
	 *  Given a list of tiles that you may be colliding with, generate a list of Rectangle hitboxes
	 *  to resolve collisions with for each tile that you cannot pass through.
	 * 
	 * @param tilePositions
	 * @return
	 */
	public List<Rectangle> getHitboxes(List<Position> tilePositions) {
		List<Rectangle> hitboxes = new ArrayList<Rectangle>();
		for (Position pos : tilePositions) {
			if (withinBounds(pos) && !tiles[pos.getX()][pos.getY()].isWalkable()) {
				// TODO: are the provided x and y in this constructor the top left corner of the rectangle or its center?
				hitboxes.add(new Rectangle(tileWidth * pos.getX(), tileHeight * pos.getY(), tileWidth, tileHeight));
			}
		}
		return hitboxes;
	}
	
	private boolean withinBounds(Position pos) {
		return pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() < tiles.length && pos.getY() < tiles[0].length;
	}
}
