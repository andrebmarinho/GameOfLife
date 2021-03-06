package br.unb.cic.gameoflife.domain;

public class Cell {
	private Status status;

	public Cell() {
		this.status = Status.Dead;
	}
	
	public Cell(Status status) {
		this.status = status;
	}
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