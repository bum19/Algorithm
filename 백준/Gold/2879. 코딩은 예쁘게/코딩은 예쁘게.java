/*
 * Greedy : 탭을 추가/삭제에따라 줄을 연속으로 묶어서 진행, 가장 차이가 큰 줄의 값만큼 더하면서 진행
 * -> gap 0일경우 그룹에 묶이면 안됨
 * 매편집마다 그룹을 정하고(연속된 추가나 삭제가 필요한 줄), 편집횟수를 1증가하면서 진행
 */
import java.io.*;
import java.util.*;
public class Main {
	
	public static class Data{
		int gap;
		boolean isAdd; //더할지 뺄지 여부
	}
	
	public static int n,answer;
	public static Data[] datas;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine().trim());
		datas = new Data[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			datas[i] = new Data();
			datas[i].gap = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			datas[i].gap -= Integer.parseInt(st.nextToken());
			if(datas[i].gap <= 0) {
				datas[i].isAdd = true;
				datas[i].gap *= -1;
			}
		}
		
		boolean gapLeft = true; //실제 인덴트와 차이가 나는 줄이 존재하는지 여부
		while(gapLeft) {
			gapLeft = makeGroupAndEdit();
		}
		
		System.out.println(answer);
	}
	
	private static boolean makeGroupAndEdit() {
		int start = -1;
		int end = n-1;
		boolean status = true;
		
		//makeGroup
		for(int i = 0; i <n ; i++) {
			if(datas[i].gap != 0) {
				start = i;
				status = datas[i].isAdd;
				break;
			}
		}
		
		if(start == -1) {
			return false;
		}
		
		for(int i = start+1; i < n; i++) {
			if(datas[i].isAdd != status || datas[i].gap == 0) {
				end = i-1;
				break;
			}
		}
		
		//Edit
		for(int i = start; i <= end; i++) {
			datas[i].gap--;
		}
		answer++;
		
		return true;
	}
}
