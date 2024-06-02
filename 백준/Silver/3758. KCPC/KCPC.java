/*
 * 너무 쉬운데? 그냥 구현
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int T,n,k,t,m;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine().trim());
		for(int test_case =1 ; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			Team[] teams = new Team[n+1]; //index 0번째는 버린다.
			for(int i = 0; i <= n; i++) {
				teams[i] = new Team();
				teams[i].scores = new int[k+1];
				teams[i].id = i;
			}
			
			//입력
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int id = Integer.parseInt(st.nextToken());
				int pId =  Integer.parseInt(st.nextToken());
				int score = Integer.parseInt(st.nextToken());
				
				//문제별 점수 갱신
				if(teams[id].scores[pId] < score) {
					teams[id].scores[pId] = score;
				}
				teams[id].lastSubmit = i;
				teams[id].submitCnt++;
			}
			
			//최종점수 구하기
			for(int i = 0; i <= n; i++) {
				int total = 0;
				for(int j = 0; j <= k; j++) {
					total += teams[i].scores[j];
				}
				teams[i].totalScore= total;
			}
			
			//정렬
			Arrays.sort(teams, (t1, t2) ->{
				if(t1.totalScore != t2.totalScore) {
					return Integer.compare(t1.totalScore, t2.totalScore) * -1;
				}
				else if(t1.submitCnt != t2.submitCnt) {
				 	return Integer.compare(t1.submitCnt, t2.submitCnt);
				}
				else
					return Integer.compare(t1.lastSubmit, t2.lastSubmit);
			});
			
			
			//내 팀 순위찾기
			for(int idx = 0; idx <= n; idx++) {
				if(teams[idx].id == t) {
					sb.append(idx+1).append('\n');
					break;
				}
			}
		}
		
		System.out.println(sb);
	}
	
	public static class Team{
		public int[] scores;
		public int id;
		public int lastSubmit;
		public int submitCnt;
		public int totalScore;
		
		public String toString() {
			return Arrays.toString(scores) + ", id : "+id + ", lastSubmit :" + lastSubmit + ", submitCnt : "+ submitCnt+ ", totalScore" + totalScore;
		}
	}
}
