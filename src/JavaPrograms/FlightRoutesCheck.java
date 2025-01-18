package JavaPrograms;
import java.util.*;
import java.io.*;

//(CSES) USACO Guide Graph Traversl
/*
There are n cities and m flight connections. Your task is to check if you can travel from any city to any other city using the available flights.
Input:
The first input line has two integers n and m: the number of cities and flights. The cities are numbered 1,2,...,n.
After this, there are m lines describing the flights. Each line has two integers a and b: there is a flight from city a to city b. All flights are one-way flights.
Output:
Print "YES" if all routes are possible, and "NO" otherwise. In the latter case also print two cities a and b such that you cannot travel from city a to city b.
If there are several possible solutions, you can print any of them.
 */

public class FlightRoutesCheck {
    static int n, m;
    static boolean[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(read.readLine());
        n = Integer.parseInt(s.nextToken());
        m = Integer.parseInt(s.nextToken());

        //Stores the graph we are given
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        //Stores the reverse of the graph we are given, i.e. what paths cannot be taken
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();

        for (int i = 0; i < n; i ++){
            adj1.add(new ArrayList<>());
            adj2.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i ++){
            s = new StringTokenizer(read.readLine());
            int a = Integer.parseInt(s.nextToken()) - 1, b = Integer.parseInt(s.nextToken()) - 1;
            adj1.get(a).add(b);
            adj2.get(b).add(a);
        }

        // run dfs to see if you can reach all of the other vertices from 1 and if all other vertices can reach one
        // It suffices to check just one because if one can reach all other vertices and all other vertices can reach one,
        // the graph is fully connected because any two of the other vertices can use vertex one as a middle man to create a route.
        vis = new boolean[n];
        dfs(0, adj1);
        for (int i = 0; i < n; i ++){
            // If some vertex wasn't visited, that means we cannot reach all othe rvertices
            if (!vis[i]){
                System.out.println("NO");
                System.out.println(1 + " " + (i + 1));
                return;
            }
        }

        vis = new boolean[n];
        dfs(0, adj2);
        for (int i = 0; i < n; i ++){
            // If some vertex wasn't visited, that means that vertex one cannot be reached from all other vertices
            if (!vis[i]){
                System.out.println("NO");
                System.out.println((i + 1) + " " + 1);
                return;
            }
        }
        System.out.println("YES");
        read.close();
    }
    static void dfs(int v, ArrayList<ArrayList<Integer>> adj){
        vis[v] = true;
        for (int neighbor : adj.get(v)) {
            if (!vis[neighbor]) dfs(neighbor, adj); // only recurse if you need to
        }
    }
}
