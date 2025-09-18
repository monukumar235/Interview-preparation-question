package mockdsa;

import java.util.Stack;

public class Backspace {
    public static void main(String[] args) {
        String s="ab#c";
        String t="ad#c";
        System.out.println(compareStrs(s,t));
    }

    private static boolean compareStrs(String s, String t) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) =='#' && !s1.isEmpty()){
                s1.pop();
            } else if (s.charAt(i)!='#') {
                s1.push(s.charAt(i));
            }

        }
        for(int i=0;i<t.length();i++){
            if(t.charAt(i) =='#' && !s2.isEmpty()){
                s2.pop();
            } else if (t.charAt(i)!='#') {
                s2.push(t.charAt(i));
            }
        }

        return  s1.equals(s2);
    }
}
