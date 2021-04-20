package entity;

<<<<<<< HEAD
public class Team {

=======
import java.util.List;

public class Team {
	private String tName;
	private int rank;
	private List<Player> players;
	private int played;
	private int won;
	private int lost;
	private int drawn;
	private int goals;
	private int points;
	
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getPlayed() {
		return played;
	}
	public void setPlayed(int played) {
		this.played = played;
	}
	public int getWon() {
		return won;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	public int getDrawn() {
		return drawn;
	}
	public void setDrawn(int drawn) {
		this.drawn = drawn;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	@Override
	public String toString() {
		return tName + "-played" + played + "-won"+ won+ "-lost"+lost+ "-drawn"+drawn + "-goals"+goals+"-points"+points;
	}
>>>>>>> refs/heads/half
}
