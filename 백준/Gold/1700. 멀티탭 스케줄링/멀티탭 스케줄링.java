import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static ArrayList<Integer> multitab = new ArrayList<>();
    public static int[] seq;
    public static boolean[] itemPlugged;
    public static int n,k, answer = 0;
    static class LateItem {
        int item;
        int distance;

        public LateItem(int item, int distance) {
            this.item = item;
            this.distance = distance;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        seq = new int[k];
        itemPlugged = new boolean[k+1];

        for(int i =0; i<k; i++){
            seq[i] = sc.nextInt();
        }

        for(int i = 0; i< k; i++){
            //멀티탭에 안꽃혀있으면
            if(!itemPlugged[seq[i]]){
                //멀티탭 다 찼으면
                if(multitab.size() == n){
                    //멀티탭에 꽂힌애들 탐색
                    LateItem lateItem = new LateItem(-1,-1);

                    for (int item : multitab) {
                        //현재 seq부터 끝까지 item 누가더 늦게나오나 갱신하며 찾기
                        int distance = 0;
                        for(int j = i+1; j < k; j++){
                            if(item == seq[j]){
                                break;
                            }
                            distance++;
                        }
                        if( distance > lateItem.distance){
                            lateItem.item = item;
                            lateItem.distance = distance;
                        }
                    }
                    //결정된애 뽑고 answer 추가
                    multitab.remove(multitab.indexOf(lateItem.item));
                    multitab.add(seq[i]);

                    itemPlugged[lateItem.item] = false;
                    itemPlugged[seq[i]] = true;
                    answer++;
                }
                else{
                    multitab.add(seq[i]);
                    itemPlugged[seq[i]] = true;
                }
            }
        }

        System.out.println(answer);
    }
}
