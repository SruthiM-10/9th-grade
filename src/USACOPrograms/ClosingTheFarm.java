package USACOPrograms;
import java.io.*;
import java.util.*;

//USACO 2016 US Open Contest, Silver

public class ClosingTheFarm {
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited, closed;
    static int nodes;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("closing.in"));
        PrintWriter out = new PrintWriter("closing.out");

        int n = sc.nextInt();
        int m = sc.nextInt();

        visited = new boolean[n];
        closed = new boolean[n];
        adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<Integer>());

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        int[] order = new int[n];
        for (int i = 0; i < n; i++) { order[i] = sc.nextInt() - 1; }

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            nodes = 0;

            dfs(order[n - 1]);  // DFS from the barn that will close last
            // Checks if all unclosed barns have been visited
            if (nodes == n - i) {
                out.println("YES");
            } else {
                out.println("NO");
            }
            closed[order[i]] = true;
        }
        out.close();
    }

    static void dfs(int node) {
        if (visited[node] || closed[node]) return;

        // Increase the number of nodes
        // iff this barn hasn't already been closed or visited
        nodes++;
        visited[node] = true;
        for (int neighbor : adj.get(node)) { dfs(neighbor); }
    }
}
