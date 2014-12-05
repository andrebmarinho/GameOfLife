package domain;

public class Cell {
	private Status status = Status.Empty; 

	public boolean isAlive() {
		return status == Status.Alive;
	}

	public void kill() {
		this.status = Status.Dead;
	}
	
	public void revive() {
		this.status = Status.Alive;
	}

	public Status getStatus() {
		return status;
	}	
	
}

