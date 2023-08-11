//메모리
//실행시간
import java.io.*;
import java.util.*;
public class Main {
	public static int n, m, minCityChickDist;
	public static List<int[]> chosenChickSpots = new ArrayList<int[]>();
	public static List<int[]> chickSpots = new ArrayList<int[]>();
	public static List<int[]> homeSpots = new ArrayList<int[]>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++){
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 2) chickSpots.add(new int[] {i,j});
				else if(temp == 1)homeSpots.add(new int[] {i,j});
			}
		}
		
		minCityChickDist = Integer.MAX_VALUE;
		comb(0);
		System.out.println(minCityChickDist);

	}

	public static void comb(int start) {
		if(chosenChickSpots.size() == m) {
			//탐색
			int CityChickDist = 0;
			for(int[] homeSpot : homeSpots) {
				int homeChickDist = Integer.MAX_VALUE;
				for(int[] chickSpot : chosenChickSpots) {
					homeChickDist = Math.min(homeChickDist, Math.abs(homeSpot[0] - chickSpot[0]) + Math.abs(homeSpot[1] - chickSpot[1]));
				}
				CityChickDist += homeChickDist;
			}
			//최소 도시거리 갱신
			minCityChickDist = Math.min(CityChickDist, minCityChickDist);
		}
		
		
		for(int i = start ; i < chickSpots.size(); i++) {
			chosenChickSpots.add(chickSpots.get(i));
			comb(i+1);
			chosenChickSpots.remove(chickSpots.get(i));
		}
		
	}
}
