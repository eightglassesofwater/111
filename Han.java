import java.io.BufferedReader;
import java.io.InputStreamReader; 
public class Han {
   public static void main(String args[]) throws Exception{//抛出异常 
        int n;       
 BufferedReader buf =  new BufferedReader(new InputStreamReader(System.in));
       System.out.println("请输入盘数：");
        n = Integer.parseInt(buf.readLine());
        Han hanoi = new Han();
        hanoi.move(n, 'A', 'B', 'C');
    } 
    public void move(int n, char a, char b, char c) {
        if (n == 1)
            System.out.println("盘 " + n + " 由 " + a + " 移至 " + c);
        else {
            move(n - 1, a, c, b);
           System.out.println("盘 " + n + " 由 " + a + " 移至 " + c);
         move(n - 1, b, a, c);
        }
    }
}