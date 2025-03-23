package USACOPrograms;
import java.util.*;

/*
There are 2 bales (a, b), with 1 <= a, b <= 10^18.
2 types of operations can be performed on them ->
            We can increase the first pile's size by the amount currently in the second pile.
     OR     We can increase the secodn pile's size by the amount currently in the first pile.
The end 2 bales (c, d) with 1 <= c, d <= 10^18 is also inputted.

Output the minimum number of operations needed to transform (a, b) -> (c, d), or if it is impossible to do so, output -1.
 */

public class TransformingPairs {
    public static long solve(Scanner scan){
        long a = scan.nextLong(), b = scan.nextLong(), c = scan.nextLong(), d = scan.nextLong();

       long operations = 0;
       while (a < c && b < d){
           if (c > d){
               long x = (c - 1)/d;
               operations += x;
               c -= d * x;
           }
           else if (d > c){
               long x = (d - 1)/c;
               operations += x;
               d -= c * x;
           }
           else break;
       }
       if (b == d && c >= a && ((c - a) % d) == 0) return operations + (c-a)/d;
       if (a == c && d >= b && ((d - b) % c) == 0) return operations + (d-b)/c;
       return -1;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int i = 0; i < T; i ++){
            System.out.println(solve(scan));
        }
    }
}
