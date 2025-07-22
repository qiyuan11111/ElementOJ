package com.elementoj.module.test.domain;
import javafx.util.Pair;
import java.util.*;


public class Main {
    static final int maxn = 100005;
    static int n;

    static long[] a = new long[maxn];
    static long[] ix = new long[maxn];

    static long[] tcost = new long[maxn];

    static Queue<Pair<Long, Integer>> que = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        for(int i = 1; i <= n; i++){
            a[i] = in.nextLong();
        }
        long cnt = 0;

        for(int i = 1; i < n; i++){
            ix[i] = Math.abs(a[i] - a[i+1]);
            cnt = cnt + ix[i];
        }

        if(n <= 3){
            System.out.println(cnt);
            return;
        }
        ix[n] = ix[n-1];

        long ans = cnt;
        ans = Math.min(ans, cnt - ix[1] - ix[2]);
        ans = Math.min(ans, cnt - ix[n-1] - ix[n-2]);

        for(int i = 2; i < n - 1; i++){
            long cost = Math.abs(a[i + 2] - a[i - 1]) - ix[i] - ix[i + 1] - ix[i - 1];
            ans = Math.min(ans, cnt + cost);
        }

        for(int i = 1; i <= n; i++){
            long cost = -ix[i] - (i - 1 >= 1 && i != n? ix[i-1]: 0);
            if(i + 1 <= n && i - 1 >= 1)    cost += Math.abs(a[i + 1] - a[i - 1]);
            tcost[i] = cost;
            que.add(new Pair<>(cost, i));
        }

        for(int i = 1; i <= n; i++){
            long cost = tcost[i];
            List<Pair<Long, Integer>> list = new ArrayList<>();
            ans = Math.min(ans, cnt + cost);

            while(que.size() > 0){
                Pair<Long, Integer> p = que.remove();
                if(p.getValue() + 1 == i || p.getValue() - 1 == i){
                    list.add(p);
                    continue;
                }
                ans = Math.min(ans, cnt + p.getKey());
                break;
            }
            que.addAll(list);
        }

        System.out.println(ans);

//        long mincost = -ix[idx] - (idx - 1 >= 1 && idx != n? ix[idx-1]: 0);
//        if(idx + 1 <= n && idx - 1 >= 1)    mincost += Math.abs(a[idx + 1] - a[idx - 1]);
//        for(int i = 1; i <= n; i++){
//            if(i + 1 == idx || i - 1 == idx)    continue;
//            long cost = -ix[i] - (i - 1 >= 1 && i != n? ix[i-1]: 0);
//            if(i + 1 <= n && i - 1 >= 1)    cost += Math.abs(a[i + 1] - a[i - 1]);
//            ans = Math.min(ans, cnt + cost + mincost);
//        }

        System.out.println(ans);


    }
}

/*
5
4 1 5 1 4
*/
