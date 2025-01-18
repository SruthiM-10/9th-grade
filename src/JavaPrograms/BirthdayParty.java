package JavaPrograms;
import java.util.*;

//(Kattis) USACO Guide - Graph Traversal
// Question Link: https://open.kattis.com/problems/birthday
/*
Input:
The input will contain up to 10 test cases.
Each test case contains on a single line two integers p (1 <= p <= 100) and c (0 <= c <= 5000).
p represents the number of people James wants to invite to the party, including himself.
The next c lines represent the connections among James’s friends represented as two integers
a (0 <= a <= p) and b (0 <= b <= p) where a is not equal to b.
This means that friend number a has friend number b’s phone number and vice versa.

The input will be terminated by a line containing 2 zeros. This case should not be processed.

Output:
For each test case, if a pair could lose each other’s numbers and make it so that not everybody can be invited to the party, print “Yes”. Otherwise, print “No”.
 */

public class BirthdayParty {
    static int p, c;
    static boolean[][] adj;
    static boolean[] vis;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            boolean allConnected = true;
            p = sc.nextInt();
            c = sc.nextInt();

            if (p == 0 && c == 0) break;

            // Adjacency matrix
            adj = new boolean[p][p];
            Edge[] edges = new Edge[c];
            for (int i = 0; i < c; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                adj[a][b] = true;
                adj[b][a] = true;
                edges[i] = new Edge(a, b);
            }

            for (Edge edge : edges) {
                // Remove the current edge we're looking at from the graph
                adj[edge.a][edge.b] = false;
                adj[edge.b][edge.a] = false;

                vis = new boolean[p];
                dfs(0);

                for (int i = 0; i < p; i++) {
                    // If a node wasn't visited after the dfs, that means that
                    // the nodes aren't all in one connected component
                    if (!vis[i]) { allConnected = false;}
                }

                // Add the edge back to the graph
                adj[edge.a][edge.b] = true;
                adj[edge.b][edge.a] = true;
            }

            if (allConnected) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
        }
    }

    static void dfs(int pos) {
        vis[pos] = true;
        for (int to = 0; to < p; to++) {
            if (adj[pos][to] && !vis[to]) { dfs(to); }
        }
    }

    static class Edge {
        int a, b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
