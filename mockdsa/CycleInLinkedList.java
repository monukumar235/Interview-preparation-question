package mockdsa;

import java.util.List;

public class CycleInLinkedList {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val=val;
        }
        ListNode(int val,ListNode next){
            this.val=val;
            this.next=next;
        }
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode secondNode = new ListNode(2);
        head.next=secondNode;
        head.next.next= new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=secondNode;

        System.out.println(hasCycle(head));
    }

    private static boolean hasCycle(ListNode head) {
        ListNode slow= head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
            {
                return true;
            }
        }
        return false;
    }
}
