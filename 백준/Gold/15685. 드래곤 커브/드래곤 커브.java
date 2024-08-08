/*
	커브가 지나간 점 저장.
	시작점움직인게 항상 끝점.
	이동하면서 이동한 edge를 인접리스트로 저장한다. > 10000* 10000이라 너무 많은데? ->  각 좌표별 행*100 + 열로 만들고, 인접리스트 생성
	문제 잘못봄. 그냥 visited처리하면 됨.
	문제 잘못 이해함. 커브만들려면 매번 점들을 끝점기준 90도회전해야함.
    x,y좌표 총 0~100까지 가능함에 유의.
*/
import java.io.*;
import java.util.*;

public class Main{
	public static final int SIZE = 101;
    public static int n,x,y,d,g,cnt;
    public static boolean[][] visited;
    public static List<int[]> points;
    public static int[] dy = {0,-1,0,1}; //우상좌하
    public static int[] dx = {1,0,-1,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine().trim());
        visited = new boolean[SIZE][SIZE];
        for(int i = 0; i <n; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            //dragon curve
            curve(x,y,d,g);
        }//curve end

        //count
        count();

        System.out.println(cnt);
    }

    private static void curve(int x, int y, int d, int g){
        points = new ArrayList<>();
        //init 0세대 커브 만들기
        //시작점 방문처리
        visited[y][x] = true;
        points.add(new int[]{y,x});

        int endY = y + dy[d];
        int endX = x + dx[d];
        //방문처리
        if(endY >= 0 && endX >= 0 && endY < SIZE && endX < SIZE) {
            visited[endY][endX] = true;
        }
        points.add(new int[]{endY,endX});

        int cnt = 0;
        for(int i  = 1; i <= g; i++){
            int pointLen = points.size();

            for(int j = pointLen-2;  j >=0 ; j--){
                int[] cur = points.get(j);
                int dy = cur[0] - endY;
                int dx = cur[1] - endX;
                int ny = endY + dx;
                int nx = endX - dy;
                if(j == 0){
                    endY = ny;
                    endX = nx;
                }
                //방문처리
                if(ny >= 0 && nx >= 0 && ny <SIZE && nx <SIZE){
                    visited[ny][nx] = true;
                }
                //정점 추가
                points.add(new int[]{ny,nx});
            }
        }
    }

    private static void count(){
        for(int y = 0; y < SIZE-1; y++){
            for(int x = 0; x <SIZE-1; x++){
                //(y,x)를 왼쪽위 점으로 하는 칸이
                //4개 변 확인
                if(check(y,x) && check(y+1,x) && check(y,x+1) && check(y+1,x+1)) {
                    cnt++;
                }
            }
        }
    }

    private static boolean check(int y, int x){
        if(y <0 || x <0 || y>=SIZE || x>=SIZE || !visited[y][x]) return false;
        return true;
    }
}