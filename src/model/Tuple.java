package model;

public class Tuple {
	
	private int score;
	private Coup coup;

	public Tuple(int score, Coup coup) {
		this.score = score;
		this.coup = coup;
	}
	
	public int getScore() {
		return score;
	}
	
	public Coup getCoup() {
		return coup;
	}
}
