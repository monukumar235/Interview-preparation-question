package singleton;

public class practise {
    public static class NodeList{
        int val;
        NodeList next;

        public NodeList(){

        }
        public  NodeList(int val,NodeList next){
            this.val=val;
            this.next=next;
        }
        public NodeList(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        NodeList nodeList = new NodeList(4);
        NodeList nodeList1 = new NodeList(2);
        NodeList nodeList2 = new NodeList(6);

        nodeList.next = nodeList1;
        nodeList1.next =nodeList2;

        NodeList list = new NodeList(2);
        NodeList list1 = new NodeList(4);
        NodeList list2 = new NodeList(5);

        list.next = list1;
        list1.next=list2;

        NodeList results = findSum(nodeList, list);
        while (results!=null) {
            System.out.print(results.val + " " );
            results = results.next;
        }
    }

    private static NodeList findSum(NodeList l1, NodeList l2) {
        NodeList dummy = new NodeList(0);
        NodeList curr = dummy;
        int carry =0;
        while (l1!=null || l2!=null || carry!=0){
            int a = l1!=null ? l1.val : 0;
            int b = l2!=null ? l2.val : 0;
            int sum = a+b+carry;
            carry=sum/10;
            curr.next = new NodeList(sum%10);
            curr=curr.next;
            if (l1 != null) {
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
        }
        return dummy.next;
    }
}
