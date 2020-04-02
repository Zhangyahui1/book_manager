package com.qlu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class wande {
    public static void main(String[] args) {

//        int[] nums = {23,5,12,64,12,56};
//        int[] data = {44,62,12,89,12,29,34};
        int[] nums = new int[1000];
        int[] data = new int[1000];
        Scanner in = new Scanner(System.in);
        for (int i = 0;i<1000;i++){
            nums[i] = in.nextInt();
        }
        for (int i = 0;i<1000;i++){
            data[i] = in.nextInt();
        }

        ArrayList<Integer> list = new ArrayList<Integer>(nums.length+data.length);
        for (int x:nums) {
            list.add(x);
        }
        for(int y:data){
            list.add(y);
        }
        Collections.sort(list);
        for (int l:list) {
            System.out.println(l);
        }
    }
}