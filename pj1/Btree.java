package pj1;

import java.io.*;

public class Btree {
    private Node root;
    private int number;

    //树的节点
    private static class Node{
        private int t = 2;//每个节点最少有t个孩子
        private int minsize = t-1;//最小项数
        private int maxsize = 2*t-1;//最大项数

        private int size = 0;
        private Element[] elements = new Element[maxsize];//节点中元素
        private Node[] children = new Node[maxsize+1];//孩子节点
        private boolean leaf;//是否为叶节点

        Node(){
            leaf = false;
        }

        Node getChildInIndex(Node node, int index){
            if (node.leaf){
                return null;
            }else {
                return node.children[index];
            }
        }
    }

    //节点中的元素
    private static class Element{
        private String key;
        private String value;

        Element(String key,String value){
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 辅助功能
     */

    //比较两个字符串大小
    private boolean compareString(String x,String y){//x<y
        x = x.toLowerCase();
        y = y.toLowerCase();
        if (x.compareTo(y) < 0){
            return true;
        }else {
            return false;
        }
    }

    //获取节点的最小键
    private Element minimum(Node x){
        if (x == null){
            return null;
        }else {
            if (x.children[0] != null){
                return minimum(x.children[0]);
            }else {
                return x.elements[0];
            }
        }
    }

    //获取节点的最大键
    private Element maximum(Node x){
        if (x == null){
            return null;
        }else {
            if (x.children[x.size] != null){
                return maximum(x.children[x.size]);
            }else {
                return x.elements[x.size-1];
            }
        }
    }

    //获取节点在index处元素的前驱
    private Element presuccessor(Node x,int index){
        if (x.children[index] != null){
            return maximum(x.children[index]);
        }else {
            return null;
        }
    }

    //获取节点在index处元素的后继
    private Element successor(Node x,int index){
        if (x.children[index+1] != null){
            return minimum(x.children[index+1]);
        }else {
            return null;
        }
    }

    //分裂节点
    private Node split(Node x){
        int mid = x.size/2;
        Node leftNode = new Node();//分裂后的左子节点
        Node rightNode = new Node();//分裂后的右子节点
        Node parent = new Node();//分裂后的父节点

        for (int i = 0;i < mid;i++){
            leftNode.elements[i] = x.elements[i];
            leftNode.children[i] = x.children[i];
            rightNode.elements[i] = x.elements[i+mid+1];
            rightNode.children[i] = x.children[i+mid+1];
        }
        leftNode.children[mid] = x.children[mid];
        rightNode.children[x.size-mid-1] = x.children[x.size];
        leftNode.size = mid;
        rightNode.size = x.size-mid-1;

        leftNode.leaf = x.leaf;
        rightNode.leaf = x.leaf;

        //处理父节点
        parent.elements[0] = x.elements[mid];
        parent.children[0] = leftNode;
        parent.children[1] = rightNode;
        parent.leaf = false;
        parent.size = 1;

        return parent;
    }

    //合并父节点x中位于index节点
    private void LeftMerge(Node x, int index){//和它的左兄弟节点合并
        Node leftNode = x.children[index-1];
        Node rightNode = x.children[index];
        leftNode.elements[leftNode.size] = x.elements[index-1];//将父节点对应的键值对放入左节点

        //将rightNode中的键值对全部放入左节点
        for (int i=0;i<rightNode.size;i++){
            leftNode.elements[leftNode.size+1+i] = rightNode.elements[i];
        }
        //将rightNode中的children全部放入左节点
        for (int i=0;i<rightNode.size+1;i++){
            leftNode.children[leftNode.size+1+i] = rightNode.children[i];
        }
        leftNode.size = leftNode.size + 1 + rightNode.size;

        //更新父节点
        for (int i=index;i<x.size;i++){
            x.elements[i-1] = x.elements[i];
            x.children[i] = x.children[i+1];
        }
        x.elements[x.size-1] = null;
        x.children[x.size] = null;
        x.size--;

    }

    private void RightMerge(Node x, int index){//和它的右兄弟节点合并
        LeftMerge(x, index + 1);
    }

    //向兄弟节点借键
    private void rightKey(Node x, int index){//向右兄弟借键
        Node rightNode = x.children[index+1];//获取右节点
        Node rightChildren = rightNode.children[0];//获取被借的键的最小儿子

        //处理左节点
        Node leftNode = x.children[index];
        leftNode.elements[leftNode.size] = x.elements[index];//将原来的父节点加到左节点最高位
        leftNode.size++;
        leftNode.children[leftNode.size] = rightChildren;//将被借的键的最小儿子加到左节点

        //处理父节点
        x.elements[index] = rightNode.elements[0];

        //处理右节点
        for(int i=1;i<rightNode.size;i++){
            rightNode.elements[i-1] = rightNode.elements[i];
            rightNode.children[i-1] = rightNode.children[i];
        }
        rightNode.elements[rightNode.size-1] = null;
        rightNode.children[rightNode.size-1] = rightNode.children[rightNode.size];
        rightNode.children[rightNode.size] = null;
        rightNode.size--;

    }

    private void leftKey(Node x, int index){//向左兄弟节点借键
        Node leftNode = x.children[index-1];//获取左节点
        Node leftChildren = leftNode.children[leftNode.size];//获取被借的键的最大儿子

        //处理右节点
        Node rightNode = x.children[index];
        for (int i=rightNode.size;i>0;i--){
            rightNode.elements[i] = rightNode.elements[i-1];
            rightNode.children[i+1] = rightNode.children[i];
        }
        rightNode.children[1] = rightNode.children[0];
        rightNode.elements[0] = x.elements[index];//将原来的父节点加到右节点最低位
        rightNode.size++;
        rightNode.children[0] = leftChildren;//将被借的键的最小儿子加到左节点

        //处理父节点
        x.elements[index-1] = leftNode.elements[leftNode.size-1];

        //处理左节点
        leftNode.elements[leftNode.size-1] = null;
        leftNode.children[leftNode.size] = null;
        leftNode.size--;

    }

    /**
     * 待实现功能
     */

    //搜索
    public String GET(String key){
        if (search(key) == null){
            return "Error:key missing";
        }else {
            return search(key).value;
        }
    }

    private Element search(String key){
        Node x = this.root;
        while (x != null && x.size != 0){
            int index = searchNode(key,x);
            if (index < x.size && key.equals(x.elements[index].key)){
                return x.elements[index];
            }else {
                x = x.children[index];
            }
        }
        return null;
    }

    private int searchNode(String key,Node x){//二分法
        int i=0;//左指针
        int j=x.size-1;//右指针
        while (i <= j){
            int mid = (j-i)/2+i;
            if (compareString(key,x.elements[mid].key)){
                j = mid-1;
            }else if (key.equals(x.elements[mid].key)){
                return mid;
            }else {
                i = mid+1;
            }
        }
        return i;
    }

    //插入
    public void insert(String key,String value){
        Element element = new Element(key,value);

        if (search(key) != null) {
            System.out.println("Error:key conflict");
        } else {
            this.root = insertNode(element,this.root);
            System.out.println("insert successfully!");
        }
    }
    private Node insertNode(Element element,Node x){//在节点中插入元素
        if (this.root == null){
            this.root = new Node();
            this.root.elements[0] = element;
            this.root.size = 1;
            this.root.leaf = true;
            number++;
            return this.root;
        }else {
            if (x.size == x.maxsize){
                x = split(x);//case 1
            }

            //若节点没有满，当前节点为叶节点时，直接插入；否则递归到下一个节点继续寻找插入
            int index = searchNode(element.key, x);
                if (x.leaf) {//case 2/4
                    for (int i = x.size; i > index; i--) {
                        x.elements[i] = x.elements[i - 1];
                    }
                    x.elements[index] = new Element(element.key, element.value);
                    x.size++;
                    number++;
                } else {
                    int j = 0;
                    while (j < x.size && compareString(x.elements[j].key, element.key)) {
                        j = j + 1;
                    }
                    Node node = insertNode(element,x.children[j]);
                    //返回的节点中只有一个元素时，说明返回的节点经过了分裂，需要将其合并到当前节点
                    if (node.size == 1) {//case 3
                        for (int i = x.size; i > index; i--) {
                            x.elements[i] = x.elements[i - 1];
                            x.children[i + 1] = x.children[i];
                        }
                        x.elements[index] = node.elements[0];
                        x.children[index] = node.children[0];
                        x.children[index + 1] = node.children[1];
                        x.size++;
                    }
                }
            return x;
        }

    }

    //删除
    public void delete(String key){
        if (search(key) != null){
            this.root = deleteNode(search(key),this.root);
            System.out.println("delete successfully!");
        }else {
            System.out.println("Error:key missing");
        }
    }

    private Node deleteNode(Element element,Node x){
            int index = searchNode(element.key, x);

            if (x.leaf) {//若是叶节点case 1
                if (index < x.size && element.key.equals(x.elements[index].key)) {
                    for (int i = index; i < x.size - 1; i++) {
                        x.elements[i] = x.elements[i + 1];
                    }
                    x.elements[x.size - 1] = null;
                    x.size--;
                    number--;
                }
            } else {//不是叶节点
                if (index < x.size && element.key.equals(x.elements[index].key)) {//key在内部节点中
                    if (x.children[index + 1].size > 1) {//case 2-a 将删除的节点原来的位置替换为后继
                        Element min = successor(x, index);
                        if (min != null) {
                            x = deleteNode(min, x);
                            index = searchNode(element.key, x);
                            x.elements[index] = min;
                        }else {
                            leftKey(x,index);
                            x = deleteNode(element, x);
                        }
                    } else if (x.children[index].size > 1) {//case 2-a 将删除的节点原来的位置替换为前驱
                        Element max = presuccessor(x, index);
                        if (max != null) {
                            x = deleteNode(max, x);
                            index = searchNode(element.key, x);
                            x.elements[index] = max;
                        }else {
                            rightKey(x,index);
                            x = deleteNode(element, x);
                        }
                    } else if (x.children[index].size == x.minsize && x.children[index + 1].size == x.minsize) {//case 2-b
                        RightMerge(x, index);
                        x = deleteNode(element, x);
                    }
                } else {
                    //若此节点没找到则递归删除
                    Node t = deleteNode(element, x.children[index]);

                    //若删除后节点数量小于minsize，则向sibling借键，若sibling键数小于等于minsize，则合并
                    if (t.size < t.minsize) {
                        if (index - 1 >= 0) {//若有左sibling
                            if (x.getChildInIndex(x, index - 1).size > x.minsize) {
                                leftKey(x, index);//case 3-a
                            } else {
                                LeftMerge(x, index);//case 3-b
                            }
                        } else if (index + 1 <= x.size) {//若有右sibling
                            if (x.getChildInIndex(x, index + 1).size > x.minsize) {
                                rightKey(x, index);//case 3-a
                            } else {
                                RightMerge(x, index);//case 3-b
                            }
                        }
                    }
                }
            }
        return x;
    }

    //更新值
    public void PUT(String key,String value){
        if (search(key) != null){
            search(key).value = value;
            System.out.println("update successfully!");
        }else {
            insert(key, value);
            System.out.println("There isn't this key.Insert it successfully!");
        }
    }

    //打印
    public void DUMP(){
        printTree(this.root);
    }
    private void printTree(Node x){
        Element[] elements = x.elements;

        if (x.leaf){
            for (int i=0;i<x.size;i++){
                if (elements[i] != null)
                    System.out.println(x.elements[i].key+":"+x.elements[i].value);
            }
        }else {
            for (int i=0;i<x.size;i++){
                printTree(x.children[i]);
                System.out.println(elements[i].key+":"+elements[i].value);
                if (i == x.size-1 && x.children[i+1] != null){
                    printTree(x.children[i+1]);
                }
            }
        }
    }

    //加载文件
    public void LOAD(String filename){
        File file = new File("C:\\Users\\22435\\IdeaProjects\\DS\\src\\pj1\\"+filename);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String key ="";
            while ((key=br.readLine()) != null){
                String value = br.readLine();
                if (search(key) == null) {
                    Element element = new Element(key,value);
                    this.root = insertNode(element,this.root);
                }else {
                    search(key).value = value;
                }
            }
            System.out.println("读取文件成功！");
            br.close();
        } catch (IOException e) {
            System.out.println("读取文件失败！- 原因：文件路径错误或者文件不存在");
        }
    }
}
