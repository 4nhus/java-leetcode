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

    public static LinkedListNode reorderList(LinkedListNode head) {
        if (head.next == null) {
            return head;
        }

        // Split the list in two
        LinkedListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        LinkedListNode curr2 = slow.next, prev2 = null, next2 = curr2.next;
        slow.next = null;

        // Reverse second list
        while (curr2 != null) {
            next2 = curr2.next;
            curr2.next = prev2;
            prev2 = curr2;
            curr2 = next2;
        }
        curr2 = prev2;

        // Combine two lists
        LinkedListNode curr1 = head, next1 = head.next;

        while (curr1 != null && curr2 != null) {
            next1 = curr1.next;
            next2 = curr2.next;
            curr1.next = curr2;
            curr2.next = next1;
            curr2 = next2;
            curr1 = next1;
        }

        return head;
    }
}
