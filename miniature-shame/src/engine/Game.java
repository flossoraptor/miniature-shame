package engine;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import assets.Area;

public class Game extends BasicGame
{
	AreaLoader areaLoader;
	Area currentArea;
	public Game(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		SpriteLoader spriteLoader = new SpriteLoader();
		areaLoader = new AreaLoader(spriteLoader);
		currentArea = areaLoader.areas.get("testArea");
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		currentArea.draw(g);
		
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			int targetFrameRate;
			
			targetFrameRate = 60;
			appgc = new AppGameContainer(new Game("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.setTargetFrameRate(targetFrameRate);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}