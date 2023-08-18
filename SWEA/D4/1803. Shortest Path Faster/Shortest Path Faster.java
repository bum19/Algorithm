//실행시간  :
//메모리   :
import java.io.*;
import java.util.*;
 //인접리스트와 우선순위 큐를 활용한 다익스트라 구현 문제
public class Solution {
    public static final long INF = Long.MAX_VALUE; //최대 경로는 10만*100만
    public static int t, n, m, start, end;
    public static long[] dist;	//시작 정점에서 각 인덱스가 가르키는 정점까지의 거리 배열
    public static List<DistIdx>[] w; //인접리스트 선언. 각 원소는 리스트인덱스와 연결된 정점, 해당 연결 간선의 길이를 저장
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        t = Integer.parseInt(br.readLine().trim());
 
        for (int test_case = 1; test_case <= t; test_case++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
 
            // 초기화
            dist = new long[n + 1]; // 노드번호와 인덱스를 맞추기위해 1개 더 크게 설정.
            w = new List[n + 1]; // 컬렉션 배열 선언시 타입은 정하지않네요.
 
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int idx1, idx2;
                long length;
 
                idx1 = Integer.parseInt(st.nextToken());// 시작점
                idx2 = Integer.parseInt(st.nextToken());// 끝점
                length = Long.parseLong(st.nextToken());// 간선길이
 
                if (w[idx1] == null) {	// 해당 인덱스에 간선추가된적 없으면 추가.
                    w[idx1] = new ArrayList<DistIdx>();
                }
                if (w[idx2] == null) {	// 해당 인덱스에 간선추가된적 없으면 추가.
                    w[idx2] = new ArrayList<DistIdx>();
                }
                w[idx1].add(new DistIdx(length, idx2));
                w[idx2].add(new DistIdx(length, idx1));
            }
            // 입력끝
             
            dijk_pq(start);	//다익스트라 시작.
            sb.append("#").append(test_case).append(" ").append(dist[end]).append("\n");
        }
        System.out.println(sb);
    }
 
    public static void dijk_pq(int start) {
        DistIdx current;
        int curNode = -1;
        long curDist = -1;
        PriorityQueue<DistIdx> pq = new PriorityQueue<DistIdx>((a1, a2) ->{ //우선순위는 거리짧은 순.
            return (int)(a1.distance - a2.distance);
        });
         
        Arrays.fill(dist, INF); // distance arrays.fill로 초기화
        dist[start] = 0; // 시작점 0으로.
        pq.add(new DistIdx(dist[start], start)); //pq에 (0, 시작점) 추가
         
        while (!pq.isEmpty()) {	//pq가 빌 때 까지 반복. 모든 간선이 추가된다고 가정하면, 큐에 최대 간선 개수만큼 원소가 들어갈 수 있다.
            current = pq.poll();
            curDist = current.distance;
            curNode = current.index;
             
            if(dist[curNode] != curDist) continue; //뽑은 정점과 거리가 dist배열 내용과 다르면, 최신 갱신되기 전 내용이니 신경쓰지 않는다.
             
            for(DistIdx nxt :  w[curNode]) {	//현재 pq에써 뽑은 정점 중
                if(dist[nxt.index] <= dist[curNode] + nxt.distance) continue; //해당 정점까지의 거리 + 해당정점과 연결된 정점의 길이 >= 연결된 정점의 현재 거리면 아무것도 하지 않는다. 
                 
                dist[nxt.index] = dist[curNode] + nxt.distance;	//해당 정점까지의 거리 + 해당정점과 연결된 정점의 길이 < 연결된 정점의 현재 거리면 dist테이블 갱신 하고
                pq.add(new DistIdx(dist[nxt.index], nxt.index));//갱신한 내용을 우선순위 큐에 넣어준다.
            }
        }
         
         
    }
    static class DistIdx{ //long형 거리, int형 정점 인덱스
        long distance;
        int index;
         
        public DistIdx(long distance, int index) { //long형 거리, int형 정점 인덱스로 DistIdx객체 생성.
            super();
            this.distance = distance;
            this.index = index;
        }
         
    }
}