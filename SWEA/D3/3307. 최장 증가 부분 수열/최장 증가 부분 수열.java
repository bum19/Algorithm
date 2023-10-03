import java.io.*;
import java.util.*;
//Lis[i] = 현재까지 입력된 애들중 나보다 큰놈이있다면, 그자리에 갱신. 다 나보다 작다면, 새로 원소 추가.
public class Solution {
    public static int t, n;
    public static int[] arr; //입력 수열
    public static List<Integer> dp ; //dp[i] 길이 i를 만족하는 부분수열의 끝 값중 최솟값.

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        t = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= t; test_case++){
            n = Integer.parseInt(br.readLine().trim());
            arr = new int[n];
            dp = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            //dp 초기값 세팅
            dp.add(arr[0]);

            //부분수열 구하는 로직 시작.
            for(int i = 1; i < n; i++){
                int size = dp.size();
                for(int j = 0; j < size; j++){
                    if(dp.get(j) > arr[i]){ //arr[i] 보다 작은 dp값 있으면, 해당 값으로 갱신.
                        dp.set(j, arr[i]); break;
                    }
                    if(j == size-1){ //현재 리스트중 arr[i]보다 작은 수가 없다면, dp에 값 추가.
                        dp.add(arr[i]);
                    }
                }
            }

            sb.append("#").append(test_case).append(" ").append(dp.size()).append("\n");
        }
        System.out.println(sb);

    }
}
