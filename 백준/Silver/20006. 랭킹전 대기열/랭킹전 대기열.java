/*
 * 방생성 로직, 생성순에 유의. 정원 수 유의, 이름 사전순 정렬에 유의
 * 플레잉어 x 방 조회 최대 시간복잡도 9만
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int p, m;
	public static List<Room> rooms;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		rooms = new ArrayList<>();
		for(int i= 0 ; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			String n = st.nextToken();
			boolean isRoomExist = false;
			for(int j = 0; j < rooms.size(); j++) {
				//방 돌면서 일일히 비교
				Room room = rooms.get(j);
				if( l - room.startlevel >= 0 && l - room.startlevel <= 20 && room.players.size() < m) {
					isRoomExist = true;
					room.players.add(new Player(n,l));
					break;
				}
			}
			
			//룸존재하지 않으면, 만들고 넣기
			if(!isRoomExist) {
				Room room = new Room(l-10);
				room.players.add(new Player(n,l));
				rooms.add(room);
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < rooms.size(); i++) {
			Room room = rooms.get(i);
			if(room.players.size() == m)
				sb.append("Started!\n");
			else
				sb.append("Waiting!\n");
			while(!room.players.isEmpty()) {
				Player p = room.players.poll();
				sb.append(p.l).append(' ').append(p.n).append('\n');
			}
		}
		
		System.out.println(sb);
	}
	
	
	
	public static class Room{
		int startlevel;
		PriorityQueue<Player> players;
		public Room(int startLevel) {
			this.startlevel = startLevel;
			players = new PriorityQueue<>((p1, p2) -> p1.n.compareTo(p2.n));
		}
	}
	
	public static class Player{
		String n;
		int l;
		public Player(String n, int l) {
			this.n = n;
			this.l = l;
		}
	}
	
}
