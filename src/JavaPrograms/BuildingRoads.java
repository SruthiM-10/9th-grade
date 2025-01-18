package JavaPrograms;
import java.io.*;
import java.util.*;

// (CSES) USACO Guide - Graph Traversal - Accepted
/*
Byteland has n cities, and m roads between them. The goal is to construct new roads so that there is a route between any two cities.
Your task is to find out the minimum number of roads required, and also determine which roads should be built.
Input
The first input line has two integers n and m: the number of cities and roads. The cities are numbered 1,2,...,n.
After that, there are m lines describing the roads. Each line has two integers a and b: there is a road between those cities.
A road always connects two different cities, and there is at most one road between any two cities.
Output
First print an integer k: the number of required roads.
Then, print k lines that describe the new roads. You can print any valid solution.
*/

public class BuildingRoads {
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String s = read.readLine(); String[] arr = s.split(" ");
        int n = Integer.parseInt(arr[0]), m = Integer.parseInt(arr[1]);

        adj = new ArrayList<>();
        for (int i = 0; i < n; i ++){ adj.add(new ArrayList<>()); }
        for (int i = 0; i < m; i ++){
            s = read.readLine(); arr = s.split(" ");
            int a = Integer.parseInt(arr[0]) - 1;
            int b = Integer.parseInt(arr[1]) - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        visited = new boolean[n];
        ArrayList<Integer> cityReps = new ArrayList<>();
        for (int i = 0; i < n; i ++){
            if (visited[i]) { continue; }
            visited[i] = true;
            cityReps.add(i);
            dfs(i);
        }

        StringBuilder ans = new StringBuilder();
        ans.append(cityReps.size() - 1).append('\n');
        for (int i = 0; i < cityReps.size() - 1; i ++){
            ans.append(cityReps.get(i) + 1)
                    .append(' ')
                    .append(cityReps.get(i + 1) + 1)
                    .append('\n');
        }
        System.out.println(ans);
        read.close();
    }
    static void dfs(int node){
        for (int n : adj.get(node)){
            if (!visited[n]){
                visited[n] = true;
                dfs(n);
            }
        }
    }
}
