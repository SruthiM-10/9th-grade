package USACOPrograms;
import java.io.*;
import java.util.*;

//USACO 2016 December Contest, Silver

public class Moocast {
    static boolean[][] connected;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new FileReader("moocast.in"));
        int cowNum = Integer.parseInt(read.readLine());
        int[] x = new int[cowNum];
        int[] y = new int[cowNum];
        int[] power = new int[cowNum];
        for (int c = 0; c < cowNum; c ++){
            StringTokenizer cow = new StringTokenizer(read.readLine());
            x[c] = Integer.parseInt(cow.nextToken());
            y[c] = Integer.parseInt(cow.nextToken());
            power[c] = Integer.parseInt(cow.nextToken());
        }

        connected = new boolean[cowNum][cowNum];
        for (int i = 0; i < cowNum; i ++){
            for (int j = 0; j < cowNum; j ++){
                int distSquared = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
                connected[i][j] = distSquared <= power[i] * power[i];
            }
        }

        int maxCows = 0;
        for (int c = 0; c < cowNum; c ++){
            visited = new boolean[cowNum];
            maxCows = Math.max(maxCows, reachableCows(c));
        }

        PrintWriter written = new PrintWriter("moocast.out");
        written.println(maxCows);
        written.close();
    }
    static int reachableCows(int c){
        visited[c] = true;
        int reached = 1;
        for (int nc = 0; nc < connected.length; nc ++){
            if (!visited[nc] && connected[c][nc]){
                visited[nc] = true;
                reached += reachableCows(nc);
            }
        }
        return reached;
    }
}
