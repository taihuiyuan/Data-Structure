package pj1;

import java.io.*;

public class RBtree {
    //节点颜色
    private final boolean red = false;
    private final boolean black = true;

    private final Node nil= new Node(null, null, black, null, null, null);//叶节点
    private Node root = nil;//根节点

    RBtree(){
        nil.parent = nil;
        nil.leftChild = nil;
        nil.rightChild = nil;
    }

    private static class Node{
        private String key;
        private String value;

        private Node parent;
        private Node leftChild;
        private Node rightChild;

        private boolean color;

        Node(String key,String value,boolean color,Node parent,Node leftChild,Node rightChild){
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    /**
     * 一些辅助方法
     */

//左旋
    private void left_rotate(RBtree T,Node x){
        Node y = x.rightChild;
        x.rightChild = y.leftChild;//turn y's left subtree into x's right subtree

        if (y.leftChild != T.nil){
            y.leftChild.parent = x;
        }

        y.parent = x.parent;//link x's parent to y

        if (x.parent == T.nil){
            T.root = y;
        }else if (x == x.parent.leftChild){
            x.parent.leftChild = y;
        }else {
            x.parent.rightChild = y;
        }

        y.leftChild = x;//put x on y's left
        x.parent = y;
    }

//右旋
    private void right_rotate(RBtree T,Node x){
        Node y = x.leftChild;
        x.leftChild = y.rightChild;

        if (y.rightChild != T.nil){
            y.rightChild.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == T.nil){
            T.root = y;
        }else if (x == x.parent.leftChild){
            x.parent.leftChild = y;
        }else {
            x.parent.rightChild = y;
        }

        y.rightChild = x;
        x.parent = y;
    }

//寻找最小元素
    private Node minimum(Node x){
        //一直往左走
        while (x.leftChild != nil){
            x = x.leftChild;
        }
        return x;
    }

//寻找后继
    private Node successor(Node x){
        if (x.rightChild != nil){
            return minimum(x.rightChild);
        }
        Node p = x.parent;
        while (p != null && p.rightChild == x){
            x = p;
            p = p.parent;
        }
        return p;
    }

//比较两个字符串大小
    private boolean compareString(String x,String y){
        x = x.toLowerCase();
        y = y.toLowerCase();

        if (x.compareTo(y) < 0){
            return true;
        }else {
            return false;
        }
    }
//判断是否是黑色
    private boolean isBlack(Node node){
        boolean isBlack = false;
        if (node == this.nil || node.color == black){
            isBlack = true;
        }
        return isBlack;
    }

//用v子树替换u子树并成为其双亲的孩子节点
    private void transplant(RBtree T,Node u,Node v){
        if (u.parent == T.nil) {
            T.root = v;
        }else if (u == u.parent.leftChild){
            u.parent.leftChild = v;
        }else {
            u.parent.rightChild = v;
        }
        v.parent = u.parent;
    }

    /**
     * 插入、删除及其修复操作
     */

//插入
    public void insert(String key,String value){
        if (search(key,this.root) == this.nil) {
            Node node = new Node(key, value, red, null, null, null);
            insertNode(this, node);
            System.out.println("insert successfully!");
        }else {
            System.out.println("Error:key conflict");
        }
    }

    private void insertNode(RBtree T,Node z){
        Node y = T.nil;
        Node x = T.root;

        while (x != T.nil){
            y = x;
            if (compareString(z.key,x.key)){
                x = x.leftChild;
            }else x = x.rightChild;
        }

        z.parent = y;

        if (y == T.nil){
            T.root = z;
        }else if (compareString(z.key,y.key)){
            y.leftChild =z;
        }else {
            y.rightChild = z;
        }

        z.leftChild = T.nil;
        z.rightChild = T.nil;
        z.color = red;
        if (z.parent != T.nil && !isBlack(z.parent)) {
            insert_fixup(T, z);
        }
    }

    private void insert_fixup(RBtree T,Node z){
        while (z.parent != T.nil && z.parent.parent != T.nil && !isBlack(z.parent)){
            if (z.parent == z.parent.parent.leftChild){
                Node y = z.parent.parent.rightChild;
                if (!isBlack(y)){//case 1
                    z.parent.color = black;
                    y.color = black;
                    z.parent.parent.color = red;
                    z = z.parent.parent;
                }else {
                    if (z == z.parent.rightChild){//case 2
                        z = z.parent;
                        left_rotate(T,z);
                    }
                    //case 3
                    z.parent.color = black;
                    z.parent.parent.color = red;
                    right_rotate(T, z.parent.parent);
                }
            }else {
                Node y = z.parent.parent.leftChild;
                if (!isBlack(y)){//case 1
                    z.parent.color = black;
                    y.color = black;
                    z.parent.parent.color = red;
                    z = z.parent.parent;
                }else{
                    if (z == z.parent.leftChild) {//case 2
                        z = z.parent;
                        right_rotate(T, z);
                    }
                    //case 3
                    z.parent.color = black;
                    z.parent.parent.color = red;
                    left_rotate(T, z.parent.parent);
                }
            }
        }
        T.root.color = black;
    }

//删除
    public void delete(String key){
        Node node = search(key,this.root);
        if (node != this.nil){
            deleteNode(this,node);
            System.out.println("delete successfully!");
        }else {
            System.out.println("Error:key missing");
        }
    }

    private void deleteNode(RBtree T,Node z){
        Node y = z;//待删节点后继replace
        boolean y_original_color = y.color;
        Node x;//替代节点child

        if (z.leftChild == T.nil){
            x = z.rightChild;
            transplant(T,z,z.rightChild);
        }else if (z.rightChild == T.nil){
            x = z.leftChild;
            transplant(T,z,z.leftChild);
        }else {
            y = successor(z);
            y_original_color = y.color;
            x = y.rightChild;//y已经是右子树的最小值了
            if (y.parent == z){
                x.parent = y;
            }else {
                transplant(T,y,y.rightChild);
                y.rightChild = z.rightChild;
                y.rightChild.parent = y;
            }
            transplant(T,z,y);
            y.leftChild = z.leftChild;
            y.leftChild.parent = y;
            y.color = z.color;
        }

        if (y_original_color == black){
            delete_fixup(T,x);
        }
    }

    private void delete_fixup(RBtree T,Node x) {
        while (x != T.root && isBlack(x)) {
            if (x == x.parent.leftChild) {
                Node w = x.parent.rightChild;
                if (!isBlack(w)) {//case 1
                    w.color = black;
                    x.parent.color = red;
                    left_rotate(T, x.parent);
                    w = x.parent.rightChild;
                }

                if (isBlack(w.leftChild) && isBlack(w.rightChild)) {//case 2
                    w.color = red;
                    x = x.parent;
                } else {
                    if (isBlack(w.rightChild)) {//case 3
                        w.leftChild.color = black;
                        w.color = red;
                        right_rotate(T, w);
                        w = x.parent.rightChild;
                    }
                    //case 4
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.rightChild.color = black;
                    left_rotate(T, x.parent);
                    x = T.root;
                }
            } else {
                Node w = x.parent.leftChild;
                if (!isBlack(w)) {//case 1
                    w.color = black;
                    x.parent.color = red;
                    right_rotate(T, x.parent);
                    w = x.parent.leftChild;
                }

                if (isBlack(w.rightChild) && isBlack(w.leftChild)) {//case 2
                    w.color = red;
                    x = x.parent;
                } else {
                    if (isBlack(w.leftChild)) {//case 3
                        w.rightChild.color = black;
                        w.color = red;
                        left_rotate(T, w);
                        w = x.parent.leftChild;
                    }
                    //case 4
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.leftChild.color = black;
                    right_rotate(T, x.parent);
                    x = T.root;
                }
            }
        }
        x.color = black;
    }

//更新值
    public void PUT(String key,String value){
        if (search(key,this.root) != this.nil){
            search(key,this.root).value = value;
            System.out.println("update successfully!");
        }else {
            insert(key, value);
            System.out.println("There isn't this key.Insert it successfully!");
        }
    }

//搜索
    public String GET(String key){
        if (search(key,this.root) != this.nil){
            return search(key,this.root).value;
        }else {
            return "Error:key missing";
        }
    }
    private Node search(String key,Node x){
        while (x != this.nil && !key.equals(x.key)){
            if (compareString(key,x.key)){
                x = x.leftChild;
            }else {
                x = x.rightChild;
            }
        }
        return x;
    }

//打印红黑树
    public void DUMP(){
        printTree(this.root);
    }
    private void printTree(Node x){
        if (x != this.nil){
            printTree(x.leftChild);
            System.out.println(x.key+":"+x.value);
            printTree(x.rightChild);
        }
    }

//加载文件
    public void LOAD(String filename){
        File file = new File("C:\\Users\\22435\\IdeaProjects\\DS\\src\\pj1\\"+filename);
        InputStreamReader reader;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String key ="";
            while ((key=br.readLine()) != null){
                String value = br.readLine();
                if (search(key,this.root) == this.nil) {
                    Node node = new Node(key, value, red, null, null, null);
                    insertNode(this, node);
                }else {
                    search(key,this.root).value = value;
                }
            }
            System.out.println("读取文件成功！");
            br.close();
        } catch (IOException e) {
            System.out.println("读取文件失败！- 原因：文件路径错误或者文件不存在");
        }
    }
}
