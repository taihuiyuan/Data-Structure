package pj1;

public class B_test {
    public static void main(String args[]) {
        Btree b = new Btree();
/*
        b.insert("independent","独立的");
        b.insert("test","测试");
        b.insert("active","积极的");
        b.insert("art","艺术");
        b.insert("PE","体育");
        b.insert("mum","ma");
        b.insert("ph","suan");*/
        //b.LOAD("windows.txt");
        //b.DUMP();
        //System.out.println(b.GET("boom"));
        //b.PUT("boom","zha");
        //System.out.println(b.GET("boom"));
        //b.delete("boom");
        //System.out.println(b.GET("boom"));
        //b.delete("test");
        //b.DUMP();
        //b.GET("PE");
        //System.out.println(b.GET("PE"));
        b.insert("F","F");
        b.insert("S","S");
        b.insert("Q","Q");
        b.insert("K","K");
        b.insert("C","C");
        b.insert("L","L");
        b.insert("H","H");
        b.insert("T","T");
        b.insert("V","V");
        b.insert("W","W");
        b.insert("M","M");
        b.insert("R","R");
        b.insert("N","N");
        b.insert("P","P");
        b.insert("A","A");
        b.insert("B","B");
        b.insert("X","X");
        b.insert("Y","Y");
        b.insert("D","D");
        b.insert("Z","Z");
        b.insert("E","E");

        b.delete("Y");
        b.delete("W");
        //b.delete("F");
        b.delete("Q");
        b.delete("X");
        b.delete("K");
        b.delete("B");
        b.delete("H");
        b.delete("P");
        b.DUMP();
    }
}
