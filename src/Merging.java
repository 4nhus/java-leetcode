import java.util.List;

public class Merging {
    public static LinkedListNode mergeTwoLists(LinkedListNode head1, LinkedListNode head2) {
        LinkedListNode dummy = new LinkedListNode(0);
        LinkedListNode prev = dummy;

        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                prev.next = head1;
                head1 = head1.next;
            } else {
                prev.next = head2;
                head2 = head2.next;
            }
            prev = prev.next;
        }

        if (head1 != null) {
            prev.next = head1;
        } else if (head2 != null) {
            prev.next = head2;
        }

        return dummy.next;
    }

    public static LinkedListNode mergeKLists(List<LinkedList> lists) {
        if (lists.isEmpty()) {
            return null;
        }

        if (lists.size() % 2 == 1) {
            lists.add(new LinkedList());
        }

        int step = 1;

        while (step < lists.size()) {
            for (int i = 0; i < lists.size(); i += step * 2) {
                lists.get(i).head = mergeTwoLists(lists.get(i).head, lists.get(i + step).head);
            }

            step *= 2;
        }

        return lists.get(0).head;
    }

    public static class LinkedListNode {
        public int data;
        public LinkedListNode next;

        // Constructor will be used to make a LinkedListNode type object
        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class LinkedList {
        public LinkedListNode head;

        // LinkedList() will be used to make a LinkedList type object.
        public LinkedList() {
            this.head = null;
        }

        // insert_node_at_head method will insert a LinkedListNode at head
        // of a linked list.
        public void insertNodeAtHead(LinkedListNode node) {
            if (this.head == null) {
                this.head = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
        }

        // create_linked_list method will create the linked list using the
        // given integer array with the help of InsertAthead method.
        public void createLinkedList(List<Integer> lst) {
            for (int i = lst.size() - 1; i >= 0; i--) {
                LinkedListNode newNode = new LinkedListNode(lst.get(i));
                insertNodeAtHead(newNode);
            }
        }
    }
}
