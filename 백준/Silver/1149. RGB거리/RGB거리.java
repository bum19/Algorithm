import java.io.*;
import java.util.*;
/*
 * dp[i][0] = i위치까지 도달했을때 r색일경우 최댓값
 * dp[i][1] = i위치까지 도달했을때 g색일경우 최댓값
 * dp[i][2] = i위치까지 도달했을떄 b색일경우 최댓값
 * 
 * dp[i][0] = Math.min(arr[i][0] + dp[i-1][1], arr[i].r + dp[i-1][2])
 * 
 */
public class Main {
	public static int[] dpR, dpG, dpB;
	public static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		
		dpR = new int[n];
		dpG = new int[n];
		dpB = new int[n];
		
		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(i == 0) {
				dpR[i] = r;
				dpG[i] = g;
				dpB[i] = b;
			}
			else {
				dpR[i] = Math.min(dpG[i-1]+r, dpB[i-1]+r);
				dpG[i] = Math.min(dpR[i-1]+g, dpB[i-1]+g);
				dpB[i] = Math.min(dpR[i-1]+b, dpG[i-1]+b);
			}			
		}
		
		System.out.println(Math.min(dpR[n-1],  Math.min(dpG[n-1], dpB[n-1])));
		
	}
}
