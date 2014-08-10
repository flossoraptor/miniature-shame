package assets;

import org.newdawn.slick.Graphics;

public class Tile extends DrawnObject{
	private Sprite sprite;
	private boolean walkable;
	
	public void draw(Graphics g, float x, float y) {
		sprite.draw(g, x, y);
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
}
