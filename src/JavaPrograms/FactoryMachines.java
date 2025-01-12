package JavaPrograms;
import java.io.*;

//(CSES) USACO Guide Silver Section "Binary Search"
/*
A factory has n machines which can be used to make products. Your goal is to make a total of t products.
For each machine, you know the number of seconds it needs to make a single product. The machines can work simultaneously, and you can freely decide their schedule.
What is the shortest time needed to make t products?
Input
The first input line has two integers n and t: the number of machines and products.
The next line has n integers k_1,k_2,...,k_n: the time needed to make a product using each machine.
Output
Print one integer: the minimum time needed to make t products.
*/

public class FactoryMachines {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String s = read.readLine();
        String[] arr = s.split(" ");
        int N = Integer.parseInt(arr[0]);
        long products = Long.parseLong(arr[1]);
        long[] times = new long[N];
        s = read.readLine();
        arr = s.split(" ");
        for (int i = 0; i < N; i++) times[i] = Long.parseLong(arr[i]);

        long low = 0;
        long high = (long) 1e18;
        long ans = 0;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (check(mid, times, products)){ ans = mid; high = mid - 1; }
            else low = mid + 1;
        }
        System.out.println(ans);
    }
    static boolean check(long mid, long[] times, long products){
        long curProducts = 0;
        for (long machine : times){
            curProducts += mid/ machine;
            if (curProducts >= products) break;
        }
        return curProducts >= products;
    }
}
