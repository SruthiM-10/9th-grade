package USACOPrograms;
import java.util.*;

//USACO 2021 December Contest, Silver (USACO Guide Graph Traversal)
/*
INPUT FORMAT (input arrives from the terminal / stdin):
Each input test case contains T sub-cases (1≤T≤20), all of which must be solved correctly to solve the input case.
The first line of input contains T, after which Tsub-test cases follow.

Each sub-test case starts with two integers, N and M.
Next, M lines follow, each one containing two integers i and j, indicating a path between two different fields i and j.
It is guaranteed that there is at most one path between any two fields, and that the sum of N+M over all sub-test cases is at most 5⋅10^5.

OUTPUT FORMAT (print output to the terminal / stdout):
Output T lines. The ith line should contain a single integer giving the minimum cost for the ith sub-test case.
 */

public class ConnectingTwoBarns {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for (int test = 0; test < t; test ++){
            int n = scan.nextInt();
            int m = scan.nextInt();

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i ++) adj.add(new ArrayList<>());
            for (int i = 0; i < m; i ++){
                int a = scan.nextInt() - 1;
                int b = scan.nextInt() - 1;
                adj.get(a).add(b);
                adj.get(b).add(a);
            }

            int[] visited = new int[n];
            int numComponents = 0;
            Arrays.fill(visited, -1);

            for (int i = 0; i < n; i ++){
                if (visited[i] == -1){
                    Stack<Integer> stack = new Stack<>();
                    stack.push(i);
                    while(!stack.isEmpty()){
                        int curr = stack.pop();
                        if (visited[curr] != -1) continue;
                        visited[curr] = numComponents;
                        for (int neighbor : adj.get(curr)) { stack.push(neighbor); }
                    }
                    numComponents++;
                }
            }

            ArrayList<ArrayList<Integer>> components = new ArrayList<>();
            for (int i = 0; i < numComponents; i ++){
                components.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i ++){
                components.get(visited[i]).add(i);
            }

            List<Integer> barn1 = components.get(visited[0]);
            List<Integer> barn2 = components.get(visited[n - 1]);

            long[] dist1 = new long[numComponents];
            long[] dist2 = new long[numComponents];
            Arrays.fill(dist1, Integer.MAX_VALUE);
            Arrays.fill(dist2, Integer.MAX_VALUE);

            int barn1Index = 0;
            for (int i = 0; i < n; i ++){
                int dist = Math.abs(barn1.get(barn1Index) - i);
                while (barn1Index < barn1.size() - 1 && Math.abs(barn1.get(barn1Index + 1) - i) < dist){
                    barn1Index ++;
                }
                dist1[visited[i]] = Math.min(dist, dist1[visited[i]]);
            }

            int barn2Index = 0;
            for (int i = 0; i < n; i ++){
                int dist = Math.abs(barn2.get(barn2Index) - i);
                while (barn2Index < barn2.size() - 1 && Math.abs(barn2.get(barn2Index + 1) - i) < dist){
                    barn2Index ++;
                }
                dist2[visited[i]] = Math.min(dist, dist2[visited[i]]);
            }

            long min = Long.MAX_VALUE;
            for (int i = 0; i < numComponents; i ++){
                long cost = dist1[i] * dist1[i] + dist2[i]*dist2[i];
                min = Math.min(min, cost);
            }
        }
    }
}
