package mockdsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLinkedList {
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
        ListNode l1= new ListNode(1);
        l1.next=new ListNode(2);
        l1.next.next=new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next=new ListNode(3);
        l2.next=new ListNode(5);

        ListNode l3 = new ListNode(3);
        l3.next=new ListNode(6);

        List<ListNode>lists = new ArrayList<>(Arrays.asList(l1,l2,l3));

        ListNode listNode = mergeKLists(lists);
        printLists(listNode);
    }

    private static void printLists(ListNode listNode) {
        while (listNode!=null)
        {
            System.out.print(listNode.val);
            listNode=listNode.next;
            System.out.print("->");
        }
    }

    private static ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a,b)->Integer.compare(a.val,b.val));
        for(ListNode  list :lists)
        {
            heap.add(list);
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!heap.isEmpty())
        {
            ListNode smallest = heap.poll();
            curr.next=smallest;
            curr=curr.next;
            if(smallest.next!=null){
                heap.add(smallest.next);
            }
        }
        return dummy.next;
    }
}
