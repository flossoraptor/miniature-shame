package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;

import org.newdawn.slick.geom.Rectangle;

import assets.Actor;
import assets.Area;
import assets.Tile;

public class AreaLoader {
	public Map<String, Area> areas;
	public AreaLoader(SpriteLoader spriteLoader, ActorLoader actorLoader) {
		areas = new HashMap<>();
		Area testArea = new Area(32, 18, 32, 32);
		Tile[][] tiles = new Tile[32][18];
		List<Actor> actors = new ArrayList<Actor>();
		Map<String, Tile> tileMap = new HashMap<>();
		Tile greentl = new Tile(spriteLoader.spriteMap.get("greentl"), true);
		Tile greentm = new Tile(spriteLoader.spriteMap.get("greentm"), true);
		Tile greentr = new Tile(spriteLoader.spriteMap.get("greentr"), true);
		Tile greenml = new Tile(spriteLoader.spriteMap.get("greenml"), true);
		Tile greenmm = new Tile(spriteLoader.spriteMap.get("greenmm"), true);
		Tile greenmr = new Tile(spriteLoader.spriteMap.get("greenmr"), true);
		Tile greenbl = new Tile(spriteLoader.spriteMap.get("greenbl"), true);
		Tile greenbm = new Tile(spriteLoader.spriteMap.get("greenbm"), true);
		Tile greenbr = new Tile(spriteLoader.spriteMap.get("greenbr"), true);
		Tile treetl = new Tile(spriteLoader.spriteMap.get("treetl"), false);
		Tile treetr = new Tile(spriteLoader.spriteMap.get("treetr"), false);
		Tile treebl = new Tile(spriteLoader.spriteMap.get("treebl"), false);
		Tile treebr = new Tile(spriteLoader.spriteMap.get("treebr"), false);
		Tile treethicktl = new Tile(spriteLoader.spriteMap.get("treethicktl"), false);
		Tile treethicktr = new Tile(spriteLoader.spriteMap.get("treethicktr"), false);
		Tile tinygrass = new Tile(spriteLoader.spriteMap.get("tinygrass"), true);
		Tile greenflowers = new Tile(spriteLoader.spriteMap.get("greenflowers"), true);
		tileMap.put("1", greentl);
		tileMap.put("2", greentm);
		tileMap.put("3", greentr);
		tileMap.put("4", greenml);
		tileMap.put("5", greenmm);
		tileMap.put("6", greenmr);
		tileMap.put("7", greenbl);
		tileMap.put("8", greenbm);
		tileMap.put("9", greenbr);
		tileMap.put("10", treetl);
		tileMap.put("11", treetr);
		tileMap.put("12", treebl);
		tileMap.put("13", treebr);
		tileMap.put("14", treethicktl);
		tileMap.put("15", treethicktr);
		tileMap.put("16", tinygrass);
		tileMap.put("17", greenflowers);

		
		try(BufferedReader in = new BufferedReader(new FileReader("resources/area/testareaexport.txt"))){
		    String line;
		    boolean actorLayer = false;
		    int xRead, yRead = 0;
		    while ( (line=in.readLine()) != null) 
		    {
		    	if (line.equals("-")) {
		    		actorLayer = true;
		    		yRead = 0;
		    	} else {
			    	xRead = 0;
			        String[] values = line.split(",");
			        for(String v:values)
			        {
			        	if (!v.equalsIgnoreCase(""))
			        	{
			        		if (actorLayer == false) {
			        			tiles[xRead][yRead] = tileMap.get(v);
			        		} else {
			        			if (!v.equals("0")) {
			        				actors.add(actorLoader.actorMap.get(v).createNew(xRead * 32, yRead * 32));
			        			}
			        		}
			        		xRead++;
			        	}
			        }
			        yRead++;
		    	}
		    }
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		testArea.setTiles(tiles);
		testArea.setActors(actors);
		areas.put("testArea", testArea);
	}
}
