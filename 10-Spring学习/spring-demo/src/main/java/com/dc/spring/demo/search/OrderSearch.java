package com.dc.spring.demo.search;

public class OrderSearch {
    public static void main(String[] args) {
        int[] arry = {1,2,3,4,5,6};
        System.out.println(find(arry ,10));
    }

    public static int find(int[] arry, int toFind){
        for (int i = 0; i < arry.length; i++) {
            if(arry[i] == toFind){
                return i;
            }
        }
        return -1;
    }
}
