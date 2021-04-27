package com.dc.spring.demo.algorithm;


public class BinarySearch {
    public static void main(String[] args) {
        int[] arry = {1, 2, 3, 4, 5, 6};
        System.out.println(find(arry, 2));
    }

    public static int find(int[] arry, int toFind) {
        int left = 0;
        int right = arry.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (toFind < arry[mid]) {
                right = mid - 1;
            } else if (toFind > arry[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
