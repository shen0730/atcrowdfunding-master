package com.sht.crowd.test;

public class test {

    public void test(){
        int arr[] = new int[]{34,5,98,6,0,3,78,74};
        for(int i = 0;i < arr.length - 1; i++){
            for(int j = 0;j < arr.length - 1 -i;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i] + " ");
        }
    }
}
