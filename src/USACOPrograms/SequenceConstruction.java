package USACOPrograms;
import java.util.*;

//USACO 2025 US Open Contest, Silver
/*
Lately, the cows on Farmer John's farm have been infatuated with watching the show Apothecowry Dairies. The show revolves around a clever bovine sleuth CowCow solving problems of various kinds.
Bessie found a new problem from the show, but the solution won't be revealed until the next episode in a week! Please solve the problem for her.

You are given integers M and K (1≤M≤10^9,1≤K≤31). Please choose a positive integer N and construct a sequence a of N non-negative integers such that the following conditions are satisfied:
1≤N≤100
a1+a2+⋯+aN=M
popcount(a1)⊕ popcount(a2)⊕⋯⊕ popcount(aN)=K
If no such sequence exists, print −1.
† popcount(x) is the number of bits equal to 1 in the binary representation of the integer x. For instance, the popcount of 11 is 3 and the popcount of 16 is 1.

†⊕ is the bitwise xor operator.

The input will consist of T (1≤T≤5⋅10^3) independent test cases.

INPUT FORMAT (input arrives from the terminal / stdin):
The first line contains T.
The first and only line of each test case has M and K.

It is guaranteed that all test cases are unique.

OUTPUT FORMAT (print output to the terminal / stdout):
Output the solutions for T test cases as follows:

If no answer exists, the only line for that test case should be −1.

Otherwise, the first line for that test case should be a single integer N, the length of the sequence -- (1≤N≤100).

The second line for that test case should contain N space-separated integers that satisfy the conditions -- (0≤a_i≤M).
 */

public class SequenceConstruction {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int i = 0; i < T; i ++){
            solve(scan);
        }
    }
    public static void solve(Scanner scan){
        int m = scan.nextInt(), k = scan.nextInt();
        String s = Integer.toBinaryString(k);
        ArrayList<Integer> num = new ArrayList<>();

        long sum = 0;
        for (int i = 0; i < s.length(); i ++){
            if (s.charAt(i) == '1'){
                num.add((int) Math.pow(2, Math.pow(2, s.length() - 1 - i)) - 1);
                if (num.get(num.size() - 1) == 0){ num.remove(num.size() - 1); num.add(1); }
                sum += num.get(num.size() - 1);
            }
        }
        if (sum > m){ System.out.println("-1"); return; }
        if (sum < m){
            if ((m - sum) % 2 == 0){
                num.add((int) (m - sum)/2);
                num.add((int) (m - sum)/2);
            }
            else if (k % 2 == 0){
                num.add(1); num.add(2);
                num.add((int) (m - sum - 3)/2);
                num.add((int) (m - sum - 3)/2);
                if ((m - sum - 3)/2 < 0){ System.out.println("-1"); return; }
            }
            else {
                num.set(num.size() - 1, num.get(num.size() - 1) + 1);
                num.add((int) (m - sum - 1)/2);
                num.add((int) (m - sum - 1)/2);
                if ((m - sum - 1)/2 < 0){ System.out.println("-1"); return; }
            }
        }
        System.out.println(num.size());
        for (int i = 0; i < num.size() - 1; i ++){
            System.out.print(num.get(i) + " ");
        }
        System.out.println(num.get(num.size() - 1));
    }
}
