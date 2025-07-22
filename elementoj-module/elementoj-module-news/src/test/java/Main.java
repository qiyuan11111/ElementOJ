import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;

public class Main {

    static int a = 0;
    static final Object lock = new Object();
    public static void main(String[] args) throws NoSuchMethodException {
        new Thread(() -> {
            while(a <= 100){
                synchronized (lock){
                    if(a % 2 == 0){
                        a++;
                        System.out.println(a);
                    }
                }

            }
        }).start();

        new Thread(() -> {
            while(a <= 100){
                synchronized (lock){
                    if(a % 2 == 1){
                        a++;
                        System.out.println(a);
                    }
                }

            }
        }).start();
    }

}
/*
2
1 2 3 4 5 6
6 5 4 3 2 1
*/

//public class Main {
//
//    static long n, m, k;
//
//    static long l, r;
//
//    public static long check(long t){
//        long sum = (1 + t) * t / 2;
//        if(r + 1 >= t){
//            sum += (1 + t) * t / 2;
//        }else{
//            sum += (2 * t - r) * (r + 1) / 2;
//        }
//
//        return sum - t;
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        n = sc.nextLong();
//        m = sc.nextLong();
//        k = sc.nextLong();
//
//        m -= n;
//
//        l = k - 1;
//        r = n - k;
//
//        if(l < r){
//            long t = l;
//            l = r;
//            r = t;
//        }
//
//        long tot = (l + 2) * (l + 1) / 2 + (2 * l + 2 - r) * (r + 1) / 2 - l - 1;
//        long ans = 0;
//
//        if(tot <= m){
//            m -= tot;
//            ans = m / n + l + 2;
//        }else{
//            long left = 0, right = l + 1;
//            while(left < right){
//                long mid = (left + right + 1) >> 1;
//                if(check(mid) <= m)  left = mid;
//                else right = mid - 1;
//            }
//            ans = left + 1;
//        }
//
//        System.out.println(ans);
//    }
//}
