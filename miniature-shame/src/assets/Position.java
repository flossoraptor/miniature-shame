package assets;

import java.util.ArrayList;
import java.util.List;

public class Position {
	private int x, y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets a list of coordinates including the original position and the ones immediately surrounding it.
	 * Note that the list will not have invalid positions removed, so you will have to check yourself
	 * that you're not trying to retrieve negative tiles or tiles out of the bounds of the area.
	 * 
	 * @return a list of positions including the original and 8 surrounding positions
	 */
	
	public List<Position> getPlusPerimeter(){
		List<Position> preliminaryPositionList = new ArrayList<Position>();
		preliminaryPositionList.add(this);
		preliminaryPositionList.add(new Position(x-1, y-1));
		preliminaryPositionList.add(new Position(x-1, y));
		preliminaryPositionList.add(new Position(x-1, y+1));
		preliminaryPositionList.add(new Position(x, y-1));
		preliminaryPositionList.add(this);
		preliminaryPositionList.add(new Position(x, y+1));
		preliminaryPositionList.add(new Position(x+1, y-1));
		preliminaryPositionList.add(new Position(x+1, y));
		preliminaryPositionList.add(new Position(x+1, y+1));
		return preliminaryPositionList;
	}
}
