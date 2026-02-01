package mockdsa;

import java.util.Stack;

public class ValidParan {
    public static void main(String[] args) {
        String s = "([{}])";
        System.out.println(isValid(s));
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(ch=='('){
                stack.push(')');
            }
            else if(ch=='{'){
                stack.push('}');
            }
            else if(ch == '['){
                stack.push(']');
            }
            else{
                if(stack.isEmpty() || ch!=stack.pop())
                {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
