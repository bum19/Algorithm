import java.io.*;
import java.util.*;
//열쇠를 가지고 방문한적있는지 체크
public class Main {
    public static int n,m, sx, sy;
    public static char[][] maze; // 입력받을 미로
    public static boolean[][][] visited; //[i][j][k] i,j칸을 열쇠를 가지고 방문했는지 여부. k는 6개 키를 체크하는 비트마스킹
    public static int[] dy = {-1,0,1,0};//상우하좌
    public static int[] dx = {0,1,0,-1};//상우하좌
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new char[n][m];
        visited = new boolean[n][m][1 << 6];

        for(int i = 0 ; i < n ; i++){
            String str = br.readLine().trim();
            for(int j = 0; j < m; j++){
                maze[i][j] = str.charAt(j);
                if(maze[i][j] == '0'){
                    sy = i; sx = j;
                }
            }
        }
        //end input

        System.out.println(move());
    }

    private static int move(){
        Queue<int[]> q = new ArrayDeque<>();
        visited[sy][sx][0] = true;
        q.add(new int[]{sy,sx,0,0}); //현재위치, 이동횟수, 획득열쇠
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int dir = 0; dir <4; dir++){
                int ny = cur[0] + dy[dir];
                int nx = cur[1] + dx[dir];
                //범위벗어나거나 벽인 경우 탐색중지
                if(ny < 0 || nx < 0 || ny >= n || nx >= m || maze[ny][nx] == '#') continue;
                //탈출구만났을경우 탈출
                if(maze[ny][nx] == '1'){
                    return cur[2]+1;
                }
                //제일 최근 획득한 키로 방문한적 있을경우 탐색중지
                if(visited[ny][nx][cur[3]]) continue;
                //문 만났을 경우 키가 없다면 탐색중지
                if( 'A' <=  maze[ny][nx] && maze[ny][nx] <= 'F' && (cur[3] & 1 << (maze[ny][nx]-'A')) == 0 ) continue;
                //위 상황 외의 경우 탐색 계속진행
                visited[ny][nx][cur[3]] = true;
                //열쇠 만났을경우 열쇠회득 갱신후 큐에 추가
                if('a' <=  maze[ny][nx] && maze[ny][nx] <= 'f'){
                    visited[ny][nx][ cur[3] | 1<<(maze[ny][nx]-'a')] = true;
                    q.add(new int[]{ny,nx,cur[2]+1, cur[3] | 1<<(maze[ny][nx]-'a')});
                }
                //열쇠 만난경우아니면 그냥 탐색 진행
                else{
                    q.add(new int[]{ny,nx,cur[2]+1, cur[3]});
                }
            }
        }
        return -1;
    }

}
