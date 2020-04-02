package com.qlu;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Test111 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String ss = in.nextLine();
//        int n = Integer.valueOf(ss);
//        String s = in.nextLine();
//        String[] s1 = s.split(" ");
//
//        int[] a = new int[s1.length];
//        for (int i = 0;i<s1.length;i++){
//            a[i] = Integer.valueOf(s1[i]);
//        }
//
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (int i = 0;i<a.length;i++){
//            if (a[i]!=n){
//                arrayList.add(a[i]);
//            }
//        }
//
//        System.out.println(arrayList.toString());
//
//    }
public static void main(String[] args) {
    Date date = new Date();
    System.out.println(date);
    long time = date.getTime();
    System.out.println(time);
    long time2 = time+1000*24*60*60*7;
    System.out.println(time2);
    Date date1 = new Date(time2);
    System.out.println(date1);
}
}
