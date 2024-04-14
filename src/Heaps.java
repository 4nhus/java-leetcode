import java.util.*;

public class Heaps {
    public static List<Point> kClosest(Point[] points, int k) {
        Queue<Point> heap = new PriorityQueue<>((point1, point2) -> point2.distance() - point1.distance());

        for (int i = 0; i < k; i++) {
            heap.add(points[i]);
        }

        for (int i = k; i < points.length; i++) {
            if (points[i].distance() < heap.peek().distance()) {
                heap.poll();
                heap.add(points[i]);
            }
        }

        return new ArrayList<>(heap);
    }

    public class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distance() {
            // Ignore square root
            return x * x + y * y;
        }
    }

    class MedianOfStream {
        Queue<Integer> topHalf = new PriorityQueue<>();
        Queue<Integer> bottomHalf = new PriorityQueue<>(Collections.reverseOrder());

        public void insertNum(int num) {
            if (topHalf.isEmpty() || num >= topHalf.peek()) {
                topHalf.add(num);
            } else {
                bottomHalf.add(num);
            }

            if (topHalf.size() - bottomHalf.size() > 1) {
                bottomHalf.add(topHalf.poll());
            } else if (bottomHalf.size() - topHalf.size() > 1) {
                topHalf.add(bottomHalf.poll());
            }
        }

        public double findMedian() {
            if (topHalf.size() == bottomHalf.size()) {
                return ((double) topHalf.peek() + (double) bottomHalf.peek()) / 2;
            } else if (topHalf.size() > bottomHalf.size()) {
                return topHalf.peek();
            } else {
                return bottomHalf.peek();
            }
        }
    }
}
