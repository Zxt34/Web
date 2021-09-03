import java.util.*;

public class Test9_2 {
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int jin = scanner.nextInt();
//        int res = 0;
//        while(n != 0){
//            int yu = n % jin;
//            res += yu;
//            n = n / jin;
//        }
//        System.out.println(res);
//    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] arr = new int[len];
        int res = 0;
        for(int i = 0; i < len; i++){
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        for(int i = 1; i <= len; i++){
            res += i * arr[i - 1];
        }
        System.out.println(res);
    }
}