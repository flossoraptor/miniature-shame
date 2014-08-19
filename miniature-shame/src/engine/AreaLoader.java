package engine;

import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;

import assets.Area;
import assets.Tile;

public class AreaLoader {
	public Map<String, Area> areas;
	public AreaLoader(SpriteLoader spriteLoader) {
		areas = new HashMap<>();
		Area testArea = new Area(32, 18, 32, 32);
		Tile[][] tiles = new Tile[32][18];
		Map<String, Tile> tileMap = new HashMap<>();
		Tile greentl = new Tile(spriteLoader.spriteMap.get("greentl"));
		Tile greentm = new Tile(spriteLoader.spriteMap.get("greentm"));
		Tile greentr = new Tile(spriteLoader.spriteMap.get("greentr"));
		Tile greenml = new Tile(spriteLoader.spriteMap.get("greenml"));
		Tile greenmm = new Tile(spriteLoader.spriteMap.get("greenmm"));
		Tile greenmr = new Tile(spriteLoader.spriteMap.get("greenmr"));
		Tile greenbl = new Tile(spriteLoader.spriteMap.get("greenbl"));
		Tile greenbm = new Tile(spriteLoader.spriteMap.get("greenbm"));
		Tile greenbr = new Tile(spriteLoader.spriteMap.get("greenbr"));
		Tile treetl = new Tile(spriteLoader.spriteMap.get("treetl"));
		Tile treetr = new Tile(spriteLoader.spriteMap.get("treetr"));
		Tile treebl = new Tile(spriteLoader.spriteMap.get("treebl"));
		Tile treebr = new Tile(spriteLoader.spriteMap.get("treebr"));
		Tile treethicktl = new Tile(spriteLoader.spriteMap.get("treethicktl"));
		Tile treethicktr = new Tile(spriteLoader.spriteMap.get("treethicktr"));
		Tile tinygrass = new Tile(spriteLoader.spriteMap.get("tinygrass"));
		Tile greenflowers = new Tile(spriteLoader.spriteMap.get("greenflowers"));
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
		    int xRead, yRead = 0;
		    while ( (line=in.readLine()) != null) 
		    {
		    	xRead = 0;
		        String[] values = line.split(",");
		        for(String v:values)
		        {
		        	if (!v.equalsIgnoreCase(""))
		        	{
		        		tiles[xRead][yRead] = tileMap.get(v);
		        		xRead++;
		        	}
		        }
		        yRead++;
		    }
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		testArea.setTiles(tiles);
		areas.put("testArea", testArea);
	}
}
