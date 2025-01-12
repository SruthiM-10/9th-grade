package USACOPrograms;
import java.io.*;
import java.util.TreeMap;

//USACO 2020 US Open Contest, Silver (9 out of 10 passed)
// Question - https://usaco.org/index.php?page=viewproblem2&cpid=1038
/*
INPUT FORMAT (file socdist.in):
The first line of input contains N and M. The next M lines each describe an interval in terms of two integers a and b, where 0≤a≤b≤1018. 
No two intervals overlap or touch at their endpoints. A cow standing on the endpoint of an interval counts as standing on grass.

OUTPUT FORMAT (file socdist.out):
Print the largest possible value of D such that all pairs of cows are D units apart. A solution with D>0 is guaranteed to exist.
*/

public class SocialDistancing{
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new FileReader("socdist.in"));
        BufferedWriter write = new BufferedWriter(new FileWriter("socdist.out"));
        String s = read.readLine(); String[] arr = s.split(" ");
        int N = Integer.parseInt(arr[0]), M = Integer.parseInt(arr[1]);
        TreeMap<Long, Long> intervals = new TreeMap<>();
        for (int i = 0; i < M; i ++){
            s = read.readLine(); arr = s.split(" ");
            intervals.put(Long.parseLong(arr[0]), Long.parseLong(arr[1]));
        }

        long low = 1;
        long high = (intervals.lastEntry().getValue() - intervals.firstKey() + 1)/N + 1;
        while (low < high){
            long mid = (low + high + 1)/2;
            if (check(mid, intervals, N))
                low = mid;
            else
                high = mid - 1;
        }
        write.write(high + "");
        write.close();
    }
    static boolean check(long D, TreeMap<Long, Long> intervals, int N){
        long pos = intervals.firstKey();
        for (int cow = 1; cow < N; cow ++){
            long key = intervals.floorKey(pos + D);
            if (pos + D <= intervals.get(key)) pos += D;
            else if (intervals.higherKey(key) != null) pos = intervals.higherKey(key);
            else return false;
        }
        return true;
    }
}
