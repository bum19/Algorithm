import java.util.*;
class Solution {
    public static int answer;
    public static boolean isPossible;
    public static int[] combSelect;
    public static int[] permSelect;
    public static boolean[] isSelected;
    public static String[] user_id;
    public static String[] banned_id;
    
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        combSelect = new int[banned_id.length];
        permSelect = new int[banned_id.length];
        isSelected = new boolean[combSelect.length];
        comb(0, 0, user_id.length, banned_id.length);
        
        return answer;
    }
    
    public static void comb(int depth, int start, int n, int m){
        if(depth == m){ //m개 골랐으면
            isPossible = false;
            perm(0, m); //m개 순열 돌린 후 맞는거 있는지 확인.
            return;
        }
        
        for(int i = start; i < n; i++){
            combSelect[depth] = i;
            comb(depth+1, i+1, n, m);
        }
    }
    
    public static void perm(int depth, int m){ //고른애들 순열
        if(isPossible) return;//이미 맞다는걸 확인했으면 탈출
        if(depth == m){
            check();
            return;
        }
        
        for(int i = 0; i < m; i++){
            if(!isSelected[i]){
                isSelected[i] = true;
                permSelect[depth] = combSelect[i];
                perm(depth+1, m);
                isSelected[i] = false;
            }
        }
    }
    
    public static void check(){// fit한지 체크.
        for(int i = 0; i < banned_id.length; i++){
            //글자 개수맞는지체크
            if(user_id[permSelect[i]].length() != banned_id[i].length()) return;
            //하나씩 fit한지 체크
            for(int j = 0; j < banned_id[i].length(); j++){
                if(banned_id[i].charAt(j) != '*' && user_id[permSelect[i]].charAt(j) != banned_id[i].charAt(j))
                    return; //제재목록쪽에 별이 아닌데 서로 다른 글자면 fit하지않음. 
            }
        }
        
        //fit하므로 true로 바꾸고 answer 증가.
        //m개고른게 가능한 경우의수라면 answer 증가.
        isPossible = true;
        answer++;
    }
}