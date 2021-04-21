package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Entrance {
	public static void main(String[] args){
		TreeSet<Team> teamList = null;
		List<String> teamInfo = new ArrayList<>();
		List<String> playerInfo = new ArrayList<>();
		List<String> resultInfo = new ArrayList<>();
		Game game = new Game();
		String filename = null;
		String[][] teamArrs;
		String menu = null;
		int menuLevel = 0;
		boolean isPreliminaryGame = false;
		boolean isFinalGame = false;
		
		//输入文件
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入文件名:");
		while(menuLevel!=4){
			scanner.hasNext();
			if(menuLevel==0){
				filename = scanner.next();
				teamArrs = Util.getTeamInfo(filename);
				if(teamArrs==null){
					System.out.println("获取球队信息失败，请重试！");
				}else{
					teamList = Util.setTeam(teamArrs);
					game.setTeamList(teamList);
					menuLevel=1;
					Util.readMenu("menu.txt");
				}
			}
			if(menuLevel==1){
				menu = scanner.next();
				switch (menu) {
					case "A":
						teamList = game.playPreliminaryGame();
						isPreliminaryGame = true;
						menuLevel=1;
						Util.readMenu("menu.txt");
						break;
					case "B":
						if(isPreliminaryGame){
							teamList = game.playFinals();
							menuLevel=1;
							isFinalGame=true;
							Util.readMenu("menu.txt");
						}else {
							System.out.println("还未进行初赛，请先进行初赛！");
							Util.readMenu("menu.txt");
						}						
						break;
					case "C":
						teamInfo = Util.getTeamInfo2(game.getTeamList());
						if(teamInfo.size()>0){
							for (int i = 0; i < teamInfo.size(); i++) {
								System.out.println(teamInfo.get(i));
							}
							menuLevel=2;
						}else {
							System.out.println("获取信息错误，请重试！");
							Util.readMenu("menu.txt");
						}			
						break;
					case "D":
						playerInfo = Util.getPlayerInfo(game.getTeamList());
						if(playerInfo.size()>0){
							for (int i = 0; i < playerInfo.size(); i++) {
								System.out.println(playerInfo.get(i));
							}
							menuLevel=2;
						}else {
							System.out.println("获取信息错误，请重试！");
							Util.readMenu("menu.txt");
						}
						break;
					case "E":
						if(isFinalGame){
							resultInfo = Util.getResult(teamList);
							for (int i = 0; i < resultInfo.size(); i++) {
								System.out.println(resultInfo.get(i));
							}
							menuLevel=2;
						}else {
							System.out.println("还未进行初赛，请先进行初赛！");
							Util.readMenu("menu.txt");
						}
						break;
					case "F":
						if(isPreliminaryGame){
							Iterator<Team> iterator =  teamList.iterator();
							int count = 0;
							StringBuffer fin = new StringBuffer();
							while(iterator.hasNext()){
								Team team2 = iterator.next();
								fin.append("-"+team2.gettName()); 
								count = count+1;								
								if(count==2){
									break;
								}
							}
							System.out.println(fin.toString());
							menuLevel=2;
						}else {
							System.out.println("还未进行初赛，请先进行初赛！");
							Util.readMenu("menu.txt");
						}
						break;
					case "G":
						if(!isPreliminaryGame){
							System.out.println("还未进行初赛，请先进行初赛！");
							Util.readMenu("menu.txt");							
						}else {
							if(isFinalGame){
								if(!resultInfo.isEmpty()){
									Util.writeResult(resultInfo);
									System.out.println("Fin");
								}else {
									resultInfo = Util.getResult(teamList);
									Util.writeResult(resultInfo);
									System.out.println("Fin");
								}								
								menuLevel=4;
							}else {
								System.out.println("还未进行决赛，请先进行决赛！");
								Util.readMenu("menu.txt");
							}							
						}		
						break;
					default:
						System.out.println("输入错误，请重试！");
						Util.readMenu("menu.txt");
						break;
					}
				}
			    
				if(menuLevel==2){
					menu = scanner.next();
					if(!menu.isEmpty()){
						Util.readMenu("menu.txt");
						menuLevel=1;
					}
				}
			}
		
			if(menuLevel==4){
				scanner.close();
			}
	}	
}
