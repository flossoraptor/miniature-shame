package engine;

import assets.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SpriteLoader {
	public Map<String, Sprite> spriteMap;
	public SpriteLoader() {
		spriteMap = new HashMap<String, Sprite>();
		
		try {
			Image tiles = new Image("/resources/tiles.png");
			SpriteSheet tileSheet = new SpriteSheet(tiles, 16, 16);
			spriteMap.put("ground", new Sprite(tileSheet.getSprite(0, 0)));
			spriteMap.put("wall", new Sprite(tileSheet.getSprite(1, 0)));
		} catch (SlickException e) {
			System.out.println("FAILED TO LOAD RESOURCE");
			e.printStackTrace();
		}
	}
}
