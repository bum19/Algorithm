import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        long answer = 0;
        for(int i = 0; i < 3; i++) answer += Long.parseLong(st.nextToken());
        System.out.println(answer);
    }
}