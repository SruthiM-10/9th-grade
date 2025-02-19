package USACOPrograms;
import java.util.*;

public class CowCheckups {
    static class Repetitions{
        TreeSet<Integer> reps;
        ArrayList<Integer> pref_sum; // d_s

        TreeSet<Integer> d_reps;
        ArrayList<Integer> d_pref_sum; //backwards too
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] cows = new int[N];

        for (int i = 0; i < N; i ++){
            cows[i] = scan.nextInt();
        }

        Repetitions[] vet_line = new Repetitions[N + 1];
        for (int i = 1; i < N + 1; i ++){
            vet_line[i] = new Repetitions();
            vet_line[i].reps = new TreeSet<>();
            vet_line[i].pref_sum = new ArrayList<>();
            vet_line[i].pref_sum.add(0); // Remember this!!

            vet_line[i].d_reps = new TreeSet<>();
            vet_line[i].d_pref_sum = new ArrayList<>();
            vet_line[i].d_pref_sum.add(0); // Remember this!!
        }

        for (int i = 0; i < N; i ++){
            int b_i = scan.nextInt();
            vet_line[b_i].reps.add(i);
            vet_line[b_i].pref_sum.add(vet_line[b_i].pref_sum.get(vet_line[b_i].pref_sum.size() - 1) + i);

            vet_line[b_i].d_reps.add(N - 1 - i);
            vet_line[b_i].d_pref_sum.add(vet_line[b_i].d_pref_sum.get(vet_line[b_i].d_pref_sum.size() - 1) + N - 1 - i);
        }

        int total_count = 0;
        for (int i = 0; i < N; i ++){
            int count = 0;
            int species = cows[i];
            //swaps without me
            if (vet_line[species].reps.contains(i)){
                count += (i - 1)*(i)/2 + (N - 1 - i)*(N - i - 2)/2;
                count += N - 1;
            }
            // swaps with me
            count += vet_line[species].reps.size();
            // swaps including me
            int d_s = i, d_b = N - i - 1;
            TreeSet<Integer> reps2 = new TreeSet<>(vet_line[species].reps);
            SortedSet<Integer> lessThan = reps2.headSet(i);
            if (lessThan.size() > 0 && lessThan.first() < d_b && d_b <= lessThan.last()) {
                lessThan = lessThan.headSet(d_b);
            }
            if (lessThan.size() > 0 && lessThan.first() < d_b) count += vet_line[species].pref_sum.get(lessThan.size());
            lessThan = reps2.headSet(i);
            if (lessThan.size() > 0 && lessThan.last() > d_b && d_b >= lessThan.first()) {
                lessThan = lessThan.tailSet(d_b);
            }
            count += lessThan.size() * d_b;

            reps2 = new TreeSet<>(vet_line[species].d_reps);
            lessThan = reps2.headSet(d_b);
            if (lessThan.size() > 0 && lessThan.first() < d_s && d_s <= lessThan.last()) lessThan = lessThan.headSet(d_s);
            if (lessThan.first() <= d_s) count += vet_line[species].d_pref_sum.get(vet_line[species].d_pref_sum.size() - 1) -
                                                    vet_line[species].d_pref_sum.get(vet_line[species].d_pref_sum.size() - 1 - lessThan.size());
            lessThan = reps2.headSet(d_b);
            if (lessThan.size() > 0 && lessThan.last() > d_s && d_s <= lessThan.first()) lessThan = lessThan.tailSet(d_s);
            count += d_s * lessThan.size();

            if (vet_line[species].reps.contains(i)) count += Math.min(d_s, d_b);
            total_count += count;
        }
        System.out.println(total_count);
    }
}
