import java.io.*;
import java.util.*;
//코테전 손풀기 문제
public class Main{
    //해쉬맵쓰기
    public static Map<String,Integer> map;
    public static int n,m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        map = new HashMap<String,Integer>();
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            map.put(st.nextToken(),i);    
        }
        
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            if(map.get(st.nextToken()) != null){
                sb.append(1).append("\n");
            }
            else
                sb.append(0).append("\n");
        }
        
        System.out.println(sb);
    }
}