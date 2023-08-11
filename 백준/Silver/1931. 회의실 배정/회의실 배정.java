import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, start, end, currentEndTime = 0, meetingCount =0;
        PriorityQueue<int[]> meetings = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        n = sc.nextInt();

        for(int i =0; i<n; i++){
            start = sc.nextInt();
            end = sc.nextInt();
            meetings.add(new int[]{start,end});
        }

        while(!meetings.isEmpty()){
            int[] currentMeeting = meetings.poll();
            if(currentEndTime <= currentMeeting[0]){
                currentEndTime = currentMeeting[1];
                meetingCount++;
            }
        }
        System.out.println(meetingCount);
    }
}
