package JavaPrograms;
import java.io.*;
import java.util.*;

//USACO - Binary Search - Codeforces
// TLE on very late test cases, try without Map -> code the binary search yourself
// Also use 2D arraylist

public class PreparingForMergeSort {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(read.readLine());
        String s = read.readLine(); String[] arr = s.split(" ");

        TreeMap<Long, ArrayList<Long>> seq = new TreeMap<>();
        for (int i = 0; i < n; i ++){
            long value = Long.parseLong(arr[i]);
            ArrayList<Long> new_arr = new ArrayList<>();
            if (seq.lowerKey(value) == null) {
                new_arr.add(value);
                seq.put(value, new_arr);
            }
            else{
                new_arr.addAll(seq.get(seq.lowerKey(value)));
                new_arr.add(value);
                seq.put(value, new_arr);
                seq.remove(seq.lowerKey(value));
            }
        }
        for (long i : seq.descendingKeySet()){
            for (int j = 0; j < seq.get(i).size(); j ++){
                System.out.print(seq.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
