package JavaPrograms;
import java.io.*;
import java.util.*;

//Binary Search - USACO Guide - CodeChef
// correct answer, note be careful when using read.read (gives ascii value not the number and includes whenever there's a new line or a space :/)

public class TripTastic {
    static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<ArrayList<Long>> grid = new ArrayList<>();
    static ArrayList<ArrayList<Long>> pref = new ArrayList<>();

    static int N, M, K;
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(read.readLine());
        for (int i = 0; i < T; i ++){
            solve(read);
        }
    }
    public static void solve(BufferedReader read) throws IOException{
        String s = read.readLine();
        String[] arr = s.split(" ");
        N = Integer.parseInt(arr[0]); M = Integer.parseInt(arr[1]); K = Integer.parseInt(arr[2]);
        long total = 0;
        grid.clear();
        for (int i = 0; i < N; i ++){
            ArrayList<Long> row = new ArrayList<>();
            s = read.readLine();
            arr = s.split(" ");
            for (int j = 0; j < M; j ++){
                long a = Long.parseLong(arr[j]);
                total += a;
                row.add(a);
            }
            grid.add(row);
        }
        if (total < K + 1){ System.out.println(-1); return; }

        pref.clear();
        for (int i = 0; i < N; i ++){pref.add (new ArrayList<>()); }
        for (int i = 0; i < N; i ++){
            for (int j = 0; j < M; j ++) {
                pref.get(i).add(grid.get(i).get(j));
                if (i > 0) pref.get(i).set(j, pref.get(i).get(j) + pref.get(i - 1).get(j));
                if (j > 0) pref.get(i).set(j, pref.get(i).get(j) + pref.get(i).get(j - 1));
                if (i > 0 && j > 0) pref.get(i).set(j, pref.get(i).get(j) - pref.get(i - 1).get(j - 1));
            }
        }
        System.out.println(first_true(0, Math.max(N, M)));
    }
    public static long rangesum(int row1, int column1, int row2, int column2){
        if (row1 < 0) row1 = 0;
        if (row2 >= N) row2 = N - 1;
        if (column1 < 0) column1 = 0;
        if (column2 >= M) column2 = M - 1;

        long answer = 0;
        answer += pref.get(row2).get(column2);
        if (row1 > 0) answer -= pref.get(row1 - 1).get(column2);
        if (column1 > 0) answer -= pref.get(row2).get(column1 -1);
        if (row1 > 0 && column1 > 0) answer += pref.get(row1 - 1).get(column1 - 1);

        return answer;
    }
    public static boolean test(int distance){
        for (int i = 0; i < N; i ++){
            for (int j = 0; j < M; j ++){
                if (grid.get(i).get(j) == 0){
                    continue;
                }
                if (rangesum(i - distance, j - distance, i + distance, j + distance) >= (K + 1)){
                    return true;
                }
            }
        }
        return false;
    }
    public static int first_true(int lo, int hi){
        hi ++;
        while (lo < hi){
            int mid = lo + (hi - lo)/2;
            if (test(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
