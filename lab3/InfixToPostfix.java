package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式转化支持[]、（）、+、-、*、/、%、^、sin、cos、tan、arcsin、arccos、arctan，所输入数字需为非负数且表达式正确
 */
public class InfixToPostfix {
    public static void main(String[] args) {
        InfixToPostfix test = new InfixToPostfix();
        //String str = "1+2^3+(4*5+6)*7";
        //String str = "1-4*sin[2*(2*5+5)]";
        String str = "arcsin1+4*sin[2*(2*5+5)]";
        System.out.println("Infix:"+str);
        test.run(str);
    }

    public void run(String str){
        str = RemoveSpaces(str);
        List<String> postfix = ToPostfix(str);
        String pstr="";
        for (int i=0;i<postfix.size();i++){
            pstr += postfix.get(i)+" ";
        }
        System.out.println("Postfix:"+pstr);
        evaluation(postfix);
    }

    //获取运算符优先级
    private int getPriority(String ch){
        switch (ch){
            case "+":return 1;
            case "-":return 1;
            case "*":return 2;
            case "/":return 2;
            case "%":return 2;
            case "^":return 3;
            case "sin":return 4;
            case "cos":return 4;
            case "tan":return 4;
            case "arcsin":return 4;
            case "arccos":return 4;
            case "arctan":return 4;
            case "(":return 0;
            case ")":return 0;
            case "[":return 0;
            case "]":return 0;
            default:return -1;
        }
    }

    //转化为后缀表达式
    private List ToPostfix(String str){
        str=str.toLowerCase();
        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        int length = 0;
        for (int i=0;i < str.length();i++){
            char ch = str.charAt(i);
            String c = String.valueOf(ch);

            //sin、cos、tan、arcsin、arccos、arctan转化
            if (isAlpha(ch)) {
                if (ch == 's' && str.charAt(i + 1) == 'i' && str.charAt(i + 2) == 'n') {
                    c = "sin";
                } else if (ch == 'c' && str.charAt(i + 1) == 'o' && str.charAt(i + 2) == 's') {
                    c = "cos";
                } else if (ch == 't' && str.charAt(i + 1) == 'a' && str.charAt(i + 2) == 'n') {
                    c = "tan";
                } else if (ch == 'a' && str.charAt(i + 3) == 's' && str.charAt(i + 4) == 'i' && str.charAt(i + 5) == 'n') {
                    c = "arcsin";
                    i = i + 5;
                } else if (ch == 'a' && str.charAt(i + 3) == 'c' && str.charAt(i + 4) == 'o' && str.charAt(i + 5) == 's') {
                    c = "arccos";
                    i = i + 5;
                } else if (ch == 'a' && str.charAt(i + 3) == 't' && str.charAt(i + 4) == 'a' && str.charAt(i + 5) == 'n') {
                    c = "arctan";
                    i = i + 5;
                }
            }

                //记录数字长度
            if (ch>='0' && ch <='9' || ch == '.'){
                length++;
            }

            //最后一个数
            if (i == str.length()-1){
                postfix.add(str.substring(i-length+1,i+1));
                length=0;
            }

            //符号
            if (getPriority(c) != -1){
                //获取符号前一个数
                if (length>0){
                    postfix.add(str.substring(i-length,i));
                    length=0;
                }
                //将左中括号放入栈中
                if (ch=='['){
                    stack.push(c);
                }else if (ch == ']'){
                    while (!stack.peek().equals("[")){
                        postfix.add(stack.pop());

                    }
                    stack.pop();
                }
                else{
                    //将左括号放入栈中
                    if (ch=='('){
                        stack.push(c);
                    }else if (ch == ')'){
                        while (!stack.peek().equals("(")){
                            postfix.add(stack.pop());
                        }
                        stack.pop();
                    }
                    // 若取得不是括号内的元素，并且当前栈顶元素的优先级>=对比元素 那就出栈插入队列
                    else{
                        while (stack.size()!=0 && getPriority(c) <= getPriority(stack.peek())) {
                            postfix.add(stack.peek());
                            stack.pop();
                        }
                        stack.push(c);
                    }
                }
            }
        }
        while (!stack.isEmpty()){
            postfix.add(stack.peek());
            stack.pop();
        }
        return postfix;
    }

    //后缀表达式计算
    private void evaluation(List postfix){
        Stack<Double> stack2 = new Stack<>();

        for (int i=0;i < postfix.size();i++){
            String pstr = postfix.get(i).toString();
            if (isNumber(pstr)){
                double n = Double.parseDouble(pstr);
                stack2.push(n);
            }else {
                if (getPriority(pstr) != -1){
                    double a = stack2.pop();
                    if (pstr.equals("sin")){
                        stack2.push(Math.sin(a*Math.PI/180));
                    }else if (pstr.equals("cos")){
                        stack2.push(Math.cos(a*Math.PI/180));
                    }else if (pstr.equals("tan")){
                        stack2.push(Math.tan(a*Math.PI/180));
                    }else if (pstr.equals("arcsin")){
                        stack2.push(Math.asin(a));
                    }else if (pstr.equals("arccos")){
                        stack2.push(Math.acos(a));
                    }else if (pstr.equals("arctan")){
                        stack2.push(Math.atan(a));
                    }else {
                        double b = stack2.pop();
                        switch (pstr) {
                            case "+":
                                stack2.push(b + a);
                                break;
                            case "-":
                                stack2.push(b - a);
                                break;
                            case "*":
                                stack2.push(b * a);
                                break;
                            case "/":
                                stack2.push(b / a);
                                break;
                            case "%":
                                stack2.push(b % a);
                                break;
                            case "^":
                                stack2.push(Math.pow(b, a));
                                break;
                        }
                    }
                }
            }
        }
        double result = stack2.pop();
        result = (double)Math.round(result*100)/100;//结果四舍五入到小数点后两位
        System.out.println("Result:"+result);
    }

    //判断是否为数字
    private boolean isNumber(String str){
        for (int i=0;i < str.length();i++){
            char ch = str.charAt(i);
            if (Character.isDigit(ch) || ch == '.'){
                return true;
            }
        }
        return false;
    }

    //判断是否为字母
    private boolean isAlpha(char ch){
        String str = String.valueOf(ch);
        return str.matches("[a-zA-Z]+");
    }

    //去掉表达式中的空格
    private String RemoveSpaces(String str){
        String str_removed = "";
        for (int i=0;i < str.length();i++){
            char ch = str.charAt(i);
            if (ch!=' '){
                str_removed += String.valueOf(ch);
            }
        }
        return str_removed;
    }
}
