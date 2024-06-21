/*
 * dp인걸 못떠올림 알고리즘 분류봄.
 * 의문: n개의 숫자가 전부 9.9로 들어오면, 안터지나?
 */
import java.io.*;
public class Main {
	
	public static int n;
	public static double[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new double[n];
		dp[0] = Double.parseDouble(br.readLine());
		
		double maxVal = dp[0];
		for(int i = 1 ; i < n ; i++) {
			double input = Double.parseDouble(br.readLine());
			dp[i] = Math.max(input, dp[i-1] * input);
			maxVal = Math.max(maxVal, dp[i]);
		}
		
		System.out.printf("%.3f", maxVal);
	}
}
