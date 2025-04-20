package USACOPrograms;
import java.io.*;
import java.util.*;

//USACO 2023 Feburary Contest, Silver
/*
Somebody has been grazing in Farmer John's (1≤G≤105) private gardens!
Using his expert forensic knowledge, FJ has been able to determine the precise time each garden was grazed.
He has also determined that there was a single cow that was responsible for every grazing incident.

In response to these crimes each of FJ's N (1≤N≤105) cows have provided an alibi that proves the cow was in a specific location at a specific time.
Help FJ test whether each of these alibis demonstrates the cow's innocence.

A cow can be determined to be innocent if it is impossible for her to have travelled between all of the grazings and her alibi.
Cows travel at a rate of 1 unit distance per unit time.

INPUT FORMAT (input arrives from the terminal / stdin):
The first line of input will contain G and N separated by a space.
The next G lines contain the integers x, y, and t (−10^9≤x,y≤10^9;0≤t≤10^9) separated by a space describing the location and time of the grazing.
It will always be possible for a single cow to travel between all grazings.

The next N lines contain x, y, and t (−10^9≤x,y≤10^9;0≤t≤10^9) separated by a space describing the location and time of each cow's alibi.

OUTPUT FORMAT (print output to the terminal / stdout):
Output a single integer: the number of cows with alibis that prove their innocence.
 */

public class Cowlibi {
    static class Loc{
        long x, y, t;
        Loc(int x, int y){
            this.x = x;
            this.y = y;
        }
        Loc (int x, int y, long t){
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = read.readLine().split(" ");
        int G = Integer.parseInt(arr[0]), N = Integer.parseInt(arr[1]);

        TreeMap<Long, Loc> grazing = new TreeMap<>();
        for (int i = 0; i < G; i ++){
            arr = read.readLine().split(" ");
            int x = Integer.parseInt(arr[0]), y = Integer.parseInt(arr[1]); long t = Integer.parseInt(arr[2]);
            grazing.put(t, new Loc(x,y)); }
        Loc[] alibis = new Loc[N];
        for (int i = 0; i < N; i ++){
            arr = read.readLine().split(" ");
            int x = Integer.parseInt(arr[0]), y = Integer.parseInt(arr[1]), t = Integer.parseInt(arr[2]);
            alibis[i] = new Loc(x, y, t);
        }

        int answer = 0;
        for (Loc alibi : alibis){
            long high = grazing.ceilingKey(alibi.t) != null ? grazing.ceilingKey(alibi.t) : -1;
            long low = grazing.floorKey(alibi.t) != null ? grazing.floorKey(alibi.t) : -1;
            boolean flag1 = false, flag2 = false;
            if (low != -1){
                long dist = (alibi.x - grazing.get(low).x) * (alibi.x - grazing.get(low).x) + (alibi.y - grazing.get(low).y) * (alibi.y - grazing.get(low).y);
                if ((alibi.t - low) * (alibi.t - low) < dist) flag1 = true;
            }
            if (high != -1){
                long dist = (alibi.x - grazing.get(high).x) * (alibi.x - grazing.get(high).x) + (alibi.y - grazing.get(high).y) * (alibi.y - grazing.get(high).y);
                if ((high - alibi.t) * (high - alibi.t) < dist) flag2 = true;
            }
            if (flag1 || flag2) answer ++;
        }
        System.out.println(answer);
    }
}
