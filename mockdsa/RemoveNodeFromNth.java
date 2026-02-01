package mockdsa;

import javax.swing.*;
import java.util.List;

public class RemoveNodeFromNth {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val)
        {
            this.val=val;
        }
        ListNode(int val,ListNode next)
        {
            this.val=val;
            this.next=next;
        }
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        int n=2;
        ListNode removeNthFromEnd = removeNthFromEnd(head, n);
        printList(removeNthFromEnd);
    }

    private static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;
        for(int i=0;i<n;i++){
            right = right.next;
        }
        if(right==null){
            return head.next;
        }
        while (right.next!=null)
        {
            left=left.next;
            right=right.next;
        }
        left.next=left.next.next;
        return head;
    }

    private static void printList(ListNode head) {
        while(head!=null)
        {
            System.out.print(head.val);
            head=head.next;
            System.out.print("->");
        }
//        System.out.print("->");
    }
}
