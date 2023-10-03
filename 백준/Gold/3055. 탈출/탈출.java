import java.io.*;
import java.util.*;
//물이동큐와 고슴도치큐를 따로두면 메모리초과. 같이 둬보자.
public class Main{

    public static int r, c, sx, sy; //티떱숲 가로세로크기, 고슴도치 시작위치
    public static char[][] area; // 티떱숲 지도 받기
//    public static boolean[][] visited; // 고슴도치 방문여부
    public static Queue<int[]> watersAndHog; //물, 고슴도치 위치 받는다. 0이면 물, 1이면 고슴도치
    public static int[] dy = {-1,0,1,0}; //상우하좌
    public static int[] dx = {0,1,0,-1}; //상우하좌
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        area = new char[r][c];
//        visited = new boolean[r][c];
        watersAndHog = new ArrayDeque<>();
        for(int i = 0 ; i < r; i++){
            String str = br.readLine().trim();
            for(int j = 0; j < c; j++) {
                area[i][j] = str.charAt(j);
                if(area[i][j] == 'S'){
                    sy = i; sx = j;
                }
                if(area[i][j] == '*'){
                    watersAndHog.add(new int[]{i,j,0});
                }
            }
        }
        watersAndHog.add(new int[]{sy,sx,1});

        int answer = getTime();
        if(answer == -1) System.out.println("KAKTUS");
        else System.out.println(answer);

    }

    private static int getTime(){
        int time = 1; //걸린 시간.
        int qSize = 0; //매 분마다 큐에 있는 위치만큼 이동하기위해 큐 사이즈가 필요함.

        while(!watersAndHog.isEmpty()) {
//            매번 현재상황출력
//            System.out.println("curStatus");
//            for(int i = 0; i < r; i++){
//                for(int j = 0; j <c; j++){
//                    System.out.print(area[i][j]);
//                }
//                System.out.println();
//            }
            //물 차는 로직
            qSize = watersAndHog.size(); //현재 큐에 들어있는 물위치
            for(int i = 0; i < qSize; i++){ //현재 물들이 퍼지는 로직.
                int[] cur = watersAndHog.poll();
                for(int dir = 0; dir < 4; dir++){
                    int ny = cur[0] + dy[dir];
                    int nx = cur[1] + dx[dir];
                    if(cur[2]== 0) { //현재 큐에서 뽑은게 물이라면
                        if (ny < 0 || nx < 0 || ny >= r || nx >= c || area[ny][nx] == '*' || area[ny][nx] == 'D' || area[ny][nx] == 'X')
                            continue;
                        area[ny][nx] = '*';
                        watersAndHog.offer(new int[]{ny, nx, 0});
                    }
                    else{ //현재 큐에서 뽑은게 고슴도치위치라면
                        if (ny < 0 || nx < 0 || ny >= r || nx >= c || area[ny][nx] == 'S' || area[ny][nx] == '*' || area[ny][nx] == 'X')
                            continue;
                        if (area[ny][nx] == 'D') return time;
                        area[ny][nx] = 'S';
                        watersAndHog.offer(new int[]{ny, nx, 1});
                    }
                }
            }
           time++;
        }
        return -1;
    }
}