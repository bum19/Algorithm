import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

//수열 묶어서 곱으로 만들어서 최대합 구하기.
// 음수는 짝수일경우 묶을것.
// 음수가 홀수일경우 하나는 남김 or 0이랑 묶을것.
// 그외는 큰수끼리 묶기.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //음수 받을 큐
        PriorityQueue<Integer> minusNums = new PriorityQueue<>();
        //양수 받을 큐. 큰수가 우선순위 높게 설정.
        PriorityQueue<Integer> plusNums = new PriorityQueue<>(Collections.reverseOrder());
        boolean zeroFlag = false;
        int N,total=0;
        N = sc.nextInt();

        for(int i = 0; i< N; i++){
            int tmp = sc.nextInt();
            if(tmp == 0)
                zeroFlag = true;
            else if(tmp > 0) plusNums.add(tmp);
            else minusNums.add(tmp);
        }

        //양수는 큰것끼리 먼저 곱하기.
        while(!plusNums.isEmpty()){
            int tmp1 = plusNums.poll();
            if(plusNums.size() > 0){
                if(plusNums.peek() == 1)
                    total += tmp1+plusNums.poll();

                else total += tmp1*plusNums.poll();
            }
            else total += tmp1;
        }

        //음수는 작은것끼리 먼저 곱하기
        while(!minusNums.isEmpty()){
            int tmp1 = minusNums.poll();
            if(minusNums.size() > 0 ) total += tmp1*minusNums.poll();
            else if(!zeroFlag) total += tmp1;
        }
        System.out.println(total);
    }
}

