package JavaPrograms;
import java.io.*;
import java.util.*;

//USACO - Binary Search - Codeforces
// Question - https://codeforces.com/contest/1244/problem/E
/*
You are given a sequence a1,a2,…,an consisting of n integers.
You may perform the following operation on this sequence: choose any element and either increase or decrease it by one.
Calculate the minimum possible difference between the maximum element and the minimum element in the sequence, if you can perform the aforementioned operation no more than k times.

Input
The first line contains two integers n and k (2≤n≤105,1≤k≤1014) — the number of elements in the sequence and the maximum number of times you can perform the operation, respectively.
The second line contains a sequence of integers a1,a2,…,an (1≤ai≤109).

Output
Print the minimum possible difference between the maximum element and the minimum element in the sequence, if you can perform the aforementioned operation no more than k times.
*/

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
