/*
 왼쪽계란들고 모든 계란 쳐보는 경우의 수 탐색
 최대  O(7^7)
*/

import java.io.*;
import java.util.*;

public class Main{
    public static Egg[] eggs;
    public static int n, answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine().trim());
        eggs = new Egg[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(s,w);
        }

        answer = Integer.MIN_VALUE;
        recur(0);
        System.out.println(answer);
    }

    private static void recur(int depth){
        if(depth == n){
            //깬 계란 세기
            int cnt = 0;
            for(int i = 0; i < n; i++){
                if(eggs[i].s <= 0){
                    cnt++;
                }
            }
            answer = Math.max(cnt, answer);
            return;
        }

        //계란 안 치고 넘어가기
        if(eggs[depth].s <= 0){
            recur(depth+1);
            return;
        }
        //계란 치기. 총 7가지 경우의 수
        //만약 깨진계란없으면
        boolean isCracked = false;
        for(int i = 0; i < n; i++){
            if(i == depth || eggs[i].s <= 0) continue;
            isCracked = true;
            //계란부딪히기
            eggs[depth].s -= eggs[i].w;
            eggs[i].s -= eggs[depth].w;
            recur(depth+1);
            //복구
            eggs[depth].s += eggs[i].w;
            eggs[i].s += eggs[depth].w;
        }
        if(!isCracked)
            recur(depth+1);
    }

    public static class Egg{
        int s, w;
        public Egg(int s, int w){
            this.s = s;
            this.w = w;
        }
    }
}