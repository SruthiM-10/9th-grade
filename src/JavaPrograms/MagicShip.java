package JavaPrograms;
import java.io.*;

//USACO Guide - Binary Search (Codeforces)
// accepted
/*
Question: https://codeforces.com/problemset/problem/1117/C
*/

public class MagicShip {
    static long x1, x2, y1, y2;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String s = read.readLine();
        String[] arr = s.split(" ");
        x1 = Long.parseLong(arr[0]); y1 = Long.parseLong(arr[1]);
        s = read.readLine(); arr = s.split(" ");
        x2 = Long.parseLong(arr[0]); y2 = Long.parseLong(arr[1]);

        N = Integer.parseInt(read.readLine());
        String wind = read.readLine();
        long[] pref_x = new long[N];
        long[] pref_y = new long[N];
        for (int i = 0; i < N; i ++){
            if (i != 0){
                pref_x[i] = pref_x[i - 1];
                pref_y[i] = pref_y[i - 1];
            }
            if (wind.charAt(i) == 'U') pref_y[i] ++;
            if (wind.charAt(i) == 'D') pref_y[i] --;
            if (wind.charAt(i) == 'L') pref_x[i] --;
            if (wind.charAt(i) == 'R') pref_x[i] ++;
        }

        long low = 1, high = Long.MAX_VALUE / 2;
        while (low <= high){
            long mid = (low + high)/2;
            if (check(mid, pref_x, pref_y)) {
                if (low == high){
                    System.out.println(low);
                    break;
                }
                high = mid;
            }
            else {
                low = mid + 1;
                if (low > high) System.out.println(-1);
            }
        }
    }
    public static boolean check (long days, long[] pref_x, long[] pref_y){
        long new_x = x1 + pref_x[N - 1] * (days / N);
        if (days % N != 0) new_x += pref_x[(int) (days % N - 1)];
        long new_y = y1 + pref_y[N - 1] * (days / N);
        if (days % N != 0) new_y += pref_y[(int) (days % N - 1)];

        long diff = Math.abs(x2 - new_x) + Math.abs(y2 - new_y);
        return diff <= days;
    }
}
