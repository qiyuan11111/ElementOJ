package com.study;

public class Main {

    public static int[] a = new int[]{1,1,2,2,2,2,4,5};
    public static int pos = 1, n = 8;
//    有序整数数组
//    首次
    static int finds(){
        if(a[0] > pos || a[n-1] < pos)  return -1;
        int l = 0, r = n-1;
        while(l < r){
            int mid = (l + r) >> 1;
            if(a[mid] < pos)    l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public static void main(String[] args) {
        int p = finds();
        System.out.println(p);
    }
}
