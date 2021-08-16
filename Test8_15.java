class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Test8_15 {
    public String solve (String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(i));
        }
        return stringBuilder.reverse().toString();
    }

    public ListNode ReverseList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
//        ListNode next = cur.next;
//        while(cur.next != null){
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//            next = next.next;
//        }
//        cur.next = pre;
//        return cur;
        while(cur != null){
            ListNode next = cur.next;
            if(next == null){
                head = cur;
            }
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return head;
    }
}