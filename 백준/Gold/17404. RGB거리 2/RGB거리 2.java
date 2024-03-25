import java.io.*;
import java.util.*;
/*
 * i번째는 i-1, i+1과 같으면 안된다.
 * 1번쨰는 n-1번과 같으면 안된다.
 * 
 * 
 * 1번째가 n-1번과 다르게하기위해,
 * 1번쨰가 R일떄 계산한 dpR에서 dpR[n-1][1], dpR[n-1][2] 비교
 * 같은방식으로 G일때, B일때 반복.
 * 
 */
public class Main {
	public static int[][] dpR; //1번째가 R인 경우
	public static int[][] dpG; //1번쨰가 G인 경우
	public static int[][] dpB; //1번쨰가 B인 경우
	
	public static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		dpR = new int[n][3];
		dpG = new int[n][3];
		dpB = new int[n][3];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(i == 0) {
				dpR[i][0] = r;
				dpR[i][1] = 1000001;
				dpR[i][2] = 1000001;
				
				dpG[i][0] = 1000001;
				dpG[i][1] = g;
				dpG[i][2] = 1000001;
				
				dpB[i][0] = 1000001;
				dpB[i][1] = 1000001;
				dpB[i][2] = b;
			}
			else {
				dpR[i][0] = Math.min(dpR[i-1][1], dpR[i-1][2]) + r;
				dpR[i][1] = Math.min(dpR[i-1][0], dpR[i-1][2]) + g;
				dpR[i][2] = Math.min(dpR[i-1][0], dpR[i-1][1]) + b;

				dpG[i][0] = Math.min(dpG[i-1][1], dpG[i-1][2]) + r;
				dpG[i][1] = Math.min(dpG[i-1][0], dpG[i-1][2]) + g;
				dpG[i][2] = Math.min(dpG[i-1][0], dpG[i-1][1]) + b;
				
				dpB[i][0] = Math.min(dpB[i-1][1], dpB[i-1][2]) + r;
				dpB[i][1] = Math.min(dpB[i-1][0], dpB[i-1][2]) + g;
				dpB[i][2] = Math.min(dpB[i-1][0], dpB[i-1][1]) + b;
			}
		}
		int answerR = Math.min(dpR[n-1][1],dpR[n-1][2]);
		int answerG = Math.min(dpG[n-1][0],dpG[n-1][2]);
		int answerB = Math.min(dpB[n-1][0],dpB[n-1][1]);
		
		int answer = Math.min(answerR, Math.min(answerB, answerG));
		
		System.out.println(answer);
	}
	
}
