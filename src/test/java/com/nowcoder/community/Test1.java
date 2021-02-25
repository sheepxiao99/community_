package com.nowcoder.community;

import java.util.Arrays;

public interface Test1 {
    public static void main(String[] args) {
        int[] a = {13, 12, 43, 22, 2, 3, 3};
        int isChange; // 记录是否发生了置换，0：没有发生；1：发生了置换
        int num = 0; //记录执行了多少遍

        // 外层循环是排序的躺数
        for (int i = 0; i < a.length - 1; i++) {
            // 没比较一趟就重新初始化为0
            isChange = 0;
            // 内层的循环是当前躺数需要比较的次数
            for (int j = 0; j < a.length - i - 1; j++) {
                // 前一位与后一位比较，如果前一位比后一位要大，那么交换
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    // 如果进入到内存循环，说明发生了置换
                    isChange = 1;
                }

            }
            // 如果比较完一趟没有发生置换，那么说明已经拍好序了
            if (isChange == 0) {
                break;
            }
            num++;
        }
        System.out.println(Arrays.toString(a));
        System.out.println("执行了" + num + "次");

    }

}
