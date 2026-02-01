package mockdsa;

import java.util.List;

public class ReverseLinkedList {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val=val;
        }
        ListNode(int val,ListNode next)
        {
            this.val=val;
            this.next=next;
        }
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next=new ListNode(1);
        head.next.next=new ListNode(2);
        head.next.next.next= new ListNode(3);
        printList(head);
        ListNode reverse = reverse(head);
        printList(reverse);
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev=null;
        ListNode curr =head;
        ListNode next =null;
        while (curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public static void printList(ListNode head) {
        while (head!=null){
            System.out.print(head.val+"->");
            head=head.next;
        }
        System.out.println();
    }
}
