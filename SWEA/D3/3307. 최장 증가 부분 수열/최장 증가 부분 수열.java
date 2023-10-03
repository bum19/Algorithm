import java.io.*;
import java.util.*;
//Lis[i] = lis[j]+1;
public class Solution {
    public static int t, n;
    public static int[] arr; //입력 수열
    public static int[] dp ; //dp[i] = dp[j] + 1;  j는 arr[j] < arr[i] && j < i를 만족하는 값.

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        t = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= t; test_case++){
            n = Integer.parseInt(br.readLine().trim());
            arr = new int[n];
            dp = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                dp[i] = 1; //dp 초기화
            }

            //dp세팅
            for(int i = 1; i < n; i++){
                for(int j = i-1; j>=0; j--){
                    if(arr[j] <= arr[i]){
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
            }

            int maxLength = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++){
                if(maxLength < dp[i]) maxLength = dp[i];
            }
            sb.append("#").append(test_case).append(" ").append(maxLength).append("\n");
        }
        System.out.println(sb);

    }
}
