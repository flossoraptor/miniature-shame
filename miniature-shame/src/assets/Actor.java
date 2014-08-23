package assets;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Actor {
	private float xComp, yComp, speed, walkSpeed;
	private Sprite sprite;
	private Rectangle hitbox;
	
	public float getxComp() {
		return xComp;
	}
	public void setxComp(float xComp) {
		this.xComp = xComp;
	}
	public float getyComp() {
		return yComp;
	}
	public void setyComp(float yComp) {
		this.yComp = yComp;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getWalkSpeed() {
		return walkSpeed;
	}
	public void setWalkSpeed(float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	
	public void draw(Graphics g) {
		sprite.draw(g, hitbox.getMinX(), hitbox.getMinY());
	}
	
	public boolean handleUpdate(int delta, Area area) {
		normalizeComponents();
		move();
		return true;	
	}
	
	protected void normalizeComponents() {
		float hypotenuse = (getxComp() * getxComp()) + (getyComp() * getyComp());
		if (hypotenuse == 1f) {
			setxComp( (getxComp() * getSpeed() / hypotenuse) );
			setyComp( (getyComp() * getSpeed() / hypotenuse) );
		} else if (hypotenuse > 0.1 || hypotenuse < -0.1) {
			setxComp( (getxComp() * getSpeed() * 1.2f / hypotenuse) );
			setyComp( (getyComp() * getSpeed() * 1.2f / hypotenuse) );
		} else {
			setxComp(0);
			setyComp(0);
		}
	}
	
	protected void move() {
		hitbox.setX(hitbox.getX() + getxComp());
		hitbox.setY(hitbox.getY() + getyComp());
	}
	
	public boolean resolveCollisions(Area area) {
		boolean colliding = false;
		List<Rectangle> hitboxes = area.getHitboxes(area.getTile((int) hitbox.getX(), (int) hitbox.getY()).getPlusPerimeter());
		for (Rectangle tileHitbox : hitboxes) {
			if (tileHitbox.intersects(hitbox)) {
				resolveCollision(tileHitbox);
				colliding = true;
			}
		}
		return colliding;
	}
	
	public void resolveCollision(Rectangle tileHitbox) {
		float resolveX = resolveCollisionX(tileHitbox);
		float resolveY = resolveCollisionY(tileHitbox);
		if (Math.abs(resolveX) < Math.abs(resolveY)) {
			hitbox.setX(hitbox.getX() + resolveX);
		} else {
			hitbox.setY(hitbox.getY() + resolveY);
		}
	}
	
	public float resolveCollisionX(Rectangle tileHitbox) {
		float distanceLeft = tileHitbox.getMinX() - hitbox.getMaxX();
		float distanceRight = tileHitbox.getMaxX() - hitbox.getMinX();
		if (Math.abs(distanceLeft) < Math.abs(distanceRight)) {
			return distanceLeft;
		} else {
			return distanceRight;
		}
	}
	
	public float resolveCollisionY(Rectangle tileHitbox) {
		float distanceUp = tileHitbox.getMinY() - hitbox.getMaxY();
		float distanceDown = tileHitbox.getMaxY() - hitbox.getMinY();
		if (Math.abs(distanceDown) < Math.abs(distanceUp)) {
			return distanceDown;
		} else {
			return distanceUp;
		}
	}
	
	public boolean isColliding(Area area) {
		return area.isColliding(hitbox);
	}
}
