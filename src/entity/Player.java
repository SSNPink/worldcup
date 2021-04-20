package entity;

public class Player {
	private String name;
	private String tName;
	private int goals;
	
	public Player(String name,String tName){
		this.name = name;
		this.tName = tName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTName() {
		return tName;
	}
	public void setTName(String tName) {
		this.tName = tName;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	@Override
	public String toString() {
		return name + "-" + tName +  "-" +goals;
	}
}
