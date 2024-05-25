/*
 * 누적합 써야되는거 알고 품.
 * 1. 석순/종유석 길이별 개수 배열을 구한다.
 * 2. 구한 배열을 누적합 배열로 바꾼다.
 * 3. 파괴해야할 장애물이 최솟값인 곳을 찾는다.
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int[] seok;
	public static int[] jong;
	public static int n,h, min, count;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		seok = new int[h + 1];
		jong = new int[h + 1];
		for(int i = 0; i < n; i++) {
			int height = Integer.parseInt(br.readLine().trim());
			if(i % 2 == 0) {
				//석순에 넣기
				seok[height]++;
			}
			else {
				//종유석에 넣기
				jong[height]++;
			}
		}
		
		
		//누적합으로 바꾸기
		for(int i = 1; i <= h; i++) {
			seok[i] = seok[i] + seok[i-1];
			jong[i] = jong[i] + jong[i-1];
		}
		
		min = Integer.MAX_VALUE;
		count = 0;
		//최솟값 찾기
		for(int i = 1; i <= h; i++) {
			int curDisturb = seok[h]- seok[i-1] + jong[h] - jong[h-i];
			if(min > curDisturb) {
				min = curDisturb;
				count = 1;
			}
			else if(min == curDisturb){
				count++;
			}
		}
		
		System.out.println(min + " "+ count);
	}
}
