package ACSLPrograms;
import java.util.*;

class Spiral {

    /*
     * Complete the 'findManDist' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING direction
     *  2. INTEGER horiz
     *  3. INTEGER vert
     *  4. INTEGER M
     *  5. INTEGER N
     */

    public static int findManDist(String direction, int horiz, int vert, int M, int N) {
        int min = Math.min(M, N), max = Math.max(M, N);
        int ans = 0;
        if (direction.equals("outward")){
            int num = 1;
            int x_min = 0, y_min = 0, h = 0, y = 0;
            int x_max = 0, y_max = 0;

            int horizontal = 1, right = 1, down = -1;
            int max_h = horiz - 1, max_y = vert - 1;
            while (num != min){
                if (horizontal == 1){
                    h = max_h;
                    while (h > 0){
                        num ++;
                        h --;
                        x_min += right;
                        if (num == min) break;
                    }
                    if (num == min) break;
                    max_h ++;
                    right = right * -1;
                    horizontal = 0;
                }
                else{
                    y = max_y;
                    while (y > 0){
                        num ++;
                        y --;
                        y_min += down;
                        if (num == min) break;
                    }
                    if (num == min) break;
                    max_y ++;
                    down = down * -1;
                    horizontal = 1;
                }
            }
            x_max = x_min; y_max = y_min;
            while (num != max){
                if (horizontal == 1){
                    if (h == 0) h = max_h;
                    while (h > 0){
                        num ++;
                        h --;
                        x_max += right;
                        if (num == max) break;
                    }
                    if (num == max) break;
                    max_h ++;
                    right = right * -1;
                    horizontal = 0;
                }
                else{
                    if (y == 0) y = max_y;
                    while (y > 0){
                        num ++;
                        y --;
                        y_max += down;
                        if (num == max) break;
                    }
                    if (num == max) break;
                    max_y ++;
                    down = down * -1;
                    horizontal = 1;
                }
            }
            ans = Math.abs(x_max - x_min) + Math.abs(y_max - y_min);
        }
        else{
            int num = 1;
            int x_min = 0, y_min = 0, h = 0, y = 0;
            int x_max = 0, y_max = 0;

            int horizontal = 1, right = 1, down = -1;
            int max_h = horiz - 1, max_y = vert - 1;

            while (num != min){
                if (horizontal == 1){
                    h = max_h;
                    while (h > 0){
                        num ++;
                        h --;
                        x_min += right;
                        if (num == min) break;
                    }
                    if (num == min) break;
                    if (num >= (horiz * 2 + vert - 2)) max_h --;
                    right = right * -1;
                    horizontal = 0;
                }
                else{
                    y = max_y;
                    while (y > 0){
                        num ++;
                        y --;
                        y_min += down;
                        if (num == min) break;
                    }
                    if (num == min) break;
                    max_y --;
                    down = down * -1;
                    horizontal = 1;
                }
            }
            x_max = x_min; y_max = y_min;
            while (num != max){
                if (horizontal == 1){
                    if (h == 0) h = max_h;
                    while (h > 0){
                        num ++;
                        h --;
                        x_max += right;
                        if (num == max) break;
                    }
                    if (num == max) break;
                    if (num >= (horiz * 2 + vert - 2)) max_h --;
                    right = right * -1;
                    horizontal = 0;
                }
                else{
                    if (y == 0) y = max_y;
                    while (y > 0){
                        num ++;
                        y --;
                        y_max += down;
                        if (num == max) break;
                    }
                    if (num == max) break;
                    max_y --;
                    down = down * -1;
                    horizontal = 1;
                }
            }
            ans = Math.abs(x_max - x_min) + Math.abs(y_max - y_min);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(findManDist(scan.next(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
    }
}
