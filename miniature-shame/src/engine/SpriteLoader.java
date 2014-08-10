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
			Image tiles = new Image("/resources/img/tiles.png");
			SpriteSheet tileSheet = new SpriteSheet(tiles, 16, 16);
			spriteMap.put("greentl", new Sprite(tileSheet.getSprite(0, 0)));
			spriteMap.put("greentm", new Sprite(tileSheet.getSprite(1, 0)));
			spriteMap.put("greentr", new Sprite(tileSheet.getSprite(2, 0)));
			spriteMap.put("greenml", new Sprite(tileSheet.getSprite(3, 0)));
			spriteMap.put("greenmm", new Sprite(tileSheet.getSprite(4, 0)));
			spriteMap.put("greenmr", new Sprite(tileSheet.getSprite(5, 0)));
			spriteMap.put("greenbl", new Sprite(tileSheet.getSprite(6, 0)));
			spriteMap.put("greenbm", new Sprite(tileSheet.getSprite(7, 0)));
			spriteMap.put("greenbr", new Sprite(tileSheet.getSprite(8, 0)));
			spriteMap.put("treetl", new Sprite(tileSheet.getSprite(9, 0)));
			spriteMap.put("treetr", new Sprite(tileSheet.getSprite(10, 0)));
			spriteMap.put("treebl", new Sprite(tileSheet.getSprite(11, 0)));
			spriteMap.put("treebr", new Sprite(tileSheet.getSprite(12, 0)));
			spriteMap.put("treethicktl", new Sprite(tileSheet.getSprite(13, 0)));
			spriteMap.put("treethicktr", new Sprite(tileSheet.getSprite(14, 0)));
			spriteMap.put("tinygrass", new Sprite(tileSheet.getSprite(15, 0)));
			spriteMap.put("greenflowers", new Sprite(new Animation(tileSheet, 16, 0, 19, 0, true, 500, true)));
			
		} catch (SlickException e) {
			System.out.println("FAILED TO LOAD RESOURCE");
			e.printStackTrace();
		}
	}
}
