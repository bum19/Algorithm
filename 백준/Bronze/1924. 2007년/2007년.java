import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int mon = sc.nextInt();
        int day = sc.nextInt();
        int totaldays = day-1;
        int[] daysInMonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        String[] dayOfWeek = {"MON","TUE","WED","THU","FRI","SAT","SUN"};

        for(int i = 1; i < mon; i++ ){
            totaldays += daysInMonth[i];
        }

        System.out.println(dayOfWeek[totaldays%7]);
    }
}