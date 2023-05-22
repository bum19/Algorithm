
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int[][] board;
    public static boolean[][] virVisited;
    public static ArrayList<Pair> safePart = new ArrayList<Pair>();
    public static ArrayList<Pair> virPoint = new ArrayList<Pair>();

    public static int n,m,maxCount=-1;
    public static int[] dy = {0, 0, -1, 1};
    public static int[] dx = {1, -1, 0, 0};
    public static void virSpread(){
        virVisited = new boolean[n][m];
        for(Pair vir : virPoint){
            dfs(vir.first, vir.second);
        }
    }

    public static void dfs(int y, int x){
        virVisited[y][x] = true;
        for(int i =0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny >= n || nx < 0 || nx >=m) continue;
            if(board[ny][nx] == 1 || virVisited[ny][nx]) continue;
            dfs(ny, nx);
        }
    }

    public static void countSafePoint() {
        int cnt=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] ==0 && !virVisited[i][j] ) cnt++;
            }
        }
        if(cnt > maxCount) maxCount = cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        board = new int[n][m];
        for(int i =0 ; i< n; i++){
            for(int j =0; j< m; j++){
                board[i][j] = sc.nextInt();
                if(board[i][j] == 0) safePart.add(new Pair(i,j));
                if(board[i][j] == 2) virPoint.add(new Pair(i,j));

            }
        }
        int l = safePart.size();
        for(int i = 0; i< l; i++){
            for(int j = 0; j<i; j++){
                for(int k =0 ; k<j; k++){
                    board[safePart.get(i).first][safePart.get(i).second] = 1;
                    board[safePart.get(j).first][safePart.get(j).second] = 1;
                    board[safePart.get(k).first][safePart.get(k).second] = 1;
                    virSpread();
                    countSafePoint();
                    board[safePart.get(i).first][safePart.get(i).second] = 0;
                    board[safePart.get(j).first][safePart.get(j).second] = 0;
                    board[safePart.get(k).first][safePart.get(k).second] = 0;
                }
            }
        }

        System.out.println(maxCount);
    }
}
class Pair{
    int first;
    int second;
    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}