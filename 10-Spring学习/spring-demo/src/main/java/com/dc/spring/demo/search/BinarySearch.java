package com.dc.spring.demo.search;

public class BinarySearch {
    public static void main(String[] args) {
        int [] array = {1,2,3,4,5,6};
        System.out.println(binarySearch(array, 6));
    }

    public static int binarySearch(int [] arr, int toFind){
        int left = 0;    //让left等于数组最左下标
        int right = arr.length-1; //让right等于数组最右下标
        while(left <= right){ // 当left小于right时一直循环此处left = right 也可以作为判断但是没有意义
            int mid = (left + right)/2; //在循环体内定义中间位置标记，不停查找数组中间元素对应下标
            if(toFind < arr[mid]){  //如果要查找元素小于中间元素，即在中间元素的左半部分查找
                right = mid - 1; //并让right移位到中间元素的前一个元素的对应位置
            } else if(toFind > arr[mid]){  //如果要查找元素大于中间元素，即在中间元素的右半部分查找
                left = mid + 1; //并让left移位到中间元素的下一个元素对应位置
            } else {
                return mid;  //核心，当前两个判定不符合时，即在此处中间位置元素即是要查找元素
            }
        }
        return  -1; //没找到就返回-1
    }
}
