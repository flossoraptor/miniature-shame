package engine;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.geom.Rectangle;

import assets.Actor;

public class ActorLoader {
	public Map<String, Actor> actorMap;
	public ActorLoader(SpriteLoader spriteLoader) {
		actorMap = new HashMap<>();
		actorMap.put("41", new Actor(spriteLoader.spriteMap.get("moblin"), new Rectangle(0, 0, 32, 32)));
	}
}
