import java.util.*;
/**
 * 编写一个程序解决选择问题。令k=N/2,。画出表格显示程序对于N种不同的值的运行时间。
 *  1.审题：从N个数集合中，找出第K大的数的运行时间  N分别取值100000 200000 30000 400000
例如：{4,2,6,7}  找出第k大的数   k=N/2=2   第2大数就是 6
2.解题思路：
1}：数组a大小为N，填充随机数
2） 临时数组b大小为K，取数组a前k个数，去进行降序排列
3） 往后的数，跟第k个数c比较，小，舍弃，大，重新排序，去掉第K个数c
4） 计算耗时
 */

/* 编写带有下列声明的例程：
public void permute(String str);
public void permute(char[] str, int low, int high);
第一个例程是个驱动程序，它调用第二个例程并显示String str中字符的所有排列。
例如，str是"abc"， 那么输出的串则是abc，acb，bac，bca，cab，cba，第二个例程使用递归。
 */

public class homework1 {
    public static void main( String args[] ) {
        homework1 test = new homework1();
        test.test1();
        test.permute("abc");
    }

    //test1

    public void test1 () {
        int N = 10000;
        int[] a = creatArray(N);
        System.out.println(Arrays.toString(a));
        int kMax = getKMax(a, N / 2);
        System.out.println(kMax);
    }

    public int[] creatArray (int N){
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = new Random().nextInt(100);
        }
        return a;
    }

    public int getKMax ( int[] a, int K){
        long start = new Date().getTime();
        int[] b = new int[K];
        int kMax;
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            if (i == 0) {//模仿插入排序，保证其中新数组中必定含有1个元素
                b[i] = temp;
                continue;
            }
            if (i < K) {
                b[i] = temp;
                //插入排序
                for (int j = i - 1; j >= 0 && b[j + 1] > b[j]; j--) {
                    int tmp = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = tmp;
                }
            } else {
                //如果往后的数大于第K个数，则与第K个数互换，再进行排序（倒序）；小于等于，则跳过。
                if (temp > b[K - 1]) {
                    b[K - 1] = temp;
                    for (int j = K - 1; j > 0 && b[j] > b[j - 1]; j--) {
                        int tmp = b[j - 1];
                        b[j - 1] = b[j];
                        b[j] = tmp;
                    }
                } else {
                    continue;
                }
            }
        }
        long end = new Date().getTime();
        System.out.println("运行时间：" + (end - start) + "ms");
        System.out.println(Arrays.toString(b));
        return b[K - 1];
    }

//test2
    public void permute(String str) {
        permute(str.toCharArray(), 0, str.length());
    }

    // 递归
    public void permute(char[] str, int low, int high) {
        if (low == high - 1) {
            String s = "";
            for (int i = 0; i < high; i++) {
                s += str[i];
            }
            System.out.println(s);
        }
        for (int i = low; i < high; i++) {
            if (isSwap(str, low, i)) {
                swap(str, low, i);
                permute(str, low + 1, high);
                swap(str, low, i);
            }
        }
    }

    // 交换
    public void swap(char[] str, int a, int b) {
        if (str[a] == str[b])
            return;
        char c = str[a];
        str[a] = str[b];
        str[b] = c;
    }

    // a到b是否重复
    public boolean isSwap(char[] str, int a, int b) {
        for (int i = a; i < b; i++)
            if (str[i] == str[b])
                return false;
        return true;
    }
}


