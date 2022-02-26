package pj1;

import java.util.Scanner;

public class test_control {
    private static boolean right = true;
    public static void main(String args[]){
        System.out.println("Please enter 'RBtree' to choose RB-tree or enter 'Btree' to choose B-tree.You can use 'Q' to quit it.");
        Scanner scanner = new Scanner(System.in);
        while (right){
            String tree = scanner.nextLine();
            switch (tree){
                case "RBtree":
                    RBtree_dictionary();
                    break;
                case "Btree":
                    Btree_dictionary();
                    break;
                case "Q":
                    System.out.println("see you next time!");
                    right = false;
                    break;
                default:
                    System.out.println("Please enter the right content");
                    break;
            }
        }
    }

    public static void RBtree_dictionary(){
        RBtree rbtree = new RBtree();
        System.out.println("Right operation:insert(),delete(),DUMP(),GET(),PUT(),LOAD()");
        Scanner input = new Scanner(System.in);
        while (right){
            System.out.println("Please enter your operation");
            String command = input.nextLine();

            if (command.equals("Q")){
                System.out.println("see you next time!");
                right = false;
            }
            else if (command.equals("DUMP()")){
                rbtree.DUMP();
            }
            else if (command.equals("insert()")){
                System.out.println("Please enter the English you want to insert:");
                Scanner key_input = new Scanner(System.in);
                String key = key_input.nextLine();
                if (key.equals("")){
                    System.out.println("English can't be null");
                }else {
                    System.out.println("Please enter the Chinese:");
                    Scanner value_input = new Scanner(System.in);
                    String value = value_input.nextLine();
                    rbtree.insert(key, value);
                }
            }
            else if (command.equals("delete()")){
                System.out.println("Please enter the English you want to delete:");
                Scanner key_input = new Scanner(System.in);
                String key = key_input.nextLine();
                if (key.equals("")){
                    System.out.println("English you delete can't be null");
                }else {
                    rbtree.delete(key);
                }
            }
            else if (command.equals("GET()")){
                System.out.println("Please enter the English you want to get:");
                Scanner key_input = new Scanner(System.in);
                String key = key_input.nextLine();
                if (key.equals("")){
                    System.out.println("English you get can't be null");
                }else {
                    System.out.println(rbtree.GET(key));
                }
            }
            else if (command.equals("PUT()")){
                System.out.println("Please enter the English you want to update:");
                Scanner key_input = new Scanner(System.in);
                String key = key_input.nextLine();
                if (key.equals("")){
                    System.out.println("English can't be null");
                }else {
                    System.out.println("Please enter the Chinese:");
                    Scanner value_input = new Scanner(System.in);
                    String value = value_input.nextLine();
                    rbtree.PUT(key,value);
                }
            }
            else if (command.equals("LOAD()")){
                System.out.println("Please enter the filename you want to load:");
                Scanner name_input = new Scanner(System.in);
                String name = name_input.nextLine();
                if (name.equals("")){
                    System.out.println("filename you load can't be null");
                }else {
                    rbtree.LOAD(name);
                }
            }
            else {
                System.out.println("Please enter the right content");
            }
        }
    }

    public static void Btree_dictionary() {
        Btree btree = new Btree();
        System.out.println("Right operation:insert(),delete(),DUMP(),GET(),PUT(),LOAD()");
        Scanner input = new Scanner(System.in);
        while (right) {
            System.out.println("Please enter your operation");
            String command = input.nextLine();

            if (command.equals("Q")) {
                System.out.println("see you next time!");
                right = false;
            } else if (command.equals("DUMP()")) {
                btree.DUMP();
            } else if (command.equals("insert()")) {
                System.out.println("Please enter the English you want to insert:");
                Scanner key_input = new Scanner(System.in);
                String key = key_input.nextLine();
                if (key.equals("")) {
                    System.out.println("English can't be null");
                } else {
                    System.out.println("Please enter the Chinese:");
                    Scanner value_input = new Scanner(System.in);
                    String value = value_input.nextLine();
                    btree.insert(key, value);
                }
            } else if (command.equals("delete()")) {
                System.out.println("Please enter the English you want to delete:");
                Scanner key_input = new Scanner(System.in);
                String key = key_input.nextLine();
                if (key.equals("")) {
                    System.out.println("English you delete can't be null");
                } else {
                    btree.delete(key);
                }
            } else if (command.equals("GET()")) {
                System.out.println("Please enter the English you want to get:");
                Scanner key_input = new Scanner(System.in);
                String key = key_input.nextLine();
                if (key.equals("")) {
                    System.out.println("English you get can't be null");
                } else {
                    System.out.println(btree.GET(key));
                }
            } else if (command.equals("PUT()")) {
                System.out.println("Please enter the English you want to update:");
                Scanner key_input = new Scanner(System.in);
                String key = key_input.nextLine();
                if (key.equals("")) {
                    System.out.println("English can't be null");
                } else {
                    System.out.println("Please enter the Chinese:");
                    Scanner value_input = new Scanner(System.in);
                    String value = value_input.nextLine();
                    btree.PUT(key, value);
                }
            } else if (command.equals("LOAD()")) {
                System.out.println("Please enter the filename you want to load:");
                Scanner name_input = new Scanner(System.in);
                String name = name_input.nextLine();
                if (name.equals("")) {
                    System.out.println("filename you load can't be null");
                } else {
                    btree.LOAD(name);
                }
            } else {
                System.out.println("Please enter the right content");
            }
        }
    }
}