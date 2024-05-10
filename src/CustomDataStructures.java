import java.util.*;

public class CustomDataStructures {
    public static class LinkedList<T> {
        private LinkedListNode<T> head;
        private LinkedListNode<T> tail;
        private int length;

        public LinkedList() {
            this.head = null;
            this.tail = null;
        }

        public int size() {
            return this.length;
        }

        public void insertAtHead(T data) {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data);
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                newNode.next = this.head;
                this.head.prev = newNode;
                this.head = newNode;
            }
            this.length++;
        }

        public void insertAtTail(T data) {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data);
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                newNode.prev = this.tail;
                this.tail = newNode;
            }
            this.length++;
        }

        public void addFirst(LinkedListNode<T> newNode) {
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                newNode.next = this.head;
                this.head.prev = newNode;
                this.head = newNode;
            }
            this.length++;
        }

        public void addLast(LinkedListNode<T> newNode) {
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                newNode.prev = this.tail;
                this.tail = newNode;
            }
            this.length++;
        }

        public void remove(T data) {
            LinkedListNode<T> tmp = this.head;
            while (tmp != null) {
                if (tmp.data == data) {
                    this.remove(tmp);
                    return;
                }
                tmp = tmp.next;
            }
        }

        public void remove(LinkedListNode<T> node) {
            if (node == null)
                return;
            if (node.prev != null)
                node.prev.next = node.next;
            if (node.next != null)
                node.next.prev = node.prev;
            if (node == this.head)
                this.head = this.head.next;
            if (node == this.tail) {
                this.tail = this.tail.prev;
                if (this.tail != null)
                    this.tail.next = null;
            }
            this.length--;
            node = null;
        }

        public void removeFirst() {
            this.remove(this.head);
        }

        public void removeLast() {
            this.remove(this.tail);
        }

        public LinkedListNode<T> getFirst() {
            return this.head;
        }

        public LinkedListNode<T> getLast() {
            return this.tail;
        }
    }

    public static class LinkedListNode<T> {
        public T data;
        public LinkedListNode<T> next;
        public LinkedListNode<T> prev;

        public LinkedListNode(T dataVal) {
            this.data = dataVal;
            this.next = null;
            this.prev = null;
        }
    }

    public static class TimeStamp {
        Map<String, TreeMap<Integer, String>> map = new HashMap<>();

        // Set TimeStamp data variables
        public boolean setValue(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }

            map.get(key).put(timestamp, value);

            return true;
        }

        // Get the value for the given key and timestamp
        public String getValue(String key, int timeStamp) {
            return map.containsKey(key) ? map.get(key).get(map.get(key).floorKey(timeStamp)) : "";
        }


    }

    public static class MinStack {
        Deque<Integer> minStack = new ArrayDeque<>();
        Deque<Integer> allStack = new ArrayDeque<>();

        // Pop() removes and returns value from minStack
        public int pop() {
            minStack.removeLast();
            return allStack.removeLast();
        }

        // Pushes values into MinStack
        public void push(Integer value) {
            allStack.addLast(value);
            if (minStack.isEmpty() || value < minStack.peekLast()) {
                minStack.addLast(value);
            } else {
                minStack.addLast(minStack.peekLast());
            }
        }

        // returns minimum value in O(1)
        public int minNumber() {
            return minStack.peekLast();
        }
    }

    public static class LRUCache {
        int size;
        LinkedList<int[]> linkedList = new LinkedList<>();
        Map<Integer, LinkedListNode<int[]>> map = new HashMap<>();

        // Constructor that sets the size of the cache
        public LRUCache(int size) {
            this.size = size;
        }

        int get(int key) {
            if (map.containsKey(key)) {
                LinkedListNode<int[]> node = map.get(key);
                linkedList.remove(node);
                linkedList.addLast(node);
                return node.data[1];
            } else {
                return -1;
            }
        }

        void set(int key, int value) {
            LinkedListNode<int[]> node;

            // Update
            if (map.containsKey(key)) {
                node = map.get(key);

                linkedList.remove(node);

                node.data[1] = value;
            } else if (linkedList.size() == size) { // Evict
                LinkedListNode<int[]> evictee = linkedList.getFirst();
                map.remove(evictee.data[0]);
                linkedList.removeFirst();
                node = new LinkedListNode<>(new int[]{key, value});
                map.put(key, node);
            } else { // Add
                node = new LinkedListNode<>(new int[]{key, value});
                map.put(key, node);
            }

            // Add node to the end of the list
            linkedList.addLast(node);
        }
    }
}
