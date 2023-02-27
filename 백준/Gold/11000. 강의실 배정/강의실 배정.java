import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // Create a priority queue of classes sorted by their start times
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add the classes to the priority queue
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            pq.offer(new int[]{start, end});
        }

        // Create a priority queue of end times for each classroom
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        // Iterate over the classes and assign them to classrooms
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            if (!endTimes.isEmpty() && endTimes.peek() <= current[0]) {
                // If the next class starts after the current class ends, assign it to the same classroom
                endTimes.poll();
            }
            // Add the current class to a new classroom
            endTimes.offer(current[1]);
        }

        // Print the minimum number of classrooms required
        System.out.println(endTimes.size());
    }
}
