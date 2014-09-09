package assets;

public class State {
	private int remainingDuration;
	private float speed;
	private Position movementVector;
	private boolean move;
	
	public int getRemainingDuration() {
		return remainingDuration;
	}
	
	public void setRemainingDuration(int remainingDuration) {
		this.remainingDuration = remainingDuration;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public Position getMovementVector() {
		return movementVector;
	}
	
	public void setMovementVector(Position movementVector) {
		this.movementVector = movementVector;
	}
	
	public boolean canMove() {
		return move;
	}
	
	public void setCanMove(boolean move) {
		this.move = move;
	}
	
	public void decrementDuration() {
		this.remainingDuration--;
	}
	
	public State(int remainingDuration, float speed, Position movementVector, boolean move) {
		this.remainingDuration = remainingDuration;
		this.speed = speed;
		this.movementVector = movementVector;
		this.move = move;
	}
}
