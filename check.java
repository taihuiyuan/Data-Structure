public class check{
    public static void main(String[] args){
        check a = new check();
        String str = "final";
        System.out.println("INPUT: "+str);
        System.out.println(a.check(str));
        String str1 = "Final";
        System.out.println("INPUT: "+str1);
        System.out.println(a.check(str1));
        String str2 = "FINAL";
        System.out.println("INPUT: "+str2);
        System.out.println(a.check(str2));
    }
    public String check(String in) {
        int k1 = 5;//v0
        int k2 = 9;//v1

        int len = in.length();//v2
        String out = "";//v8

        char[] b = new char[]{'a', 'A'};//v4
        try {
            int d = 1 / (len-9);//v5
            for (int i=0;i<len;i++){//:goto_0,i>len时跳出循环
                int enc = in.toLowerCase().toCharArray()[i] - b[0];//v9
                out = new StringBuilder().append(out).append(String.valueOf((char)((k1*enc+k2)%26+b[1]))).toString();//v10
            }
        }catch (Exception e){//:catch_0,:catch_1
            //:goto_1
            char[] c = new char[]{'e','r','r'};
            return String.valueOf(c);
        }
        return out;//:cond_0,:goto_2
    }
}