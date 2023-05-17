package com.yu.demo;

public class Demo04 {

    public static void main(String[] args) {
        int[] arrs = {1,5,9,4,6,7,8,3,8,7,2};
        paixu(arrs, 1, arrs.length - 1);
        for (int i = 0; i < arrs.length; i++){
            System.out.println(arrs[i]);
        }
    }

    public static void paixu(int[] arrs, int l, int h){
        int i,j,temp,t;
        if (l > h){
            return;
        }
        i = l;
        j = h;
        //以temp为比较的基准
        temp = arrs[l];

        while (i < j){
            //先看右边
            while (temp <= arrs[j] && i < j){
                j--;
            }
            //先看左边
            while (temp >= arrs[i] && i <j){
                i++;
            }
            //满足条件进行交换
            if (i < j){
                t = arrs[j];
                arrs[j] = arrs[i];
                arrs[i] = t;
            }
        }

        arrs[l] = arrs[i];
        arrs[i] = temp;
        paixu(arrs, l, j - 1);
        paixu(arrs, j + 1, h);


    }

}

