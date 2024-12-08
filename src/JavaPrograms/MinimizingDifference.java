package JavaPrograms;
import java.io.*;
import java.util.*;

//USACO - Binary Search - Codeforces

public class MinimizingDifference {
    public static void main(String[] args) throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String s = sc.readLine(); String[] arr = s.split(" ");
        int n = Integer.parseInt(arr[0]);
        long k = Long.parseLong(arr[1]);

        long[] nums = new long[n];
        s = sc.readLine(); arr = s.split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(arr[i]);
        }

        // Sort the array
        Arrays.sort(nums);

        long[] diff = new long[n];
        for (int i = 1; i < n; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }

        int l = 1, r = n - 1, times = 1;
        long ans = nums[n - 1] - nums[0];

        // While we can still reduce the range and have remaining k
        while (l < r && k > 0) {
            long reduceBy = Math.min((diff[l] + diff[r]) * times, k);
            ans -= reduceBy / times;
            k -= reduceBy;
            l++;
            r--;
            times++;
        }

        // Handle the case where l == r and k > 0
        if (l == r && k > 0) {
            long reduceBy = Math.min(diff[r] * times, k); // diff[l] or diff[r] doesn't matter
            ans -= reduceBy / times;
            k -= reduceBy;
        }

        System.out.println(ans);
        sc.close();
    }
}
