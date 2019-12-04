import java.util.List;

public class Exp35 {
    public static ListNode reverse(ListNode head) {
        // write your code here;
        ListNode p = head;

        ListNode param = null;
        ListNode result = null;
        while (p!=null) {

            param = new ListNode(p.val);
            param.next = result;
            result = param;
            p = p.next;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode a0 = new ListNode(0);
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        a0.next = a1;
        a1.next = a2;
        a2.next = a3;
        ListNode p = reverse(a0);
        while (p!=null){
            System.out.print(p.val+">>");
            p = p.next;
        }
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
        next = null;
    }
}

