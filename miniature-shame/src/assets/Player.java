package assets;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Actor {
	
	private HashMap<String, Boolean> flags;
	
	public Player(Sprite sprite, float x, float y) {
		setSprite(sprite);
		setHitbox(new Rectangle(x, y, 32, 32));
		getHitbox().setX(x);
		getHitbox().setY(y);
		setSpeed(2f);
		flags = new HashMap<>();
		flags.put("walkingUp", false);
		flags.put("walkingDown", false);
		flags.put("walkingLeft", false);
		flags.put("walkingRight", false);
	}
	
	@Override
	public boolean handleUpdate(int delta, Area area) {
		// TODO: Consider making a list of all possible flags and iterating over that
		Boolean isWalking = false;
		// TODO: first check to see if player is currently under control or not
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
		
		//if (!isWalking) {
			//this.currentAnimation = animations.get("stand" + state);
		//}
		
		normalizeComponents();
		
		move();
		
		resolveCollisions(area);
		
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
