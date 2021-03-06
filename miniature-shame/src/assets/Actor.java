package assets;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Actor {
	private float xComp, yComp, speed, walkSpeed;
	private Sprite sprite;
	private Rectangle hitbox;
	private State currentState;
	
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
	
	public State getCurrentState() {
		return currentState;
	}
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
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
	
	public List<Position> damageCollision(List<Actor> actors) {
		List<Position> positions = new ArrayList<>();
		
		for (Actor actor : actors) {
			positions.add(damageCollision(actor));
		}
		
		return positions;
	}
	
	public void flyBack(Position position) {
		this.currentState = new State(10, 150f, position, false);
	}
	
	public Position damageCollision(Actor actor) {
		Rectangle actorHitbox = actor.getHitbox();
		if (actorHitbox.intersects(hitbox)) {
			return new Position((int) (hitbox.getCenterX() - actorHitbox.getCenterX()), (int) (hitbox.getCenterY() - actorHitbox.getCenterY()));
		} else {
			return null;
		}
	}
	
	public boolean resolveCollisions(Area area) {
		boolean colliding = false;
		List<Rectangle> hitboxes = area.getHitboxes(area.getTile((int) hitbox.getX(), (int) hitbox.getY()).getPlusPerimeter());
		for (Rectangle tileHitbox : hitboxes) {
			// TODO: the method "intersects" probably uses the same collision detection calculations that are being used
			// later to determine the magnitude of the intersection. It's probably safe and more efficient to
			// attempt to resolve the collision without checking .intersects(hitbox) because, if there is no intersection,
			// it would be resolved by a magnitude of 0, which does nothing.
			if (tileHitbox.intersects(hitbox)) {
				resolveCollision(tileHitbox, area);
				colliding = true;
			}
		}
		/*
		for (Actor actor : area.getActors()) {
			if (actor.getHitbox().intersects(hitbox)) {
				resolveCollision(actor.getHitbox());
			}
		}
		*/
		return colliding;
	}
	
	public void resolveCollision(Rectangle actorHitbox) {
		float resolveX = resolveCollisionX(actorHitbox);
		float resolveY = resolveCollisionY(actorHitbox);
		if (Math.abs(resolveX) < Math.abs(resolveY)) {
			hitbox.setX(hitbox.getX() + resolveX);
		} else {
			hitbox.setY(hitbox.getY() + resolveY);
		}
	}
	
	public void resolveCollision(Rectangle tileHitbox, Area area) {
		float resolveX = resolveCollisionX(tileHitbox);
		float resolveY = resolveCollisionY(tileHitbox);
		if (Math.abs(resolveX) < Math.abs(resolveY)) {
			if (!isInternalEdgeCollisionX(tileHitbox, area, resolveX)) {
				hitbox.setX(hitbox.getX() + resolveX);
			}
		} else {
			if (!isInternalEdgeCollisionY(tileHitbox, area, resolveY)) {
				hitbox.setY(hitbox.getY() + resolveY);
			}
		}
	}
	
	private boolean isInternalEdgeCollisionX(Rectangle tileHitbox, Area area, float resolveX) {
		int tileX = (int) (tileHitbox.getMinX() / area.getTileWidth() + (resolveX / Math.abs(resolveX)));
		int tileY = (int) tileHitbox.getMinY() / area.getTileHeight();
		// TODO: you can't check to see if the edge we are resolving our collision with is walkable
		// if its coordinates are invalid. Will this cause the player to still get caught on edges
		// if the edge is an "internal edge" that is shared with the border of the map?
		return area.withinBounds(tileX, tileY) && !area.getTiles()[tileX][tileY].isWalkable();
	}
	
	private boolean isInternalEdgeCollisionY(Rectangle tileHitbox, Area area, float resolveY) {
		int tileX = (int) tileHitbox.getMinX() / area.getTileWidth();
		int tileY = (int) (tileHitbox.getMinY() / area.getTileHeight() + + (resolveY / Math.abs(resolveY)));
		return area.withinBounds(tileX, tileY) && !area.getTiles()[tileX][tileY].isWalkable();
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
	
	
	
	public Actor createNew(float x, float y) {
		Actor newActor = new Actor();
		newActor.walkSpeed = this.walkSpeed;
		newActor.sprite = this.sprite;
		newActor.setHitbox(new Rectangle(x, y, this.getHitbox().getWidth(), this.getHitbox().getHeight()));
		return newActor;
	}
	
	public Actor() {
	}
	
	public Actor(Sprite sprite, Rectangle hitbox) {
		this.sprite = sprite;
		this.hitbox = hitbox;
	}
}
