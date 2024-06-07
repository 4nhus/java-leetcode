public class LinkedLists {
    public static class LinkedListNode {
        public int data;
        public LinkedListNode next;
        // LinkedListNode() will be used to make a LinkedListNode type object.
        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static LinkedListNode reverse(LinkedListNode head) {
        LinkedListNode prev = null;
        LinkedListNode curr = head;
        LinkedListNode next = head.next;

        while (next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }

        curr.next = prev;
        head = curr;

        return head;
    }

    public static LinkedListNode removeNthLastNode(LinkedListNode head, int n) {
        LinkedListNode end = head, curr = head, dummy = new LinkedListNode(0), prev = dummy;
        dummy.next = head;
        for (int i = 0; i < n; i++) {
            end = end.next;
        }

        while (end != null) {
            prev = curr;
            curr = curr.next;
            end = end.next;
        }

        prev.next = curr.next;

        return dummy.next;
    }
}
