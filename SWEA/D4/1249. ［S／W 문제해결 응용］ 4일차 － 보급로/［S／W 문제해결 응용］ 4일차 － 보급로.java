//
import java.io.*;
import java.util.*;
//처음에 완탐 dfs 생각
//두번째 다익스트라 생각
//세번재 dp활용. 격자로 이루어져있으면dp를 쓸수있다?(못쓴다) 해당위치까지의 최소경로 저장.
//bfs로 방문로직을 재밌게하기. 방문했더라도 거리가 더 짧으면 갱신하고 큐에 넣기. 즉, 방문boolean 배열이아니라 최소거리 배열 만들기. 일종의 dp?
public class Solution {
    public static int t, n;
    public static int[][] map;
    public static int[][] cost; //각 칸까지 도달까지걸리는 시간 저장하는 배열
    public static int[] dy = {-1,1,0,0}; //상하좌우
    public static int[] dx = {0,0,-1,1}; //상하좌우
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        t = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= t; test_case++){
            n = Integer.parseInt(br.readLine().trim());
            map = new int[n][n];
            cost = new int[n][n];

            for(int i  = 0; i < n; i++) {
                String str = br.readLine().trim();
                for (int j = 0; j < n; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            bfs(0, 0, 0);

            sb.append("#").append(test_case).append(" ").append(cost[n-1][n-1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int y, int x, int dist){

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x, 0});
        while(!q.isEmpty()){
            int cur[] = q.poll();

            for(int dir = 0; dir < 4; dir++){
                int ny = cur[0] + dy[dir];
                int nx = cur[1] + dx[dir];
                int curCost = cur[2];
                if(ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if(cost[ny][nx] > curCost + map[ny][nx]){
                    cost[ny][nx] = curCost + map[ny][nx];
                    q.offer(new int[]{ny,nx, curCost + map[ny][nx]});
                }
            }
        }
    }
}
