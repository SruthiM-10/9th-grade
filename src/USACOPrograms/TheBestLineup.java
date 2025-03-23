package USACOPrograms;
import java.util.*;

/*
'FJ will construct another line b in the following manner:

Initially, b is empty.
While a is nonempty, remove the cow at the front of a and potentially add that cow to the back of b.
FJ wants to construct line b such that the sequence of labels in b from front to back is lexicographically greatest (see the footnote).

Before FJ constructs line b, he can perform the following operation at most once:
Choose a cow in line a and move it anywhere before its current position.

Given that FJ optimally performs the aforementioned operation at most once, output the lexicographically greatest label sequence of b he can achieve.
 */

public class TheBestLineup {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int t = 0; t < T; t ++){
            int N = scan.nextInt();
            int[] a = new int[N];

            // by number grouping..
            Map<Integer, TreeSet<Integer>> a_indices = new HashMap<>();
            for (int i = 0; i < N; i ++){
                int x = scan.nextInt();
                a[i] = x;
                if (!a_indices.containsKey(x)) a_indices.put(x, new TreeSet<>());
                a_indices.get(x).add(i);
            }

            int[] most_back = new int[N + 1]; // the maximum amount of distance that one number can go back

            int[] b = new int[N];

            int prev_num = -1;
            int pointer = 0;
            int[] movedIndexRange= new int[]{-1, -1};
            TreeSet<Integer> keyset = new TreeSet<>(a_indices.keySet());
            for (int number : keyset.descendingSet()){
                for (int index : a_indices.get(number)) {
                    if (index > movedIndexRange[0] && index < movedIndexRange[1]) index ++;
                    if (index > prev_num){
                        b[pointer] = number;
                        most_back[number] = prev_num + 1;
                        prev_num = index;
                        pointer ++;
                    }
                    else if (movedIndexRange[0] == - 1){
                        if (index + 1 > most_back[a[prev_num]]){
                            movedIndexRange[0] = most_back[a[prev_num]];
                            movedIndexRange[1] = prev_num; // in between this range now need to increase index by 1
                            b[pointer] = number;
                            most_back[number] = prev_num + 1;
                            prev_num = index;
                            pointer ++;
                        }
                    }
                }
            }
            for (int i = 0; i < N; i ++){
                if (b[i] == 0) break;
                if (i > 0) System.out.print(" " + b[i]);
                else System.out.print(b[i]);
            }
            if (t != T - 1) System.out.println();
        }
    }
}
