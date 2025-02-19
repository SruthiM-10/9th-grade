package USACOPrograms;
import java.io.*;
import java.util.*;

//USACO 2024 December Contest, Silver

public class Deforestation {
    static class Restrict implements Comparable<Restrict>{
        int start, end, numOfTrees;
        Restrict (int start, int end, int numOfTrees){
            this.start = start;
            this.end = end;
            this.numOfTrees = numOfTrees;
        }

        public int compareTo(Restrict o) {
            return Integer.compare(o.start, this.start);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(read.readLine());
        for (int t = 0; t < T; t ++){
            String s = read.readLine(); String[] arr = s.split(" ");
            int N = Integer.parseInt(arr[0]), K = Integer.parseInt(arr[1]);

            TreeSet<Integer> trees = new TreeSet<>();
            s = read.readLine(); arr = s.split(" ");
            for (int i = 0; i < N; i ++){
                trees.add(Integer.parseInt(arr[i]));
            }
            ArrayList<Integer> trees_arr = new ArrayList<>(trees);

            Restrict[] restrictions = new Restrict[K];
            for (int i = 0; i < K; i ++){
                s = read.readLine(); arr = s.split(" ");
                int start = Integer.parseInt(arr[0]), end = Integer.parseInt(arr[1]);
                int numOfTrees = Integer.parseInt(arr[2]);
                restrictions[i] = new Restrict(start, end, numOfTrees);
            }
            Arrays.sort(restrictions);

            int[] pref_trees = new int[N];
            TreeSet<Integer> minTrees = new TreeSet<>();
            TreeSet<Integer> ends = new TreeSet<>();
            int prev_num = 0, rightmostEnd = Integer.MIN_VALUE;
            for (Restrict r : restrictions){
                int startingTree = trees.floor(r.start), endTree = trees.ceiling(r.end);
                int treeNum = trees_arr.indexOf(endTree) - trees_arr.indexOf(startingTree) + 1;
                int maxCut = r.numOfTrees - treeNum;

                if (prev_num == 0) prev_num = r.numOfTrees;
                if (r.numOfTrees > prev_num){
                    pref_trees[trees_arr.indexOf(startingTree)] = r.numOfTrees;
                    if (rightmostEnd - endTree > 1){
                        pref_trees[trees_arr.indexOf(endTree + 1)] = -1;
                    }
                    else if (endTree > rightmostEnd){
                        rightmostEnd = endTree;
                        pref_trees[endTree + 1] = -1;
                    }
                }
                else if (endTree >= rightmostEnd){
                    if (startingTree > rightmostEnd){
                        pref_trees[startingTree] = r.numOfTrees;
                    }
                    else{

                    }
                    rightmostEnd = endTree;
                    pref_trees[endTree + 1] = -1;
                }
            }
        }
    }
//    Integer arr2[] = new Integer[(int) Math.pow(10, 5)];
//            Arrays.fill(arr2, 0);
//    ArrayList<Integer> pref_trees = new ArrayList<>(Arrays.asList(arr2));
}
