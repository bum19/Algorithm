/*
 * 신발끈 공식 사용.
 * 자료형 입력까지 전부 double형으로 변환
 */
import java.io.*;
import java.util.*;
public class Main {
	public static double[][] points;
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		points = new double[n][2];
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Double.parseDouble(st.nextToken());
			points[i][1] = Double.parseDouble(st.nextToken());
		}
		
		//신발끈공식
		double area = 0.0;
		for(int i = 0; i< n-1; i++) {
			area += points[i][0] * points[i+1][1];
			area -= points[i][1] * points[i+1][0];
		}
		area += points[n-1][0] * points[0][1];
		area -= points[n-1][1] * points[0][0];
		
		area /= 2.0;
		
		System.out.printf("%.1f", Math.abs(area));
	}
	
}
