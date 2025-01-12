package JavaPrograms;
import java.io.*;

//interactive problem on Codeforces (didn't work)
/*
Question - https://codeforces.com/contest/1520/problem/F1
Still working on it.
*/

public class GuessTheKthZero {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int N = (read.read() + "").length(), t = read.read(), k = read.read();
        solve(N, read, k);
        System.out.flush();
    }
    public static void solve(int N, BufferedReader read, int k) throws IOException {
        int high = N-1, low = 0;
        while (low < high){
            int mid = (low + high)/2;
            System.out.println("? " + (low + 1) + " " + (mid + 1));
            int sum = read.read();
            int numZeros = mid - low + 1 - sum;
            if (numZeros < k){ low = mid + 1; k -= numZeros; }
            else high = mid;
        }
        System.out.print("! " + (low + 1));
    }
}
