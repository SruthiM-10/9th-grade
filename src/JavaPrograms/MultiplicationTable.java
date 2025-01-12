package JavaPrograms;
import java.io.*;

// (CSES) USACO Guide Silver Binary Search Problem
/*
Find the middle element when the numbers in an n * n multiplication table are sorted in increasing order. 
It is assumed that n is odd.
For example, the 3 * 3 multiplication table is as follows:
1 2 3
2 4 6
3 6 9
The numbers in increasing order are [1,2,2,3,3,4,6,6,9], so the answer is 3.
Input
The only input line has an integer n.
Output
Print one integer: the answer to the task.
*/

public class MultiplicationTable {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        long n = Integer.parseInt(read.readLine());
        long low = 1, high = n*n, mid, leq;

        while (low < high){
            mid = (low + high)/2;
            leq = 0;
            for (int i = 1; i <= n; i ++) { leq += Math.min(n, mid / i); }
            // how many multiples of that row are less than mid added over all rows determines number of numbers less than mid.
            if (leq >= (n * n + 1)/2) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(high);
    }
}
