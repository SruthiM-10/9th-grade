package USACOPrograms;
import java.util.*;

/*
The words are given in a compressed format. We will first define N+1 (1≤N≤106) distinct words, and then the word bank will consist of all those words that are not a prefix of another word.
The words are defined as follows:
    Initially, the 0th word will be the empty string.
    Then for each 1≤i≤N, the ith word will be equal to the pith word plus an additional character at the end (0≤pi<i).
    The characters are chosen such that all N+1 words are distinct.

While the bank is nonempty, Bessie will select a word from the bank, remove it from the bank, and read it to Elsie one character at a time from left to right.
Elsie's task is to tell Bessie once she can uniquely identify it, after which Bessie will stop reading.

Input:
N and p1,p2,p3, ... pN
M is the number of words that are not the prefix of some other word. Next m lines contain w1, w2, ... wM, meaning the w_ith word will be the ith word to be read.

Output:
If Elsie answers as quickly as possible, how many characters will Bessie read?
 */

public class VocabularyQuiz {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] p = new int[N + 1];
        for (int i = 1; i <= N; i ++) p[i] = Integer.parseInt(scan.next());
        int[] edges = new int[N + 1]; // stores number of children per node
        int[] vertices = new int[N + 1]; // stores depth, distance to root node

        for (int i = 1; i <= N; i ++){
            edges[p[i]] += 1;
            vertices[i] = vertices[p[i]] + 1;
        }

        // calculate M (number of leaves)
        while (scan.hasNext()){
            int x = Integer.parseInt(scan.next());
            while (edges[x] == 0){
                if (x == 0){
                    System.out.println("0");
                    break;
                }
                if (edges[p[x]] == 1){
                    x = p[x];
                    edges[x] = 0;
                    continue;
                }
                System.out.println(vertices[x]);
                edges[p[x]] -= 1;
                break;
            }
        }
    }
}
