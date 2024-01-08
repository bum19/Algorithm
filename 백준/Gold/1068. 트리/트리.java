import java.io.*;
import java.util.*;
//자식노드가 3개 이상일 수 있다.
public class Main {
	public static int n, killNode;
	public static int[] parents;
	public static boolean[] isParentNode;
	public static boolean[] isErased;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		parents = new int[n];
		for(int i = 0; i < n; i++) {
			parents[i] = Integer.parseInt(st.nextToken());			
		}
		
		killNode = Integer.parseInt(br.readLine());
		
		isParentNode =  new boolean[n];
		isErased = new boolean[n];
		
		erase(killNode);
		
		for(int i = 0; i< n; i++) {
			if(isErased[i]) continue;
			if(parents[i] != -1) isParentNode[parents[i]] = true;
		}
		
		int answer = 0;
		for(int i = 0; i < n; i++) {
			if(!isErased[i] && !isParentNode[i] ) answer++; 
		}
		
		System.out.println(answer);
	}
	
	private static void erase(int node) {
		for(int i = 0; i < n; i++) {
			if(isErased[i]) continue;
			if(parents[i] == node) {
				erase(i);
			}
		}
		
		isErased[node] = true;
	}
}
