import java.io.*;
import java.util.*;
public class Main {
    public static int n,m,d, maxKill;
    public static int[] archers;
    public static int[][] board;
    public static int[][] tmp;
    public static boolean[][] visited;
    public static boolean[][] gonnaDie;
    public static Queue<int[]> q = new ArrayDeque<>();
    public static int[] dy = {0,-1,0}; //좌상우
    public static int[] dx = {-1,0,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        maxKill = Integer.MIN_VALUE;
        archers = new int[3];
        board = new int[n][m];
        tmp = new int[n][m]; //탐색에 쓸 보드
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //궁수 위치 정하기 m개 열중 3곳
        for(int i = 0; i < m; i++){
            for(int j = i+1; j < m;j++){
                for(int k = j+1; k < m; k++){
                    archers[0] = i;
                    archers[1] = j;
                    archers[2] = k;
//                    if(i == 0 && j == 2 && k == 4){
//                        System.out.println("playgame start");
//                    }
                    playGame();
                }
            }
        }
        System.out.println(maxKill);
    }

    private static void playGame(){
        int killCnt = 0;
        //게임판 초기화
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                tmp[i][j] = board[i][j];
            }
        }
        for(int i = n-1; i >= 0; i--){
            gonnaDie = new boolean[n][m];//죽일사람 위치 초기화.
            for(int j = 0; j < 3; j++) { //all archers shoot
                visited = new boolean[n][m]; //방문초기화.
                q.clear();
                q.offer(new int[]{i, archers[j], 1});
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    if(tmp[cur[0]][cur[1]] == 1){ //현재위치에 있으면 gonnadie 표시하고 탈출.
                        gonnaDie[cur[0]][cur[1]] = true;
                        break;
                    }
                    if(cur[2] == d) continue; //최대거리까지 왔으면 탐색종료
                    for(int dir = 0; dir <3; dir++) {//다음위치탐색. 좌상우
                        int ny = cur[0] + dy[dir];
                        int nx = cur[1] + dx[dir];
                        //인덱스범위 안이고 방문한적없으면 탐색
                        if(ny >= 0 && nx >= 0 && ny < n && nx < m  &&!visited[ny][nx]){
                            visited[ny][nx] = true;
                            q.offer(new int[]{ny, nx, cur[2] + 1});
                        }
                    }
                }
            }

            //제거한 적 세기
            for(int y = 0; y < n; y++){
                for(int x = 0; x < m; x++){
                    if(gonnaDie[y][x]){
                        killCnt++;
                        tmp[y][x] = 0;
                    }
                }
            }
        }
        //maxKill와 비교
        if(maxKill < killCnt) maxKill = killCnt;
    }
}
