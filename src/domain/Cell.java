package domain;

public class Cell {
	private Status status = Status.Empty; 

	public boolean isAlive() {
		return getStatus() == Status.Alive;
	}

	public void kill() {
		this.setStatus(Status.Dead);
	}
	
	public void revive() {
		this.setStatus(Status.Alive);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}	
	
}

