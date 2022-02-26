package pj1;

public class RB_test {
    public static void main(String args[]){
        RBtree rb = new RBtree();

        rb.insert("independent","独立的");
        rb.insert("test","测试");
        rb.insert("active","积极的");
        rb.DUMP();
        System.out.println(rb.GET("active"));
        rb.PUT("active","活动的");
        rb.DUMP();
        rb.delete("active");
        rb.DUMP();
        /*

        rb.insert("j","10");
        rb.insert("b","2");
        rb.insert("l","12");
        rb.insert("d","4");
        rb.insert("f","6");
        rb.insert("h","8");
        rb.insert("a","1");
        rb.insert("i","9");
        rb.insert("g","7");
        rb.insert("c","3");
        rb.insert("k","11");
        rb.insert("e","5");

        rb.delete("c");
        rb.delete("b");
        rb.delete("i");
        rb.delete("d");
        rb.delete("j");
        rb.delete("k");
        rb.delete("h");*/

        /*rb.LOAD("words.txt");
        rb.DUMP();*/

        /*
        rb.insert("apd","as");
        rb.DUMP();
        rb.insert("apa","as");
        rb.DUMP();
        rb.insert("apb","as");
        rb.DUMP();*/
    }
}
