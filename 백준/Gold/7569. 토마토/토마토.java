import java.io.*;
import java.util.*;

public class Main{
    public static int m, n, h, leftTomatoes, answer;
    public static List<int[]> startTomatoes;
    public static int[][][] box;
    public static boolean[][][] visited;
    public static int[] dy ={-1,1,0,0,0,0}; //뒤앞좌우상하
    public static int[] dx ={0,0,-1,1,0,0}; //뒤앞좌우상하
    public static int[] dh ={0,0,0,0,1,-1}; //뒤앞좌우상하


    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //input
        st = new StringTokenizer(br.readLine());
        m= Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        box = new int[h][n][m];
        visited = new boolean[h][n][m];
        startTomatoes = new ArrayList<>();

        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < m; k++){
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if(box[i][j][k] == 0) leftTomatoes++;
                    if(box[i][j][k] == 1) startTomatoes.add(new int[]{i,j,k});
                }
            }
        }
        // input done

        //edge case
        if(leftTomatoes == 0){
            System.out.println(0);
            return;
        }
        if(startTomatoes.size() == 0){
            System.out.println(-1);
            return;
        }

        //init
        answer = 0;

        //bfs
        Queue<int[]> q = new ArrayDeque<>(); // h,y,x, time
        for(int i = 0; i < startTomatoes.size(); i++){
            int[] tmp = startTomatoes.get(i);
            visited[tmp[0]][tmp[1]][tmp[2]] = true;
            q.add(new int[]{tmp[0], tmp[1], tmp[2], 0});
        }
        while(!q.isEmpty()){
            if(answer != 0) break; //답이 구해졌으면 탈출
            int[] cur = q.poll();
            for(int dir = 0; dir < 6; dir++){
                int nh = cur[0] + dh[dir];
                int ny = cur[1] + dy[dir];
                int nx = cur[2] + dx[dir];
                int time = cur[3];
                if(nh < 0 || ny < 0 || nx < 0 || nh >=h || ny >= n || nx >= m || visited[nh][ny][nx] || box[nh][ny][nx] == -1) continue;

                visited[nh][ny][nx] = true;
                //0이면 익은걸로 바꾸고 진행. 이때 남은 안익은거 없으면 탈출
                if(box[nh][ny][nx] == 0){
                    box[nh][ny][nx] = 1;
                    leftTomatoes--;
                    if(leftTomatoes <= 0){
                        answer = time + 1;
                        break;
                    }
                }
                q.add(new int[]{nh,ny,nx,time+1});
            }
        }

        //print
        if(leftTomatoes == 0){
            System.out.println(answer);
        }
        if(leftTomatoes != 0){
            System.out.println(-1);
        }

    }
}