package ACSLPrograms;
import java.util.*;

//ACSL 2009 Practice P4 (USACO Guide - Graph Traversal)
// Question Link: https://dmoj.ca/problem/acsl1p4
/*
Input Specification:
The first line contains 2 integers N (2 <= N <= 20) and K (1 <= K <= 30) in this order, where N is the number of players and K the number of games played.
The players are identified by the integers 1, 2, ..., N.
There are K lines after the first line.
Each of the K lines contains 4 integers [a b s_a s_b] representing the result of a game: a and b are the identifiers of the players, and s_a and s_b are the score of players a and b respectively.
All scores are non-negative integers less than 10, and the players iwth the larger score wins.

Output Specification:
The output contains an integer which is the number of players whose ranking cannot be determined due to cyclic ordering.
 */

public class Rank {
    static ArrayList<ArrayList<Integer>> adj;
    static TreeSet<Integer> totCyclicPlayers;
    static boolean[] visited;
    static ArrayList<Integer> runningSet;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();

        adj = new ArrayList<>();
        for (int i = 0; i < n; i ++) adj.add(new ArrayList<>());
        for (int i = 0; i < k; i ++){
            int a = sc.nextInt() - 1, b = sc.nextInt() - 1, s_a = sc.nextInt(), s_b = sc.nextInt();
            if (s_a > s_b) adj.get(a).add(b);
            else adj.get(b).add(a);
        }

        totCyclicPlayers = new TreeSet<>();
        visited = new boolean[n];
        runningSet = new ArrayList<>();
        for (int i = 0; i < n; i ++){
            runningSet = new ArrayList<>();
            if (totCyclicPlayers.contains(i)) continue;
            dfs(i);
        }
        System.out.println(totCyclicPlayers.size());
    }
    public static void dfs(int node){
        if (runningSet.contains(node)){
            int index = runningSet.indexOf(node);
            for (int i = index; i < runningSet.size(); i ++){
                totCyclicPlayers.add(runningSet.get(i));
            }
            return;
        }
        runningSet.add(node);
        for (int neighbor : adj.get(node)){ dfs(neighbor); }
        runningSet.remove( (Integer) node);
    }
}
