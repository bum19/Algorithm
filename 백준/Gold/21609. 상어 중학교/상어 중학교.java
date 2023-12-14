import java.io.*;
import java.util.*;
//중력고려하는곳에서 디버깅
public class Main {
    
    public static int n,m,score;
    public static int[][] board;
    public static boolean[][] visited;
    public static boolean[][][] rainbowVisited; //i,j 좌표에k색깔 그룹을 찾는데 쓰였는지 확인.
    public static int[] dy = {-1,0,0,1}; //상좌우하. 가장작은행 먼저, 그다음 가장 작은 열 먼저 탐색.
    public static int[] dx = {0,-1,1,0}; //상좌우하
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        score = 0;
        
        while(true) {
            //가장 큰 블록그룹찾기
            BlockGroup bg = findBG();
            if(bg == null) break;
            
            //점수합산
            score += bg.points.size() * bg.points.size();
            
            //제거
            for(int i = 0; i < bg.points.size(); i++) {
                int[] cur = bg.points.get(i);
                board[cur[0]][cur[1]] = -2;
            }
            
            //중력
            for(int i = n-1; i >= 0; i--) {
                for(int j =  n-1; j >= 0; j--) {
                    if(board[i][j] <= -1) continue;
                    int tmp = board[i][j];
                    board[i][j] = -2;
                    board[gravity(i,j)][j] = tmp;    
                }
            }
            
            //회전
            rotate();
            
            //중력. 밑에서부터 진행
            for(int i = n-1; i >= 0; i--) {
                for(int j =  n-1; j >= 0; j--) {
                    if(board[i][j] <= -1) continue;
                    int tmp = board[i][j];
                    board[i][j] = -2;
                    board[gravity(i,j)][j] = tmp;    
                }
            }
        }
        
        System.out.println(score);
        
    }
    
    private static BlockGroup findBG() {
        BlockGroup maxBg = new BlockGroup();
        visited = new boolean[n][n];
        rainbowVisited = new boolean[n][n][m+1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j] || board[i][j] <= 0) continue;
                //현재위치부터 bg 구성
                BlockGroup tmpBg = makeBG(i, j);
                //비교및 갱신
                //1.사이즈 비교
                if(maxBg.points.size() < tmpBg.points.size()) maxBg = tmpBg;
                else if(maxBg.points.size() > tmpBg.points.size()) continue;
                //2.무지개수 비교
                else if(maxBg.rainbowCnt < tmpBg.rainbowCnt) maxBg = tmpBg;
                else if(maxBg.rainbowCnt > tmpBg.rainbowCnt) continue;
                //3.기준블록 행 비교
                else if(maxBg.by < tmpBg.by) maxBg = tmpBg;
                else if(maxBg.by > tmpBg.by) continue;
                //4.기준블록 열 비교
                else if(maxBg.bx < tmpBg.bx) maxBg = tmpBg;
                else if(maxBg.bx > tmpBg.bx) continue;
            }
        }
        if(maxBg.points.size() < 2)
            return null;
        else
            return maxBg;
    }
    
    private static BlockGroup makeBG(int y, int x) {
        BlockGroup bg = new BlockGroup();
        bg.by = y;
        bg.bx = x;
        bg.points.add(new int[] {y,x});
        
        Queue<int[]> q = new ArrayDeque<int[]>();
        int color = board[y][x];
        visited[y][x] = true;
        q.add(new int[] {y,x});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int dir = 0; dir < 4; dir++) {
                int ny = cur[0] + dy[dir];
                int nx = cur[1] + dx[dir];
                //범위벗어나거나 검은블록/빈칸일경우 탐색x
                if(ny < 0 || nx < 0 || ny >= n || nx >= n || board[ny][nx] <= -1) continue;
                //무지개일경우 같은색 방문한경우 탐색x
                if(board[ny][nx] == 0 && rainbowVisited[ny][nx][color]) continue;
                //비무지개일경우 같은색 방문한경우 탐색x
                if(board[ny][nx] != 0 && visited[ny][nx]) continue;
                //무지개일 경우 방문처리,무지개수 증가, 좌표추가
                if(board[ny][nx] == 0) {
                    rainbowVisited[ny][nx][color] = true;
                    bg.rainbowCnt++;
                    bg.points.add(new int[] {ny, nx});
                    q.add(new int[] {ny, nx});
                }
                //비무지개일 경우 방문처리, 좌표추가
                if(board[ny][nx] == color) {
                    visited[ny][nx] = true;
                    bg.points.add(new int[] {ny,nx});
                    q.add(new int[] {ny,nx});
                }
            }
            
        }
        
        return bg;
    }
    
    //gravity. drop y point.
    private static int gravity(int y, int x) {
        
        while(true) {
            int ny = y + 1;
            if(ny >= n || board[ny][x] != -2) break;
            y = ny;
        }
        
        return y;
    }
    
    //rotate. reverse clock side rotattion
    private static void rotate() {
        int[][] tmp = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <n ; j++) {
                tmp[i][j] = board[j][n-1-i];
            }
        }
        board = tmp;
    }
    
    static class BlockGroup{
        int rainbowCnt, by, bx;
        List<int[]> points = new ArrayList<int[]>();
    }
}