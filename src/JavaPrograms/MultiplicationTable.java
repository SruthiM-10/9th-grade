package JavaPrograms;
import java.io.*;

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
