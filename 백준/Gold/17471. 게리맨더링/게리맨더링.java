//
import java.io.*;
import java.util.*;
//부분집합 구하기 dfs 응용후, 서로소집합 로직응용해서 연결되있는지 여부확인한뒤, 가능할경우 두 구역 인구수 차이 갱신
public class Main {
	public static int n, minAbs;
	public static int[] peopleNums;
	public static boolean[] isA; //구역 A인지 확인. true면 A구역, false면 B구역.
	public static List<Integer>[] adjs;
	public static int[] parents; //구역이 available한지 확인할 때 쓸 parent배열
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//입력
		n = Integer.parseInt(br.readLine().trim());
		peopleNums = new int[n+1]; //인덱스 1부터 시작.
		adjs = new List[n+1]; //인덱스 1부터 시작
		isA = new boolean[n+1]; //인덱스 1부터 시작
		for(int i = 1; i <= n ; i++) {
			adjs[i] = new ArrayList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			peopleNums[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n; i++) { 
			String[] strs = br.readLine().split(" ");
			for(int j = 1; j < strs.length; j++) {
				adjs[i].add(Integer.parseInt(strs[j]));				
			}
		}
//		System.out.println(Arrays.deepToString(adjs));
		//입력 끝
		minAbs = Integer.MAX_VALUE;
		setRegions(1, 0, 0);
		
		if(minAbs == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minAbs);
		
	}
	
	private static void setRegions(int cnt, int aCnt, int bCnt) { //구역정하고 가능한 구역나눔일시 출력.
		
		if(cnt == n+1) {
			if(aCnt == 0 || bCnt == 0) return; //둘중 한구역이 아예 없을경우 계산안함.
			int[] aRegion = new int[aCnt]; //값으로 a구역 인덱스를 가짐.
			int[] bRegion = new int[bCnt]; //값으로 b구역 인덱스를 가짐.
			
			//aRegion, bRegion 인덱스 세팅
			int aIdx = 0, bIdx = 0;
			for(int i = 1; i <= n; i++) {
				if(isA[i]) aRegion[aIdx++] = i;
				else	   bRegion[bIdx++] = i;
			}
			////////////
//			System.out.println("aRegion :"+Arrays.toString(aRegion));
//			System.out.println("bRegion :"+Arrays.toString(bRegion));
//			System.out.println("---------------");
			//a그룹으로 prim, b그룹으로 prim했을때 둘다 가능하면
//			System.out.println(isRegionAvailable(aRegion)+" "+isRegionAvailable(bRegion));
			if(isRegionAvailable(aRegion) && isRegionAvailable(bRegion)) { //두 구역 모두 서로 연결되어있을때
				//인구 차이 구하고 갱신
				int aPeopleNum = 0, bPeopleNum = 0;
				for(int i = 0; i < aCnt; i++) {
					aPeopleNum += peopleNums[aRegion[i]];
				}
				
				for(int i = 0; i < bCnt; i++) {
					bPeopleNum += peopleNums[bRegion[i]];
				}
//				System.out.println("두 구역 인구차 : "+Math.abs(aPeopleNum - bPeopleNum));
				if(minAbs > Math.abs(aPeopleNum - bPeopleNum)) minAbs = Math.abs(aPeopleNum - bPeopleNum);
				
			} 
			return;
		}
		
		isA[cnt] = true;
		setRegions(cnt+1, aCnt+1, bCnt);
		isA[cnt] = false;
		setRegions(cnt+1, aCnt, bCnt+1);
		
	}
	
	
	private static boolean isRegionAvailable(int[] region) {
		parents = new int[n+1];
		
		for(int i = 0; i < region.length; i++) {
			parents[region[i]] = region[i];
		}
		
		//각 노드에 대해 인접한 노드와 union 진행.
		for(int i = 0; i < region.length; i++) {
			for(int node : adjs[region[i]]) {//region[i] 노드와 인접한 노드 에대해	
				if(parents[node] != 0) { //0이 아니면 해당 노드가 존재한다.
					union(region[i], node);//해당노드가 region에 있다면, 합친다.
				}
			}
		}
		//그 후, 모든 노드가 같은 parent인지 확인.(union작업을 통해. true가나올경우 인접했던게 아니므로 거짓 출력)
		for(int i = 0; i < region.length - 1; i++) {
			if(union(region[i], region[i+1])) return false;
		}
		//모두 같은 노드라면, true 출력.
		return true;
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

}
