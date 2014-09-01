package engine;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import assets.Area;
import assets.Player;

public class Game extends BasicGame
{
	AreaLoader areaLoader;
	ActorLoader actorLoader;
	SpriteLoader spriteLoader;
	Area currentArea;
	Player player;
	public Game(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		spriteLoader = new SpriteLoader();
		actorLoader = new ActorLoader(spriteLoader);
		areaLoader = new AreaLoader(spriteLoader, actorLoader);
		currentArea = areaLoader.areas.get("testArea");
		player = new Player(spriteLoader.spriteMap.get("link"), 32, 32);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		player.handleUpdate(i, currentArea);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		currentArea.draw(g);
		player.draw(g);
	}
	
	public void keyPressed(int key, char code) {
		player.keyPressed(key, code);
	}

	public void keyReleased(int key, char code) {
		player.keyReleased(key, code);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			int targetFrameRate;
			
			targetFrameRate = 60;
			appgc = new AppGameContainer(new Game("Simple Slick Game"));
			appgc.setDisplayMode(1024, 576, false);
			appgc.setTargetFrameRate(targetFrameRate);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}