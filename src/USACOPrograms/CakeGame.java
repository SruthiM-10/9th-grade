package USACOPrograms;
import java.io.*;
import java.util.*;

//USACO 2024 December Contest, Silver
//Question Link: https://usaco.org/index.php?page=viewproblem2&cpid=1446
/*
Each cow wants to eat as much as possible. However, being very civilized cows, they decided to play a game to split it!
The game proceeds with both cows alternating turns. Each turn consists of one of the following:

Bessie chooses two adjacent cakes and stacks them, creating a new cake with the sum of the sizes.
Elsie chooses either the leftmost or rightmost cake and puts it in her stash.
When only one cake remains, Bessie eats it, and Elsie eats all cakes in her stash.
If both cows play optimally to maximize the amount of cake they eat and Bessie plays first, how much cake will each cow eat?
 */
/*
INPUT FORMAT (input arrives from the terminal / stdin):
Each input consists of T (1≤T≤10) independent test cases.
It is guaranteed that the sum of all N within an input does not exceed 10^6.
Each test case is formatted as follows. The first line contains N. The next line contains N space-separated integers, a1,a2,…,aN.

OUTPUT FORMAT (print output to the terminal / stdout):
For each test case, output a line containing b and e, representing the amounts of cake Bessie and Elsie respectively will consume if both cows play optimally.
 */

public class CakeGame {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(read.readLine());
        for (int i = 0; i < T; i ++){
            int N = Integer.parseInt(read.readLine());
            String s = read.readLine(); String[] arr = s.split(" ");
            ArrayList<Integer> cakes = new ArrayList<>();
            for (int l = 0; l < N; l ++) cakes.add(Integer.parseInt(arr[l]));

            long cake_elsie, cake_bessie;
            int num_elsie = cakes.size()/2 - 1; // number of cakes elsie eats

            long[] pref = new long[N];
            pref[0] = cakes.get(0);
            pref[N - 1] = cakes.get(N - 1);
            for (int e = 1; e < num_elsie + 1; e ++){
                pref[e] = pref[e - 1] + cakes.get(e);
            }
            for (int e = N - 2; e > num_elsie; e --){
                pref[e] = pref[e + 1] + cakes.get(e);
            }

            long total = pref[num_elsie] + pref[num_elsie + 1];
            long max = Math.max(pref[num_elsie - 1], pref[num_elsie + 2]);
            long[] pref_elsie = new long[num_elsie];
            for (int e = 0; e < num_elsie - 1; e ++){
                pref_elsie[e] = pref[e] + pref[N - num_elsie + 1 + e];
                if (pref_elsie[e] > max) max = pref_elsie[e];
            }
            cake_elsie = max; cake_bessie = total - max;
            System.out.println(cake_bessie + " " + cake_elsie);
        }
        read.close();
    }
}
