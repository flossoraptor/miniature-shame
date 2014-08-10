package assets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Sprite {
	private boolean animated;
	private Animation anim;
	private Image image;

	public boolean isAnimated() {
		return animated;
	}

	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
	
	public void draw(Graphics g, float x, float y) {
		if (animated) {
			g.drawAnimation(anim, x, y);
		} else {
			g.drawImage(image, x, y);
		}
	}
	
	public Sprite(Animation anim) {
		this.animated = true;
		this.anim = anim;
	}
	
	public Sprite(Image image) {
		this.animated = false;
		this.image = image;
	}
}
