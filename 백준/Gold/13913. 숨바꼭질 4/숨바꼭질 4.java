import java.io.*;
import java.util.*;
//시간이랑 경로 출력. 각칸에 시간이랑 경로 str로 저장하면 될듯.
public class Main{
	public static int n,k;
	public static int[] dist;
	public static char[] path; //최종적으로 어떤 연산으로 왔는지 저장.
	public static Map<Integer, String> idxDist; // idx, dist, path
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dist = new int[100001];
		Arrays.fill(dist,Integer.MAX_VALUE);
		path = new char[100001];
		
		
		dist[n] = 0;
		path[n] = 0;
		
		Queue<int[]> q= new ArrayDeque<>();
		q.add(new int[] {n, 0});
		while(!q.isEmpty()){
			int[] idp = q.poll(); //idx, dist
			if(idp[0] == k) break;
			//1 plus
			if(idp[0] + 1 <= 100000) {
				//기존에 값 없으면 갱신 후 넣기.
				if(dist[idp[0] + 1] == Integer.MAX_VALUE) {
					dist[idp[0]+ 1] = idp[1] + 1;
					path[idp[0]+ 1] = '+';
//		System.out.println(++cnt);		
					q.add(new int[] {idp[0]+1, idp[1]+1});
				}
			}
			//1 minus
			if(idp[0] - 1 >= 0) {
				//기존에 값 없으면 갱신 후 넣기.
				if(dist[idp[0] - 1] == Integer.MAX_VALUE) {
					dist[idp[0]- 1] = idp[1] + 1;
					path[idp[0]- 1] = '-';
					q.add(new int[] {idp[0]-1, idp[1]+1});
				}
			}
			//2 multiple
			if(idp[0] *2 <= 100000) {
				//기존에 값 없으면 갱신 후 넣기.
				if(dist[idp[0]*2] == Integer.MAX_VALUE) {
					dist[idp[0]*2] = idp[1] + 1;
					path[idp[0]*2] = '*';
					q.add(new int[] {idp[0]*2, idp[1]+1});
				}
			}
		}
		
		System.out.println(dist[k]);
		StringBuilder sb = new StringBuilder();

		int curIdx = k;
		while(curIdx != n) {
			sb.insert(0, curIdx);
			sb.insert(0, ' ');
			if(path[curIdx] == '+')		curIdx--;
			else if(path[curIdx] == '-')curIdx++;
			else if(path[curIdx] == '*')curIdx /= 2;
		}
		sb.insert(0, curIdx);
		System.out.println(sb);
		
	}
}