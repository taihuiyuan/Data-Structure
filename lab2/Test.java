package lab2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String args[] ) {
        InsertionSort insert = new InsertionSort();
        MergeSort merge = new MergeSort();
        OptSort opt = new OptSort();
        int[] arr = generateArray(30000);
        int[] arr2 = new int[arr.length];
        int[] arr3 = new int[arr.length];
        int[] arr4 = new int[arr.length];
        int[] arr5 = new int[arr.length];
        int[] arr6 = new int[arr.length];
        for (int i=0;i<arr.length;i++){
            arr2[i] = arr[i];
            arr3[i] = arr[i];
            arr4[i] = arr[i];
            arr5[i] = arr[i];
            arr6[i] = arr[i];
        }

        long begin1 = System.nanoTime();
        insert.Insertion(arr);
        long finish1 = System.nanoTime();
        //printArray(arr);
        System.out.println("InsertionSort:"+(finish1-begin1)+"ns");

        long begin2 = System.nanoTime();
        merge.mergeSort(arr2,0,arr2.length-1);
        long finish2 = System.nanoTime();
        //printArray(arr2);
        System.out.println("MergeSort:"+(finish2-begin2)+"ns");

        long begin3 = System.nanoTime();
        opt.optSort(arr3,40);
        long finish3 = System.nanoTime();
        //printArray(arr3);
        System.out.println("OptSort40:"+(finish3-begin3)+"ns");

        long begin4 = System.nanoTime();
        opt.optSort(arr4,50);
        long finish4 = System.nanoTime();
        //printArray(arr4);
        System.out.println("OptSort50:"+(finish4-begin4)+"ns");

        long begin5 = System.nanoTime();
        opt.optSort(arr5,60);
        long finish5 = System.nanoTime();
        //printArray(arr5);
        System.out.println("OptSort60:"+(finish5-begin5)+"ns");

        long begin6 = System.nanoTime();
        opt.optSort(arr6,70);
        long finish6 = System.nanoTime();
        //printArray(arr6);
        System.out.println("OptSort70:"+(finish6-begin6)+"ns");
    }

    public static int[] generateArray(int length){
        int[] arr = new int[length];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*length);
        }
        return arr;
    }

    public static void printArray(int[] nums){
        List<Integer> res = new ArrayList<>();
        for (int i=0;i < nums.length;i++) {
            res.add(nums[i]);
        }
        System.out.println(res);
    }

    //String
    public static void printArray(String[] nums){
        List<String> res = new ArrayList<>();
        for (int i=0;i < nums.length;i++) {
            res.add(nums[i]);
        }
        System.out.println(res);
    }

    public static boolean compareStr(String a,String b){
        a.toLowerCase();
        b.toLowerCase();
        char[] a2= a.toCharArray();
        char[] b2 = b.toCharArray();
        if (a2.length < b2.length) {
            for (int i = 0; i < a2.length; i++) {
                if (a2[i] > b2[i]) {
                    return true;
                } else {
                    return false;
                }
            }
        }else if (a2.length > b2.length){
            for (int i = 0; i < b2.length; i++) {
                if (a2[i] > b2[i]) {
                    return true;
                } else {
                    return false;
                }
            }
        }else {
            for (int i = 0; i < b2.length; i++) {
                if (a2[i] > b2[i]) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
