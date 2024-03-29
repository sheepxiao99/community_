package com.nowcoder.community;

import java.util.ArrayList;

public class Test2 {

    public static void main(String[] args) {
//        int []a = {2,3,1,3};
//        System.out.println(new Test2().binarySearch(a, 3));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for (int i = 0; i < 2; i++) {
            list.remove(i);
        }
        System.out.println(list);


    }

    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意

        while (left <= right) { // 注意
            int mid = (right + left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1; // 注意
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }


}
