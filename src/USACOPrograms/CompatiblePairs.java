package USACOPrograms;
import java.util.*;

//USACO 2025 US Open Contest, Silver
/*
Deep in the countryside, Farmer John’s cows aren’t just ordinary farm animals—they are part of a clandestine bovine intelligence network.
Each cow carries an ID number, carefully assigned by the elite cow cryptographers. However, due to Farmer John's rather haphazard tagging system, some cows ended up with the same ID.

Farmer John noted that there are N 1≤N≤2⋅10^5) unique ID numbers, and for each unique ID d_i (0≤d_i≤10^9), there are n_i (1≤n_i≤10^9) cows who shared it.

The cows can only communicate in pairs, and their secret encryption method has one strict rule: two cows can only exchange information if they are not the same cow and the sum of their ID numbers equals either A
 or B
 (0≤A≤B≤2⋅109
). A cow can only engage in one conversation at a time (i.e., no cow can be part of more than one pair).

Farmer John would like to maximize the number of disjoint communication pairs to ensure the best information flow. Can you determine the largest number of conversations that can happen at once?

INPUT FORMAT (input arrives from the terminal / stdin):
The first line contains N
, A
, B
.
The next N
 lines each contain ni
 and di
. No two di
 are the same.

OUTPUT FORMAT (print output to the terminal / stdout):
The maximum number of disjoint communicating pairs that can be formed at the same time.
 */

public class CompatiblePairs {
    static TreeMap<Integer, Integer> singleEdge = new TreeMap<>();
    static TreeMap<Integer, Integer> ids = new TreeMap<>(); // id, number of cows
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int A = scan.nextInt(), B = scan.nextInt();
        if (A == B) B = -1;

        for (int i = 0; i < N; i ++){
            int cows = scan.nextInt(), id = scan.nextInt();
            ids.put(id, cows);
        }

        for (int id : ids.keySet()){
            checkIfSingle(A, B, id);
        }

        long ans = 0;
        while (!singleEdge.isEmpty()){
            int id = singleEdge.firstKey(), connections;
            if (singleEdge.get(id) - id == id) connections = ids.get(id)/2;
            else {
                connections = Math.min(ids.get(singleEdge.get(id) - id), ids.get(id));
                ids.put(singleEdge.get(id) - id, ids.get(singleEdge.get(id) - id) - connections);
            }
            ans += connections;
            ids.remove(id);
            checkIfSingle(A, B, singleEdge.get(id) - id);
            singleEdge.remove(id);
        }
        System.out.println(ans);
    }
    public static void checkIfSingle(int A, int B, int id){
        if (ids.containsKey(A - id) && !ids.containsKey(B - id) && !singleEdge.containsKey(A - id)){
            singleEdge.put(id, A);
        }
        if (!ids.containsKey(A - id) && ids.containsKey(B - id) && !singleEdge.containsKey(B - id)){
            singleEdge.put(id, B);
        }
    }
}
