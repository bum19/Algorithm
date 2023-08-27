import java.io.*;
import java.util.*;

public class Solution {
    public static int t,h,w,n, sx, sy;
    public static char[][] map;
    public static char[] commands;
    public static int[] dy = {-1,1,0,0}; //상하좌우
    public static int[] dx = {0,0,-1,1}; //상하좌우
    public static char[] tankShapes = { '^', 'v', '<', '>'}; //게임판에 탱크 적을때
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= t; test_case++){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];

            for(int i = 0; i < h; i++){ //게임판 입력
                String str = br.readLine().trim();
                for(int j = 0; j < w; j++){
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>'){
                        sy = i;
                        sx = j;
                    }
                }
            }

            //명령입력
            n = Integer.parseInt(br.readLine().trim());
            commands = new char[n];
            String str = br.readLine().trim();
            for(int i = 0; i < n; i++){
                commands[i] = str.charAt(i);
            }

            playGame();

            sb.append("#").append(test_case).append(" ");
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void playGame(){
        //초기 위치 및 방향설정.
        int y = sy;
        int x = sx;
        int dir;
        if(map[sy][sx] == '^')      dir = 0;
        else if(map[sy][sx] == 'v') dir = 1;
        else if(map[sy][sx] == '<') dir = 2;
        else                        dir = 3;

        for(int i = 0; i < n; i++){
            if(commands[i] == 'S'){ //발사
                shoot(y,x, dir);
            }
            else{
                //방향전환
                if(commands[i] == 'U') {
                    map[y][x] = tankShapes[0];
                    dir = 0;
                }
                else if(commands[i] == 'D'){
                    map[y][x] = tankShapes[1];
                    dir = 1;
                }
                else if(commands[i] == 'L'){
                    map[y][x] = tankShapes[2];
                    dir = 2;
                }
                else if(commands[i] == 'R'){
                    map[y][x] = tankShapes[3];
                    dir = 3;
                }

                //이동
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if(ny < 0 || nx < 0 || ny >= h || nx >= w || map[ny][nx] != '.') continue; //벗어나거나 평지아니면 이동은 하지않는다.
                map[y][x] = '.'; //이동
                y = ny; //이동
                x = nx; //이동
                map[y][x] = tankShapes[dir];
            }
        } //움직임 종료. 게임끝
    }

    private static void shoot(int y, int x, int dir){
        //첫 발사 도달지 설정
        y = y + dy[dir];
        x = x + dx[dir];
        while(true){
            if(y < 0 || x < 0 || y >= h || x >= w || map[y][x] == '#') break; //인덱스벗어나거나 강철벽 만나면 종료

            if(map[y][x] == '*'){ //벽돌벽만나면 부시고 종료
                map[y][x] = '.';
                break;
            }

            y = y + dy[dir];
            x = x + dx[dir];
        }
    }
}
