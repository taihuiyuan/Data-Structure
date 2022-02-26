package lab1;

class MyLinkedList {

    private int size;
    private Node head,tail;

    public static void main(String args[] ) {
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.addAtTail(3);
        obj.addAtIndex(1,2);

        System.out.println(obj.get(1));
        obj.deleteAtIndex(1);
        System.out.println(obj.get(1));
    }

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new Node(-1,null);
        tail = head;
        size = 0;
    }

    private class Node{
        int val;
        Node next;
        Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size)
            return -1;
        Node current = head;
        for (int i=0;i < index;i++){
            current = current.next;
        }
        return current.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node p = new Node(val,null);
        if (head == null){
            head = p;
            tail = p;
        }else {
            p.next = head;
            head = p;
        }
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node p = new Node(val,null);
        if (head == null){
            head = p;
            tail = p;
        }else {
            tail = head;
            for (int i=0;i < size-1;i++){
                tail = tail.next;
            }
            tail.next = p;
            tail = p;
        }
       size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }else if (index < 0){
            addAtHead(val);
        }else if (index == size){
            addAtTail(val);
        }else {
            Node prev = head;
            for (int i = 0;i < index-1;i++){
                prev = prev.next;
            }
            Node p = new Node(val,null);
            p.next = prev.next;
            prev.next = p;
            size++;
        }
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size)
            return;
        Node prev = head;
        for (int i = 0;i < index-1;i++){
            prev = prev.next;
        }
        prev.next = prev.next.next;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 **/