package JavaPrograms;
import java.io.*;
import java.util.*;

//USACO Guide - Binary Search - Codeforces

public class TheMeetingPlaceCannotBeChanged {
    static final double MAX_ERROR = 10e-6;

    static List<Double> locations = new ArrayList<>();
    static List<Double> speeds = new ArrayList<>();

    static double min_ans = Double.MAX_VALUE;
    static double curr_min_time;
    static boolean down_has_max = false;  // which side gives us max answer
    static boolean up_has_max = false;

    /**
     * Checks whether all friends can converge on one point in the specified time interval
     */
    public static double get_time(double loc){
        double max_time = 0;
        for (int i = 0; i < locations.size(); i ++){
            if (locations.get(i) == loc) continue;
            double i_time = Math.abs(locations.get(i) - loc) / speeds.get(i);
            if (i_time > max_time){
                down_has_max = false;
                up_has_max = false;
                if (locations.get(i) > loc) up_has_max = true;
                else down_has_max = true;
                max_time = i_time;
            }
            else if (i_time == max_time){
                if (locations.get(i) > loc) up_has_max = true;
                else down_has_max = true;
            }
        }
        return max_time;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(read.readLine());
        double low = 0, high = 0; // location of the farthest north friend

        String s = read.readLine(); String[] arr = s.split(" ");
        for (int i = 0; i < n; i ++){
            double location = Double.parseDouble(arr[i]);
            locations.add(location);
            high = Math.max(high, location);
        }

        //Binary search to find the minimum time
        while (high - low > MAX_ERROR){
            double mid = (high + low)/2;
            curr_min_time = get_time(mid);
            min_ans = Math.min(min_ans, curr_min_time);
            if (down_has_max && up_has_max) break;
            else if (down_has_max) high = mid;
            else low = mid;
        }

        System.out.printf("%.10f%n", min_ans);
    }
}
