package engine;

import java.util.HashMap;
import java.util.Map;

import assets.Area;
import assets.Tile;

public class AreaLoader {
	public Map<String, Area> areas;
	public AreaLoader(SpriteLoader spriteLoader) {
		areas = new HashMap<>();
		Area testArea = new Area(40, 30, 16, 16);
		Tile[][] tiles = new Tile[40][30];
		Tile ground = new Tile(spriteLoader.spriteMap.get("ground"));
		Tile wall = new Tile(spriteLoader.spriteMap.get("wall"));
		for (int i=0; i<tiles.length; i++) {
			for (int j=0; j<tiles[i].length; j++) {
				if (i==0 || j==0) {
					tiles[i][j] = wall;
				} else if (i==tiles.length-1 || j==tiles[i].length-1) {
					tiles[i][j] = wall;
				} else {
					tiles[i][j] = ground;
				}
			}
		}
		testArea.setTiles(tiles);
		areas.put("testArea", testArea);
	}
}
