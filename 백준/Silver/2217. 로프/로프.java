import java.io.*;
import java.util.*;

public class Main {
    public static int n, maxWeight;
    public static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine().trim());
        pq = new PriorityQueue<>();
        maxWeight = Integer.MIN_VALUE;
        for(int i = 0; i < n ; i++){
            pq.add(Integer.parseInt(br.readLine().trim()));
        }

        for(int i = n; i >= 1; i--){
            int tmp = pq.poll();
            if(maxWeight < tmp * i)
                maxWeight = tmp * i;
        }

        System.out.println(maxWeight);
    }
}
