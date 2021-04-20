package entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class Util {
	
	public static String[][] getTeamInfo(String file){
		File teamFile = new File(file);
		if(!teamFile.exists()|| !teamFile.getName().endsWith("txt")){
			System.out.println("文件不存在或文件格式内容存在错误，请修改后重试1:");
			return null;
		}
		String[] itemArr;
		String[][] teamArrs = new String[4][];
		try (FileReader reader = new FileReader(teamFile)){
			System.out.println((int) teamFile.length());
			char[] len = new char[(int) teamFile.length()];
			reader.read(len);
			String teamString = new String(len);
			String[] teamArr = teamString.split(",");
			for (int i = 0; i < teamArr.length; i++) {
				itemArr = teamArr[i].split(" ");
				if(itemArr.length!=4){
					System.out.println("文件不存在或文件格式内容存在错误，请修改后重试3:");
					return null;
				}
				if(itemArr[3].matches("")){
					System.out.println("文件不存在或文件格式内容存在错误，请修改后重试3:");
					return null;
				}
				teamArrs[i] = itemArr;
			}
			return teamArrs;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		return null;	
	}	
	public static TreeSet<Team> setTeam(String[][] teamArrs){
		String[] teamArr;
		Team team;
		TreeSet<Team> teams = new TreeSet<>(getComparator1());
		List<Player> players;
		for (int i = 0; i < teamArrs.length; i++) {
			team = new Team();
			teamArr = teamArrs[i];
			team.settName(teamArr[0]);
			team.setRank(new Integer(teamArr[3].trim()));
			players  = new ArrayList<>();
			for (int j = 0; j < 2; j++) {
					Player player = new Player(teamArr[j+1],teamArr[0]);
					players.add(player);
			}
			team.setPlayers(players);
			teams.add(team);
		}		
		return teams;		
	}
	
	public static boolean readMenu(String file){
		boolean flag = false;
		File teamFile = new File(file);
		if(!teamFile.exists()|| !teamFile.isFile()){
			flag = false;
		}
		try (FileReader reader = new FileReader(teamFile)){
			char[] len = new char[(int) teamFile.length()];
			reader.read(len);
			String teamString = new String(len);
			System.out.println(teamString);
			flag = true;			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return flag;
	}
	public static List<String> getTeamInfo2(TreeSet<Team> teams){
		List<String> teamInfo = new ArrayList<>();
		if(!teams.isEmpty()){
			Iterator<Team> iterator =  teams.iterator();
			while(iterator.hasNext()){
				Team team = iterator.next();
				teamInfo.add(team.toString());
			}
		}
		return teamInfo;
		
	}
	public static List<String> getPlayerInfo(TreeSet<Team> teams){
		List<String> playerInfo = new ArrayList<>();
		List<Player> players;
		System.out.println("teams size:"+teams.size());
		if(!teams.isEmpty()){
			Iterator<Team> iterator =  teams.iterator();
			while(iterator.hasNext()){
				Team team = iterator.next();
				players = team.getPlayers();
				for (int j = 0; j < players.size(); j++) {
					playerInfo.add(players.get(j).toString());
				}	
			}
		}
		return playerInfo;		
	}
	public static List<String> getResult(TreeSet<Team> teams){
		List<String> result = new ArrayList<>();
		if(!teams.isEmpty()){
			String team = "Champion-"+teams.first().gettName();
			result.add(team);
			List<Player> players = new ArrayList<>();
			Iterator<Team> iterator =  teams.iterator();
			while(iterator.hasNext()){
				Team team2 = iterator.next();
				players.addAll(team2.getPlayers());	
			}
			Collections.sort(players, getComparator2());
			String player = "MVP-"+players.get(0).getName()+"("+players.get(0).getTName()+")";
			result.add(player);
		}
		return result;
		
	}
	public static int getRandomFor(){
		int re= getRandom(100);
		if(re<20 || re==20)
			return 0;
		else 
			return 1;
	}
	public static int getRandom(int x){
		Random random = new Random();
		Integer yInteger = random.nextInt(x);
		return yInteger.intValue();
	}
	public static int getRandom1(int x){
		Integer yInteger = getRandom(x);
		int mid = 5+yInteger.intValue();
		return getRandom(mid);
	}
	public static int getRandom2(int x){
		Integer yInteger = getRandom(x);
		int mid = 5-yInteger.intValue();
		return getRandom(mid);
	}
	public static int compare(Team t1, Team t2){
		if(t1.getPoints()>t2.getPoints()){
        	return 1;  //正数表示h1比h2要大
        }else if(t1.getPoints()==t2.getPoints()){
        	if(t1.getGoals()>t2.getGoals()){
        		return 1;
        	}else if (t1.getGoals()==t2.getGoals()) {
        		return 0;						
			}else if (t1.getGoals()<t2.getGoals()) {
				return -1;
			}
        }else if(t1.getPoints()<t2.getPoints()){
        	 return -1;
        }
		return 0;
		
	}
	public static Comparator<Team> getComparator1(){
		Comparator<Team> c = new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
            	int i= compare(t1, t2);
            	if(i==0){
            		int ra = getRandom(2);
					if(ra==1){
						return 1;
					}else{
						return -1;
					}
            	}
				return i;
            }
        };
		return c;
	}
	public static Comparator<Player> getComparator2(){
		Comparator<Player> c = new Comparator<Player>() {
            @Override
            public int compare(Player t1, Player t2) {
                if(t1.getGoals()>=t2.getGoals())
                    return 1;  //正数表示h1比h2要大
                else
                    return -1;
            }
        };
		return c;
	}
	public static Comparator<Team> getComparator3(){
		Comparator<Team> c = new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                if(t1.getRank()>=t2.getRank())
                    return 1;  //正数表示h1比h2要大
                else
                    return -1;
            }
        };
		return c;
	}
	public static void writeResult(List<String> resultInfo){
		File f = new File("result.txt");
		try (FileWriter fr = new FileWriter(f)) {
            // 以字符流的形式把数据写入到文件中
            char[] cs = (resultInfo.get(0)+resultInfo.get(1)).toCharArray();
            fr.write(cs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	private static String getFileContent(String filePath) throws IOException {        
        byte[] b = new byte[28];         
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            inputStream.read(b, 0, 28);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return bytesToHexString(b);
    }
	private static String bytesToHexString(byte[] src){
		StringBuilder builder = new StringBuilder(); 
		if(src == null || src.length==0){
			return null;
		}
		for(byte item:src){
			int v = item & 0xff;
			String fv = Integer.toHexString(v);
			if(fv.length()<2){
				builder.append(0);
			}
			builder.append(fv);
		}
		return builder.toString();		
	}
	public static boolean getType(String filePath) throws IOException {
        String fileHead = getFileContent(filePath);         
        if (fileHead == null || fileHead.length() == 0) {
            return false;
        }
        fileHead = fileHead.toUpperCase();
        if(fileHead.startsWith("")){
        	return true;
        }
        return false;
    }
}
