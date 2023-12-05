import java.io.*;
import java.util.*;

public class Main {
    public static int n, sum;
    public static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine().trim());
        sum = 0;
        pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            pq.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = n; i >= 1; i--){
            sum += pq.poll() * i;
        }

        System.out.println(sum);

    }
}
