package assets;

import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Actor {
	
	private HashMap<String, Boolean> flags;
	
	public Player(Sprite sprite, float x, float y) {
		setSprite(sprite);
		setHitbox(new Rectangle(x, y, 32, 32));
		getHitbox().setX(x);
		getHitbox().setY(y);
		setWalkSpeed(2.2f);
		flags = new HashMap<>();
		flags.put("walkingUp", false);
		flags.put("walkingDown", false);
		flags.put("walkingLeft", false);
		flags.put("walkingRight", false);
		setCurrentState(new State(1, 0, null, true));
	}
	
	@Override
	public boolean handleUpdate(int delta, Area area) {
		// TODO: Consider making a list of all possible flags and iterating over that
		Boolean isWalking = false;

		if (getCurrentState().getRemainingDuration() <= 0) {
			setCurrentState(new State(1, 0, null, true));
		}
		
		if (getCurrentState().canMove()) {
			// set the speed we will move to the appropriate value: in this case, we are under control, so you will only
			// move as fast as you can walk
			setSpeed(getWalkSpeed());
			setxComp(0f);
			setyComp(0f);
			if (flags.get("walkingUp")) {
				this.setyComp(-1f);
				//this.currentAnimation = animations.get("walkUp");
				//state = "Up";
				isWalking = true;
			}
			if (flags.get("walkingDown")) {
				this.setyComp(1f);
				//this.currentAnimation = animations.get("walkDown");
				//state = "Down";
				isWalking = true;
			}
			if (flags.get("walkingLeft")) {
				this.setxComp(-1f);
				//this.currentAnimation = animations.get("walkLeft");
				//state = "Left";
				isWalking = true;
			}
			if (flags.get("walkingRight")) {
				this.setxComp(1f);
				//this.currentAnimation = animations.get("walkRight");
				//state = "Right";
				isWalking = true;
			}
		} else {
			setSpeed(getCurrentState().getSpeed());
			setxComp(getCurrentState().getMovementVector().getX());
			setyComp(getCurrentState().getMovementVector().getY());
			getCurrentState().decrementDuration();
		}
		
		//if (!isWalking) {
			//this.currentAnimation = animations.get("stand" + state);
		//}
		
		normalizeComponents();
		
		move();
		
		System.out.println("i'm gonna move now!! get down!! " + getHitbox().getX() + ", " + getHitbox().getY());
		
		resolveCollisions(area);
		
		System.out.println("did i move? " + getHitbox().getX() + ", " + getHitbox().getY());
		
		if (getCurrentState().canMove()) {
			List<Position> possibleActorCollisions = damageCollision(area.getActors());
			
			for (Position position : possibleActorCollisions) {
				if (position != null) {
					System.out.println("fly back!!");
					flyBack(position);
					break;
				}
			}
		}
		
		return true;	
	}
	
	public void keyPressed(int key, char code) {
		if (code == 'a') {
			flags.put("walkingLeft", true);
		} else if (code == 'd') {
			flags.put("walkingRight", true);
		} else if (code == 'w') {
			flags.put("walkingUp", true);
		} else if (code == 's') {
			flags.put("walkingDown", true);
		}
	}
	
	public void keyReleased(int key, char code) {
		if (code == 'a') {
			flags.put("walkingLeft", false);
		} else if (code == 'd') {
			flags.put("walkingRight", false);
		} else if (code == 'w') {
			flags.put("walkingUp", false);
		} else if (code == 's') {
			flags.put("walkingDown", false);
		}
	}
}
