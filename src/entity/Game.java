package entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Game {
	private TreeSet<Team> teamList;
	
	private Team[] playGame(Team[] teamList){
		if(teamList[0].getRank()>teamList[1].getRank()){
			int goals1 = Util.getRandom1(2);
			int goals2 = Util.getRandom2(2);
			if(goals1>goals2){
				teamList[0].setWon(teamList[0].getWon()+1);
				teamList[0].setWon(teamList[0].getPoints()+3);
				teamList[1].setWon(teamList[1].getLost()+1);
			}else if(goals1==goals2){
				teamList[1].setWon(teamList[1].getDrawn()+1);
				teamList[1].setWon(teamList[1].getPoints()+1);
				teamList[0].setWon(teamList[0].getPoints()+1);
				teamList[0].setWon(teamList[0].getDrawn()+1);
			}else if(goals1<goals2){
				teamList[1].setWon(teamList[1].getWon()+1);
				teamList[1].setWon(teamList[1].getPoints()+3);
				teamList[0].setWon(teamList[0].getLost()+1);
			}
			teamList[0].setGoals(goals1);
			teamList[1].setGoals(goals2);
		}else{
			int goals1 = Util.getRandom1(2);
			int goals2 = Util.getRandom2(2);
			if(goals1>goals2){
				teamList[1].setWon(teamList[1].getWon()+1);
				teamList[1].setPoints(teamList[1].getPoints()+3);
				teamList[0].setLost(teamList[0].getLost()+1);
			}else if(goals1==goals2){
				teamList[1].setDrawn(teamList[1].getDrawn()+1);
				teamList[1].setPoints(teamList[1].getPoints()+1);
				teamList[0].setPoints(teamList[0].getPoints()+1);
				teamList[0].setDrawn(teamList[0].getDrawn()+1);
			}else if(goals1<goals2){
				teamList[0].setWon(teamList[0].getWon()+1);
				teamList[0].setPoints(teamList[0].getPoints()+3);
				teamList[1].setLost(teamList[1].getLost()+1);
			}
			teamList[0].setGoals(goals2);
			teamList[1].setGoals(goals1);
		}
		for (int i = 0; i < teamList.length; i++) {
			int goals = teamList[i].getGoals();
			if(goals>0){
				if(goals%2==0){
					for (int j = 0; j < teamList[i].getPlayers().size(); j++) {
						teamList[i].getPlayers().get(j).setGoals(goals/2);
					}					
				}else{
					int ind = Util.getRandom1(1);
					teamList[i].getPlayers().get(ind).setGoals(goals/2+1);
					if(ind==0){
						ind = 1;
					}else if (ind==1) {
						ind = 0;
					}
					teamList[i].getPlayers().get(ind).setGoals(goals/2);				
				}
			}
		}
		return teamList;
	}
	public TreeSet<Team> playPenaltyShootOut(){
		
		return null;
	}
	public TreeSet<Team> playPreliminaryGame(){
		List<Team> teams = new ArrayList<Team>();
		Iterator<Team> iterator =  teamList.iterator();
		while(iterator.hasNext()){
			teams.add(iterator.next());
		}
		List<Team[]> forA = new ArrayList<>();
		int size = teams.size();
		for (int i = 0; i < size-1; i++) {
			for (int j = i; j < size; j++) {
				if(i!=j){
					Team[] teams2 = {teams.get(i),teams.get(j)};
					forA.add(teams2);
				}					
			}
		}
		
		for (int i = 0; i < forA.size(); i++) {
			Team[] teams2 = playGame(forA.get(i));
			for (int j = 0; j < teams2.length; j++) {
				Team t1 = teams2[j];
				for (int k = 0; k < teams.size(); k++) {
					if(teams.get(k).gettName() == t1.gettName()){
						int goals = teams.get(k).getGoals()+t1.getGoals();
						int points = teams.get(k).getPoints()+t1.getPoints();
						int drawn = teams.get(k).getDrawn()+t1.getDrawn();
						int lost = teams.get(k).getLost()+t1.getLost();
						int won = teams.get(k).getWon()+t1.getWon();
						int played = teams.get(k).getPlayed()+1;
						int goals1 = teams.get(k).getPlayers().get(0).getGoals()+t1.getPlayers().get(0).getGoals();
						int goals2 = teams.get(k).getPlayers().get(1).getGoals()+t1.getPlayers().get(1).getGoals();
						teams.get(k).setDrawn(drawn);
						teams.get(k).setGoals(goals);
						teams.get(k).setLost(lost);
						teams.get(k).setPlayed(played);
						teams.get(k).setPoints(points);
						teams.get(k).setWon(won);
						teams.get(k).getPlayers().get(0).setGoals(goals1);
						teams.get(k).getPlayers().get(1).setGoals(goals2);
					}
				}
			}
		}
		teamList.clear();
		Collections.sort(teams, Util.getComparator1());
		for (int i = 1; i < teams.size()+1; i++) {
			teams.get(i).setRank(i);
			teamList.add(teams.get(i));
		}
		return teamList;
	}
	public TreeSet<Team> playFinals(){
		List<Team> teams = new ArrayList<Team>();
		Iterator<Team> iterator =  teamList.iterator();
		Team[] forA = new Team[2];
		int i=0;
		while(iterator.hasNext()){
			teams.add(iterator.next());
			if(i<2){
				forA[i] = iterator.next();
				i=i+1;
			}
		}		
		forA = playGame(forA);
		for (int j = 0; j < forA.length; j++) {
			Team t1 = forA[j];
			for (int k = 0; k < 2; k++) {
				if(teams.get(k).gettName() == t1.gettName()){
					int goals = teams.get(k).getGoals()+t1.getGoals();
					int points = teams.get(k).getPoints()+t1.getPoints();
					int drawn = teams.get(k).getDrawn()+t1.getDrawn();
					int lost = teams.get(k).getLost()+t1.getLost();
					int won = teams.get(k).getWon()+t1.getWon();
					int played = teams.get(k).getPlayed()+1;
					int goals1 = teams.get(k).getPlayers().get(0).getGoals()+t1.getPlayers().get(0).getGoals();
					int goals2 = teams.get(k).getPlayers().get(1).getGoals()+t1.getPlayers().get(1).getGoals();
					teams.get(k).setDrawn(drawn);
					teams.get(k).setGoals(goals);
					teams.get(k).setLost(lost);
					teams.get(k).setPlayed(played);
					teams.get(k).setPoints(points);
					teams.get(k).setWon(won);
					teams.get(k).getPlayers().get(0).setGoals(goals1);
					teams.get(k).getPlayers().get(1).setGoals(goals2);
				}
			}
		}
		teamList.clear();
		Collections.sort(teams, Util.getComparator1());
		for (int i1 = 1; i1 < teams.size()+1; i1++) {
			teams.get(i1).setRank(i1);
			teamList.add(teams.get(i1));
		}
		return teamList;		
	}
	
	public TreeSet<Team> getTeamList() {
		return teamList;
	}
	public void setTeamList(TreeSet<Team> teamList) {
		this.teamList = teamList;
	}
}
