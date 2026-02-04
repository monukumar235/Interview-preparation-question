package mockdsa;

public class AddTwoLists {
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
        ListNode l1= new ListNode(9);
//        l1.next = new ListNode(2);
//        l1.next.next=new ListNode(3);

        ListNode l2 = new ListNode(9);
//        l2.next=new ListNode(5);
//        l2.next.next=new ListNode(6);

        ListNode twoLinkedlist = addTwoLinkedlist(l1, l2);
        printList(twoLinkedlist);
    }

    private static void printList(ListNode twoLinkedlist) {
        while (twoLinkedlist!=null)
        {
            System.out.print(twoLinkedlist.val+"->");
            twoLinkedlist=twoLinkedlist.next;
        }
    }

    private static ListNode addTwoLinkedlist(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1!=null || l2!=null || carry!=0){
            int a = l1!=null ? l1.val : 0;
            int b = l2!=null ? l2.val : 0;
            int sum = a+b+carry;
            carry=sum/10;
            curr.next=new ListNode(sum%10);
            curr=curr.next;
            if(l1!=null)
            {
                l1=l1.next;
            }
            if(l2!=null)
            {
                l2=l2.next;
            }
        }
        return dummy.next;
    }
}
