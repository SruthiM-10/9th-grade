package USACOPrograms;
import java.io.*;

//USACO 2020 Jan Contest, Silver (USACO Guide Graph Traversal)
// In Progress
/*
INPUT FORMAT (file wormsort.in):
The first line contains two integers, N and M.
The second line contains the N integers p1,p2,…,pN. It is guaranteed that p is a permutation of 1…N.

For each i between 1 and M, line i+2 contains the integers ai, bi, and wi.

OUTPUT FORMAT (file wormsort.out):
A single integer: the maximum minimal wormhole width which a cow must squish itself into during the sorting process.
If the cows do not need any wormholes to sort themselves, output −1.
 */
public class WormholeSort {
    public static void main(String[] args) throws IOException{
        BufferedReader read = new BufferedReader(new FileReader("wormsort.in"));
        BufferedWriter write = new BufferedWriter(new FileWriter("wormsort.out"));

        int N = Integer.parseInt(read.readLine()), M = Integer.parseInt(read.readLine());

    }
}
