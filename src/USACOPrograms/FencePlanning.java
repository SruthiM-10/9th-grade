package USACOPrograms;
import java.io.*;
import java.util.*;

//USACO 2019 US Open Contest, Silver - 9/10 test cases passed (HashMap Overcomplication)

public class FencePlanning {
    static HashMap<int[], ArrayList<int[]>> adj;
    static ArrayList<int[]> cows;
    static int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX, maxY;

    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter out = new PrintWriter("fenceplan.out");

        StringTokenizer info = new StringTokenizer(read.readLine());
        int N = Integer.parseInt(info.nextToken()), M = Integer.parseInt(info.nextToken());

        cows = new ArrayList<>();
        for (int i = 0; i < N; i ++){
            info = new StringTokenizer(read.readLine());
            cows.add(new int[] {Integer.parseInt(info.nextToken()), Integer.parseInt(info.nextToken())});
        }

        adj = new HashMap<>();
        for (int i = 0; i < M; i ++) {
            info = new StringTokenizer(read.readLine());
            int a = Integer.parseInt(info.nextToken()) - 1;
            int b = Integer.parseInt(info.nextToken()) - 1;
            ArrayList<int[]> val = new ArrayList<>();
            if (adj.containsKey(cows.get(a))) val = adj.get(cows.get(a));
            val.add(cows.get(b));
            adj.put(cows.get(a), val);
            val = new ArrayList<>();
            if (adj.containsKey(cows.get(b))) val = adj.get(cows.get(b));
            val.add(cows.get(a));
            adj.put(cows.get(b), val);
        }

        long perimeter = Integer.MAX_VALUE;
        while (cows.size() > 0){
            dfs(cows.get(0));
            long newPerimeter = (maxY - minY + maxX - minX) * 2L;
            perimeter = Math.min(perimeter, newPerimeter);
            minX = minY = Integer.MAX_VALUE;
            maxX = maxY = 0;
        }
        out.println(perimeter);
        read.close();
        out.close();
    }
    static void dfs(int[] node){
        if (!cows.contains(node)) return;
        if (node[0] < minX) minX = node[0];
        if (node[0] > maxX) maxX = node[0];
        if (node[1] < minY) minY = node[1];
        if (node[1] > maxY) maxY = node[1];
        cows.remove(node);
        for (int[] neighbor : adj.get(node)){
            dfs(neighbor);
        }
    }
}
