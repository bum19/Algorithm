//6개의 경우의수로 직사각형 자를수있음
import java.util.Scanner;

public class Main {
    public static long maxResult;
    public static int y,x;
    public static int[][] rectangle;
    public static void check(long a, long b, long c){
        maxResult = Math.max(maxResult, a*b*c);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        y= sc.nextInt();
        x= sc.nextInt();
        rectangle = new int[y][x];

        for(int j =0; j<y; j++){
            String tmp =sc.next();
            for(int i=0; i<x; i++){
                rectangle[j][i] = tmp.charAt(i)-'0';
            }
        }
        int a,b,c;
        //경우 1
        if(x>=3){
            for(int i=1; i<x-1; i++){
                for(int j=i+1; j<x;j++){
                    a=0;b=0;c=0;
                    //a
                    for(int ax=0; ax<i;ax++){
                        for(int ay=0;ay<y;ay++){
                            a += rectangle[ay][ax];
                        }
                    }
                    //b
                    for(int bx = i; bx < j; bx++){
                        for(int by =0 ; by<y; by++){
                            b += rectangle[by][bx];
                        }
                    }
                    //c
                    for(int cx =j; cx < x; cx++){
                        for(int cy =0; cy<y; cy++){
                            c += rectangle[cy][cx];
                        }
                    }
                    check(a,b,c);
                }
            }
        }
        //경우 2
        if(y>=3){
            for(int i=1; i<y-1; i++){
                for(int j=i+1; j<y;j++){
                    a=0;b=0;c=0;
                    //a
                    for(int ax=0; ax<x;ax++){
                        for(int ay=0;ay<i;ay++){
                            a += rectangle[ay][ax];
                        }
                    }
                    //b
                    for(int bx = 0; bx < x; bx++){
                        for(int by = i ; by< j; by++){
                            b += rectangle[by][bx];
                        }
                    }
                    //c
                    for(int cx =0; cx < x; cx++){
                        for(int cy =j; cy<y; cy++){
                            c += rectangle[cy][cx];
                        }
                    }
                    check(a,b,c);
                }
            }
        }

        //경우3,4,5,6
        if(x>=2 && y>=2) {
            for (int i = 1; i < x; i++) {
                for (int j = 1; j < y; j++) {
                    //경우3
                    a = 0;
                    b = 0;
                    c = 0;
                    //a
                    for (int ax = 0; ax < i; ax++) {
                        for (int ay = 0; ay < j; ay++) {
                            a += rectangle[ay][ax];
                        }
                    }
                    //b
                    for (int bx = i; bx < x; bx++) {
                        for (int by = 0; by < j; by++) {
                            b += rectangle[by][bx];
                        }
                    }
                    //c
                    for (int cx = 0; cx < x; cx++) {
                        for (int cy = j; cy < y; cy++) {
                            c += rectangle[cy][cx];
                        }
                    }
                    check(a, b, c);

                    //경우4
                    a = 0;
                    b = 0;
                    c = 0;
                    //a
                    for (int ax = 0; ax < i; ax++) {
                        for (int ay = j; ay < y; ay++) {
                            a += rectangle[ay][ax];
                        }
                    }
                    //b
                    for (int bx = i; bx < x; bx++) {
                        for (int by = j; by < y; by++) {
                            b += rectangle[by][bx];
                        }
                    }
                    //c
                    for (int cx = 0; cx < x; cx++) {
                        for (int cy = 0; cy < j; cy++) {
                            c += rectangle[cy][cx];
                        }
                    }
                    check(a, b, c);

                    //경우5
                    a = 0;
                    b = 0;
                    c = 0;
                    //a
                    for (int ax = 0; ax < i; ax++) {
                        for (int ay = 0; ay < j; ay++) {
                            a += rectangle[ay][ax];
                        }
                    }
                    //b
                    for (int bx = 0; bx < i; bx++) {
                        for (int by = j; by < y; by++) {
                            b += rectangle[by][bx];
                        }
                    }
                    //c
                    for (int cx = i; cx < x; cx++) {
                        for (int cy = 0; cy < y; cy++) {
                            c += rectangle[cy][cx];
                        }
                    }
                    check(a, b, c);

                    //경우6
                    a = 0;b = 0;c = 0;
                    //a
                    for (int ax = 0; ax < i; ax++) {
                        for (int ay = 0; ay < y; ay++) {
                            a += rectangle[ay][ax];
                        }
                    }
                    //b
                    for (int bx = i; bx < x; bx++) {
                        for (int by = 0; by < j; by++) {
                            b += rectangle[by][bx];
                        }
                    }
                    //c
                    for (int cx = i; cx < x; cx++) {
                        for (int cy = j; cy < y; cy++) {
                            c += rectangle[cy][cx];
                        }
                    }
                    check(a, b, c);
                }
            }
        }
        System.out.println(maxResult);
    }
}
