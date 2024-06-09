import java.io.*;
import java.util.*;
/*
이진탐색인듯?
*/
public class Main {
    public static Data[] datas;
    public static int n,m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        datas = new Data[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            datas[i] = new Data(num, name);
        }

        Arrays.sort(datas, (d1,d2)->{
            return Integer.compare(d1.num, d2.num);
        });

        for(int i = 0; i < m; i++){
            int input = Integer.parseInt(br.readLine());
            //이진탐색..? 숫자를 넘는 가장 큰 수
            int sIdx = 0;
            int eIdx = n-1;
            int answerIdx = -1;

            //가장 작은 input보다 큰 값의 인덱스찾기.
            while(sIdx <= eIdx){
                int mIdx = (sIdx + eIdx)/2;
                //중간값이 input보다 크거나 같으면 일단 그걸로 놓고, 중간값 천장으로하기. 만약 중간값이 0이면 그대로 탈출
                if(input <= datas[mIdx].num){
                    answerIdx = mIdx;
                    eIdx = mIdx - 1;
                    if(mIdx == 0){
                        break;
                    }
                }
                //중간값이 input보다 작으면 중간값 바닥으로 하기. 만약 중간값이 n-1이면 그대로 탈출
                else{
                    sIdx = mIdx + 1;
                    if(mIdx == n-1){
                        answerIdx = n-1;
                        break;
                    }
                }
                
            }
            sb.append(datas[answerIdx].name).append('\n');
        }

        System.out.println(sb);
    }

    public static class Data{
        int num;
        String name;
        public Data(int num, String name){
            this.num= num;
            this.name = name;
        }

        public String toString(){
            return num+" ";
        }
    }
}
