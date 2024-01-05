import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
//이동재 학우의 코드를 수정
public class Main {
    static class Node {
        int from, weight;

        public Node(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }
    }

    static int T, N, M, W;
    static ArrayList<Node> roads[];
    static final int INF = 100000000;
    static int dis[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] s = br.readLine().split(" ");
            N = Integer.parseInt(s[0]);
            M = Integer.parseInt(s[1]);
            W = Integer.parseInt(s[2]);

            roads = new ArrayList[N + 1];
            dis = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                roads[i] = new ArrayList<Node>();
            }

            for (int i = 0; i < M; i++) {
                s = br.readLine().split(" ");

                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int wei = Integer.parseInt(s[2]);

                roads[a].add(new Node(b, wei));
                roads[b].add(new Node(a, wei));

            }

            for (int i = 0; i < W; i++) {
                s = br.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int wei = Integer.parseInt(s[2]);
                roads[a].add(new Node(b, -wei));
            }
            boolean flag = false;


            if (bf()) {
                flag = true;
            }

            System.out.println(flag ? "YES" : "NO");
        }


    }


    //기존 BF에서 INF체크를 뺀 형태. 그냥 사이클만 확인하고, 단절그래프의 경우에도 확인할 수 있음.
    private static boolean bf() {
        //이게 한 정점에서 체크하는 경우임.
        Arrays.fill(dis, INF);
        for(int i = 1; i <=N; i++) { //최악의 경우 N-1번동안 갱신이 일어남.
            //모든 엣지순회해서 갱신
            for (int j = 1; j <= N; j++) {
                for (Node n : roads[j]) {
                    if (dis[n.from] > n.weight + dis[j]) {
                        dis[n.from] = dis[j] + n.weight;
                        //만약 N-1번 이후 또 갱신되는상황이있다? 음수임
                        if (i == N) return true;
                    }
                }
            }
        }
        return false;
    }
}