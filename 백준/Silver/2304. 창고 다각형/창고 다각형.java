/*
 * 그리디한 풀이.
 * 정렬후 가장 큰 막대기를 찾은뒤, 왼쪽 오른쪽 진행하는 방식
 */
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		Stick[] sticks = new Stick[n];
		for(int i = 0; i< n; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			sticks[i] = new Stick(l,h);
		}
		
		Arrays.sort(sticks, (s1, s2)-> (Integer.compare(s1.l, s2.l)));
		
		//find maxH
		int maxIdx = 0;
		int maxH = -1;
		for(int i  = 0; i < n; i++) {
			if(sticks[i].h > maxH) {
				maxH = sticks[i].h;
				maxIdx = i;
			}
		}
		
		int answer = 0;
		
		int preL = -1;
		int preH = 0;
		//left ~ maxH
		for(int i = 0; i <= maxIdx; i++) {
			if(sticks[i].h >= preH) {
				answer += (sticks[i].l - preL) * preH;
				preL = sticks[i].l;
				preH = sticks[i].h;
			}
		}
		
		preL = -1;
		preH = 0;
		//right ~ maxH
		for(int i = n-1; i>= maxIdx; i--) {
			if(sticks[i].h >= preH) {
				answer += (preL - sticks[i].l) * preH;
				preL = sticks[i].l;
				preH = sticks[i].h;
			}
		}
		
		answer += preH;
		
		System.out.println(answer);
		
	}
	
	public static class Stick{
		int l,h;
		public Stick(int l, int h) {
			this.l = l;
			this.h = h;
		}
	}
}
