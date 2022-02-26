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
        //定义两个数组记录两个字符串中各个字符的数量
        int[] fre1 = new int[256];
        int[] fre2 = new int[256];

        //首先记录字符串t中的字符
        char[] s2 = T.toCharArray();
        for (int i=0;i<s2.length;i++){
            fre2[s2[i]]++;
        }

        //定义子串左端点为i，右端点为j，初始均设为0（数组的起始位置）
        //k表示子串中包含 字符串t中字符的数量
        int i=0,j=0,k=0;

        //result数组记录满足要求的最小子串的左右端点
        int[] result = {0,0};

        //定义min记录满足要求的最小子串的长度
        int min=S.length()+1;

        //循环开始，直到子串右端点j移动到字符串s的右端点
        while (j<S.length()){

            //记录当前新加入子串的字符，将其数量+1
            fre1[S.charAt(j)]++;

            //如果该字符在字符串t中存在，且子串中该字符的数量小于等于字符串t中该字符的数量，就将k加一(若大于说明子串中包含t中重复的字符，不对其计数)
            if (fre2[S.charAt(j)]>0&&fre1[S.charAt(j)]<=fre2[S.charAt(j)]) k++;
            j++;

            //如果k等于字符串t的长度，说明子串包含字符串t中所有的字符
            while (k==T.length()){

                //将当前子串长度与min进行比较，若当前长度更小，则更新min的值，并将左右端点更新到result
                if (j-i<min) {
                    result[0]=i;
                    result[1]=j;
                    min=j-i;
                }

                //左端点i向右移动，将子串中当前的字符数量-1
                fre1[S.charAt(i)]--;

                //如果当前字符为字符串t中的字符，且从子串中删去该字符后，使得子串不能包含字符串t中所有的字符，就将k减一，并回到原来的循环，继续将右端点j右移直到满足条件（包含t中所有的字符）
                if (fre2[S.charAt(i)]>0&&fre1[S.charAt(i)]<fre2[S.charAt(i)]) {
                    k--;
                }
                i++;
            }
        }

        //如果min未被更新过，说明字符串s中并不拥有满足条件的子串，返回一个空字符串。
        if (min==S.length()+1) return "";
            //否则，返回正确的子串（满足条件的最小子串）
        else return S.substring(result[0],result[1]);
    }
}
