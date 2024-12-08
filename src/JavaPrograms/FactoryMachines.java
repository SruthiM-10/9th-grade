package JavaPrograms;
import java.io.*;

//(CSES) USACO Guide Silver Section "Binary Search"

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
