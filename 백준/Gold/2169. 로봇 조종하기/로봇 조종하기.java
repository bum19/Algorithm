import java.io.*;
import java.util.*;

/*
풀이 로직
- (0,0)에서 시작
- dp값 위에서온거, 왼쪽에서온거, 오른쪽에서 온 거 별로 저장
- bfs가 아니라, 한줄씩 갱신.
- dp[i][j][dir] = i,j로 dir방향에서 왔을 때 최대 가치.
- dp[i][j][위] = Math.max(dp[i-1][j][위, 왼 ,오른]) + map[i-1][j]
- dp[i][j][왼] = Math.max(dp[i][j-1][위, 왼]) + map[i-1][j]; //오른일수는 없음. 그러면 무한루프.
- dp[i][j][오] = Math.max(dp[i][j+1][위, 오]) + map[i-1][j]; //왼일수는 없음. 그러면 무한루프 
 */
public class Main {
	public static final int NOTVISIT = Integer.MIN_VALUE + 100;
	public static int n,m;
	public static int[][] map;
	public static int[][][] dp; //dp[i][j][dir] dir방향에서 온 (i,j)까지 도달하는데 걸리는 최대가치
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//init
		dp = new int[n][m][3];
		for(int i  = 0;  i < n; i++){
			for(int j = 0; j < m; j++) {
				Arrays.fill(dp[i][j], NOTVISIT);
			}
			
		}
	
		Arrays.fill(dp[0][0], map[0][0]);
		
		int sum = 0;
		for(int j = 0; j < m; j++) {
			sum += map[0][j];
			dp[0][j][1] = sum;
		}
		
		//dp 채우기
		for(int i = 1; i < n ; i++) {
			
			for(int j = 0; j < m; j++) {
				//위에서 온 경우 최댓값.
				dp[i][j][0] = Math.max(Math.max(dp[i-1][j][0], dp[i-1][j][1]), dp[i-1][j][2]) + map[i][j];

			}
			
			//왼쪽에서 온 경우 최댓값.
			for(int j = 1; j < m; j++) {
				dp[i][j][1] = Math.max(dp[i][j-1][0], dp[i][j-1][1]) + map[i][j];
			}
			
			//오른쪽에서 온 경우 최댓값
			for(int j = m-2; j>=0; j--) {
				dp[i][j][2] = Math.max(dp[i][j+1][0], dp[i][j+1][2]) + map[i][j];
			}
		}
		
		System.out.println(Math.max(dp[n-1][m-1][0], dp[n-1][m-1][1])); //오른쪽은 없으므로 볼필요 없다.
	}
	
}
