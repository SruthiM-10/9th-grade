package ACSLPrograms;
import java.util.*;

class Marbles {

    /*
     * Complete the 'marblesSort' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER num
     *  2. STRING original
     *  3. STRING moves
     */
    static class Marble{
        String color; int num;
        Marble(String color, int num) {
            this.color = color;
            this.num = num;
        }
        void setNum(int n){
            num = n;
        }
        void setNum(boolean x, int n){
            num += n;
        }
    }
    public static String marblesSort(int num, String original, String moves) {
        ArrayList<ArrayList<Marble>> tubes = new ArrayList<>();
        int[] size = new int[num + 2];
        for (int i = 0; i < num + 2; i ++) {tubes.add(new ArrayList<>());}

        String[] arr = original.split(" ");
        for (int i = 0; i < num; i ++){
            for (String c : arr[i].split("")){
                Marble m = new Marble(c, 1);
                if (tubes.get(i).size() == 0){
                    tubes.get(i).add(m);
                }
                else if(tubes.get(i).get(tubes.get(i).size() - 1).color.equals(m.color)){
                    tubes.get(i).get(tubes.get(i).size() - 1).setNum(true, m.num);
                }
                else{
                    tubes.get(i).add(m);
                }
                size[i] ++;
            }
        }

        arr = moves.split(" ");
        int filledTubes = 0;
        for (int i = 0; i < num + 2; i ++){ if (tubes.get(i).size() != 0 && tubes.get(i).get(0).num == num) filledTubes ++; }
        for (String move : arr){
            int src = move.charAt(0) - '0' - 1, dest = move.charAt(1) - '0' - 1;
            if (tubes.get(src).get(0).num == num || size[dest] == num) break;
            Marble m = tubes.get(src).get(0);
            if (tubes.get(dest).size() == 0){
                tubes.get(dest).add(m);
                size[dest] += m.num; size[src] -= m.num;
                tubes.get(src).remove(0);
            }
            else{
                if (m.num + size[dest] > num){
                    m.setNum(num - size[dest]);
                    if(tubes.get(dest).get(0).color.equals(m.color)){
                        tubes.get(dest).get(0).setNum(true, m.num);
                    }
                    else break;
                    size[dest] += m.num; size[src] -= m.num;
                    tubes.get(src).set(0, new Marble(m.color, tubes.get(src).get(0).num - m.num));
                }
                else{
                    if(tubes.get(dest).get(0).color.equals(m.color)){
                        tubes.get(dest).get(0).setNum(true, m.num);
                    }
                    else break;
                    size[dest] += m.num; size[src] -= m.num;
                    tubes.get(src).remove(0);
                }
            }
            for (ArrayList<Marble> tube : tubes){
                for (Marble a : tube){
                    System.out.print(a.color + "(" + a.num + ")");
                }
                System.out.print(" ");
            }
            System.out.println();
            if (tubes.get(dest).get(0).num == num) filledTubes ++;
            if (filledTubes == num) break;
        }
        String ans = "";
        if (filledTubes == num){
            for (int i = 0; i < num + 2; i ++){
                if (tubes.get(i).size() == 0) ans += "E";
                else ans += tubes.get(i).get(0).color;
            }
        }
        else{
            for (int i = 0; i < num + 2; i ++){ if (tubes.get(i).size() != 0 && tubes.get(i).get(0).num != num) ans += (i + 1); }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt(); scan.nextLine();
        System.out.println(marblesSort(num, scan.nextLine(), scan.nextLine()));
    }
}
