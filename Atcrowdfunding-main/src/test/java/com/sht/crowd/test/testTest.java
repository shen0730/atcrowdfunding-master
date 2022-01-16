package com.sht.crowd.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void test1() {
//        int arr[] = new int[]{34, 5, 98, 6, 0, 3, 78, 74};
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//    }
//
//    @Test
//    public void main() {
//        // 定义字符串
//        String string = "adAHFJBVFafffassas";
//
//        // 定义map容器
//        Map<Character, Integer> map = new HashMap<Character, Integer>();
//        for (int i = 0; i < string.length(); i++) {
//
//            char ch = string.charAt(i);
//
//            if (map.containsKey(ch)) {
//                int count = map.get(ch);
//                count = count + 1;
//                map.put(ch, count);
//            } else {
//                map.put(ch, 1);
//            }
//        }
//        // 遍历map集合
//        Set<Character> keySet = map.keySet();
//        for (Character chars : keySet) {
//            System.out.println("字符:"+chars + "，出现的次数为："+map.get(chars));
//        }
//
//    }

    @Test
    public void test2() {
        int a = 0;
        for(int i = 0;i <= 100;i++){
            a += i;
        }
        System.out.println("和：" + a);
    }

    @Test
    public void test3(){
        int x=0;
        int y=0;
        for(int i=0;i<=100;i++){
            if(i%2==0){
                x+=i;
            }else{
                y+=i;
            }
        }
        System.out.println("1到100之间的奇数的和为："+x);
        System.out.println("1到100之间的偶数的和为："+y);
    }

}
