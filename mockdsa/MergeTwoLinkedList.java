package mockdsa;

public class MergeTwoLinkedList {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val)
        {
            this.val=val;
            this.next=null;
        }
        ListNode(int val,ListNode next)
        {
            this.val=val;
            this.next=next;
        }
    }
    public static void main(String[] args) {
        ListNode list1= new ListNode(1);
        list1.next= new ListNode(2);
        list1.next.next=new ListNode(4);
//        printList(list1);

        ListNode list2 = new ListNode(1);
        list2.next= new ListNode(3);
        list2.next.next= new ListNode(5);
//        printList(list2);
        ListNode mergeTwoLists = mergeTwoLists(list1, list2);
        printList(mergeTwoLists);
    }

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (list1!=null && list2!=null){
            if(list1.val<list2.val){
                curr.next=list1;
                list1=list1.next;
            }else{
                curr.next=list2;
                list2=list2.next;
            }
            curr=curr.next;
        }
        if(list1!=null){
            curr.next=list1;
        }
        if(list2!=null)
        {
            curr.next=list2;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        while (head!=null){
            System.out.print(head.val);
            head=head.next;
            System.out.print("->");
        }
        System.out.println();
    }
}
