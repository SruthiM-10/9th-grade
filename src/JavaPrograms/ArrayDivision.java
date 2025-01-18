package JavaPrograms;
import java.io.*;

//(CSES) USACO Guide Silver Section "Binary Search
/*
You are given an array containing n positive integers.
Your task is to divide the array into k subarrays so that the maximum sum in a subarray is as small as possible.
Input
The first input line contains two integers n and k: the size of the array and the number of subarrays in the division.
The next line contains n integers x_1,x_2,...,x_n: the contents of the array.
Output
Print one integer: the maximum sum in a subarray in the optimal division.
*/

public class ArrayDivision {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String s = read.readLine(); String[] arr = s.split(" ");
        int N = Integer.parseInt(arr[0]), k = Integer.parseInt(arr[1]);
        int[] num = new int[N];
        s = read.readLine(); arr = s.split(" ");

        long max = 0, sum = 0;
        for (int i = 0; i < N; i ++){
            num[i] = Integer.parseInt(arr[i]);
            if (num[i] > max) max = num[i];
            sum += num[i];
        }

        long answer;
        long low = max, high = sum;
        while (low < high){
            long mid = (low + high)/2;
            long temp = check (mid, num, k);
            if (temp != 0) high = mid;
            else low = mid + 1;
        }

        answer = check(low, num, k);
        System.out.println(answer);
    }
    public static long check (long mid, int[] num, int k){
        long ans = num[0];
        long sum = num[0];
        int pos = 0, i;
        for (i = 0; i < k && pos < num.length - 1; i ++){
            do {
                pos ++;
                sum += num[pos];
                if (sum > mid){ sum -= num[pos]; pos --; break;}
            } while (pos < num.length - 1);
            ans = Math.max(ans, sum);
            sum = 0;
        }
        if (pos == num.length - 1) return ans;
        else return 0;
    }
}
