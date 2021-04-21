package entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Game {
	private TreeSet<Team> teamList;
	
	private Team[] playGame(Team[] teamList){
		teamList[0].setPlayed(teamList[0].getPlayed()+1);
		teamList[1].setPlayed(teamList[1].getPlayed()+1);
		int goals1 = Util.getRandom1(2);
		int goals2 = Util.getRandom2(2);
		if(teamList[0].getRank()<teamList[1].getRank()){
			teamList[0].setGoals(teamList[0].getGoals()+goals1);
			teamList[1].setGoals(teamList[1].getGoals()+goals2);
			if(goals1>goals2){
				teamList[0].setWon(teamList[0].getWon()+1);
				teamList[0].setPoints(teamList[0].getPoints()+3);
				teamList[1].setLost(teamList[1].getLost()+1);
			}else if(goals1==goals2){
				teamList[1].setDrawn(teamList[1].getDrawn()+1);
				teamList[1].setPoints(teamList[1].getPoints()+1);
				teamList[0].setPoints(teamList[0].getPoints()+1);
				teamList[0].setDrawn(teamList[0].getDrawn()+1);
			}else if(goals1<goals2){
				teamList[0].setLost(teamList[0].getLost()+1);
				teamList[1].setPoints(teamList[1].getPoints()+3);
				teamList[1].setWon(teamList[1].getLost()+1);
			}
			if(goals1>0){
				if(goals1%2==0){
					for (int j = 0; j < teamList[0].getPlayers().size(); j++) {
						teamList[0].getPlayers().get(j).setGoals(teamList[0].getPlayers().get(j).getGoals()+goals1/2);
					}
				}else{
					int ind = Util.getRandom(1);
					teamList[0].getPlayers().get(ind).setGoals(teamList[0].getPlayers().get(ind).getGoals()+goals1/2+1);
					if(ind==0){
						ind = 1;
					}else if (ind==1) {
						ind = 0;
					}
					teamList[0].getPlayers().get(ind).setGoals(teamList[0].getPlayers().get(ind).getGoals()+goals1/2);				
				}
			}
			if(goals2>0){
				if(goals2%2==0){
					for (int j = 0; j < teamList[1].getPlayers().size(); j++) {
						teamList[1].getPlayers().get(j).setGoals(teamList[1].getPlayers().get(j).getGoals()+goals2/2);
					}
				}else{
					int ind = Util.getRandom(1);
					teamList[1].getPlayers().get(ind).setGoals(teamList[1].getPlayers().get(ind).getGoals()+goals2/2+1);
					if(ind==0){
						ind = 1;
					}else if (ind==1) {
						ind = 0;
					}
					teamList[1].getPlayers().get(ind).setGoals(teamList[1].getPlayers().get(ind).getGoals()+goals2/2);				
				}
			}
			
		}else{
			teamList[0].setGoals(teamList[0].getGoals()+goals2);
			teamList[1].setGoals(teamList[1].getGoals()+goals1);
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
			if(goals2>0){
				if(goals2%2==0){
					for (int j = 0; j < teamList[0].getPlayers().size(); j++) {
						teamList[0].getPlayers().get(j).setGoals(teamList[0].getPlayers().get(j).getGoals()+goals2/2);
					}
				}else{
					int ind = Util.getRandom(1);
					teamList[0].getPlayers().get(ind).setGoals(teamList[0].getPlayers().get(ind).getGoals()+goals2/2+1);
					if(ind==0){
						ind = 1;
					}else if (ind==1) {
						ind = 0;
					}
					teamList[0].getPlayers().get(ind).setGoals(teamList[0].getPlayers().get(ind).getGoals()+goals2/2);				
				}
			}
			if(goals1>0){
				if(goals1%2==0){
					for (int j = 0; j < teamList[1].getPlayers().size(); j++) {
						teamList[1].getPlayers().get(j).setGoals(teamList[1].getPlayers().get(j).getGoals()+goals1/2);
					}
				}else{
					int ind = Util.getRandom(1);
					teamList[1].getPlayers().get(ind).setGoals(teamList[1].getPlayers().get(ind).getGoals()+goals1/2+1);
					if(ind==0){
						ind = 1;
					}else if (ind==1) {
						ind = 0;
					}
					teamList[1].getPlayers().get(ind).setGoals(teamList[1].getPlayers().get(ind).getGoals()+goals1/2);				
				}
			}
		}
		return teamList;
	}
	public Team[] playPenaltyShootOut(Team...teams){
		int[] goals = {0,0};
		for (int i = 0; i < teams.length; i++) {
			for (int j = 0; j < teams[i].getPlayers().size(); j++) {
				for (int j2 = 0; j2 < 5; j2++) {
					int re = Util.getRandomFor();
					goals[i] = goals[i]+re;
					teams[i].setGoals(teams[i].getGoals()+re);	
					teams[i].getPlayers().get(j).setGoals(teams[i].getPlayers().get(j).getGoals()+re);
				}
			}
		}
		if(goals[0]==goals[1]){
			int re2 = Util.getRandom(1);
			if(re2==0){
				teams[0].setRank(1);
				teams[1].setRank(2);
			}else {
				teams[1].setRank(1);
				teams[0].setRank(2);
			}
		}else if (goals[0]>goals[1]) {
			teams[0].setRank(1);
			teams[1].setRank(2);
		}else if(goals[0]<goals[1]){
			teams[1].setRank(1);
			teams[0].setRank(2);
		}
		return teams;
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
						teams.set(k, t1);
					}
				}
			}
		}
		teamList.clear();
		Collections.sort(teams, Util.getComparator4());
		
		for (int i = 0; i < teams.size(); i++) {
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
				forA[i] = teams.get(i);
				i=i+1;
			}
		}	
		forA = playGame(forA);
		for (int j = 0; j < forA.length; j++) {
			Team t1 = forA[j];
			for (int k = 0; k < teams.size(); k++) {
				if(teams.get(k).gettName() == t1.gettName()){
					teams.set(k, t1);
				}
			}
		}
		
		Collections.sort(teams, Util.getComparator4());
		if(Util.compare1(teams.get(0), teams.get(1))==0){
			Team[] re = playPenaltyShootOut(teams.get(0), teams.get(1));
			if(re[0].getRank()==1){
				teams.set(0, re[0]);
				teams.set(1, re[1]);
			}else {
				teams.set(0, re[1]);
				teams.set(1, re[0]);
			}
			for (int j = 2; j < teams.size(); j++) {
				teams.get(j).setRank(j+1);
			}
		}else{
			for (int i1 = 0; i1 < teams.size(); i1++) {
				teams.get(i1).setRank(i1+1);
			}
		}
		teamList.clear();
		teamList.addAll(teams);
		return teamList;		
	}
	
	public TreeSet<Team> getTeamList() {
		return teamList;
	}
	public void setTeamList(TreeSet<Team> teamList) {
		this.teamList = teamList;
	}
}