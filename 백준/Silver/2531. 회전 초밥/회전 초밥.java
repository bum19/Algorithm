import java.io.*;
import java.util.*;

public class Main {
	public static int n,d,k,c, maxKind;
	public static int[] sushiCnt;
	public static int[] input;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		input = new int[n];
		for(int i = 0; i < n ; i++) {
			input[i] = Integer.parseInt(br.readLine().trim());
		}
		
		//init
		int kind = 0; //count except coupon
		sushiCnt = new int[d+1];
		for(int i = 0; i < k; i++) {
			sushiCnt[input[i]]++;
			if(sushiCnt[input[i]]== 1) kind++;
		}
		//check coupon
		if(sushiCnt[c] == 0) {
			maxKind = Math.max(kind+1, maxKind);
		}
		else {
			maxKind = Math.max(kind, maxKind);
		}
		

		for(int s = 0; s < n; s++) {
			//시작 초밥 제거
			sushiCnt[input[s]]--;
			if(sushiCnt[input[s]] == 0) kind--;
			
			//다음 초밥 포
			sushiCnt[input[(s+k)%n]]++;
			if(sushiCnt[input[(s+k)%n]] == 1) kind++;
			
			//check coupon
			if(sushiCnt[c] == 0) {
				maxKind = Math.max(kind+1, maxKind);
			}
			else {
				maxKind = Math.max(kind, maxKind);
			}
		}
		System.out.println(maxKind);
	}
}
