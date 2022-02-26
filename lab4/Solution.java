package lab4;

import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        String S1 = "ASODFNOASF";
        String T1 = "AA";
        System.out.println("\""+search(S1,T1)+"\"");
        String S2 = "ADOBECODEBANC";
        String T2 = "ABC";
        System.out.println("\""+search(S2,T2)+"\"");
        String S3 = "A";
        String T3 = "AA";
        System.out.println("\""+search(S3,T3)+"\"");
    }

    public static String search(String S,String T){
        //定义两个哈希表记录子串和字符串T各个字符的数量
        HashMap<Character,Integer> map1 = new HashMap();
        HashMap<Character,Integer> map2 = new HashMap();

        int start = 0;//子串左端点
        int end = 0;//子串右端点
        int count = 0;//子串中包含T中字符的数量
        int minlength = S.length()+1;//记录最小子串长度

        char[] s1 = S.toCharArray();
        char[] s2 = T.toCharArray();

        //将字符串T各个字符数量写进哈希表
        for (int i = 0;i < s2.length;i++){
            if (map2.containsKey(s2[i])){
                map2.put(s2[i],map2.get(s2[i])+1);
            }else {
                map2.put(s2[i],1);
            }
        }

        //记录最小子串的左右端点
        int i = 0;
        int j = 0;

        //循环开始，直到子串右端点向右移动到字符串S的右端点
        while (end < S.length()){
            char ch_right = s1[end];//子串右端点

            //记录当前新加入子串的字符，将哈希表内对应数量+1
            if (map1.containsKey(ch_right)){
                map1.put(ch_right,map1.get(ch_right)+1);
            }else {
                map1.put(ch_right,1);
            }

            //如果该字符在字符串T中存在，且子串中该字符的数量小于等于字符串T中该字符的数量，就将count加一(若大于说明子串中包含T中重复的字符，不对其计数)
            if (map2.containsKey(ch_right) && map1.get(ch_right)<=map2.get(ch_right)){
                count++;
            }

            //右端点向右移动
            end++;

            //若子串包含所有字符串T中元素
            while (count == T.length()){
                char ch_left = s1[start];//子串左端点

                //将当前子串长度与minlength进行比较，若当前长度更小，则更新minlength的值
                if (end-start < minlength){
                    i = start;
                    j = end;
                    minlength = end-start;
                }

                //左端点向右移动，将子串中当前的字符数量-1
                map1.put(ch_left,map1.get(ch_left)-1);

                //如果当前字符为字符串T中的字符，且从子串中删去该字符后，使得子串不能包含字符串T中所有的字符，就将count减一，并回到原来的循环，继续将右端点右移直到包含T中所有的字符
                if (map2.containsKey(ch_left) && map1.get(ch_left)<map2.get(ch_left)){
                    count--;
                }
                start++;
            }
        }

        //循环结束，若minlength未被更新过，说明字符串S中并不拥有满足条件的子串，返回一个空字符串，否则，返回满足条件的最小子串。
        if (minlength == S.length()+1){
            return "";
        }else {
            return S.substring(i,j);
        }
    }
}
