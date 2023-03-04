
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N, tmpStart, tmpEnd, answer;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        N = sc.nextInt();

        // Add the classes to the priority queue
        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            pq.offer(new int[]{start, end});
        }

        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        while(!pq.isEmpty()){
            int[] currentClass = pq.poll();
            if(!rooms.isEmpty() && currentClass[0] >= rooms.peek()){
                rooms.poll();
            }
            rooms.add(currentClass[1]);
        }
        System.out.println(rooms.size());
    }
}
