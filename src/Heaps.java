import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class Heaps {
    public static List<Point> kClosest(Point[] points, int k) {
        Queue<Point> heap = new PriorityQueue<>((point1, point2) -> point2.distance() - point1.distance());

        heap.addAll(Arrays.asList(points).subList(0, k));

        for (int i = k; i < points.length; i++) {
            if (points[i].distance() < heap.peek().distance()) {
                heap.poll();
                heap.add(points[i]);
            }
        }

        return new ArrayList<>(heap);
    }

    public static List<Integer> topKFrequent(int[] arr, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        // Each array contains a number and its frequency
        PriorityQueue<Map.Entry<Integer, Integer>> topK = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            topK.add(entry);

            if (topK.size() > k) {
                topK.poll();
            }
        }

        return topK.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    static class MedianOfStream {
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

    public static class Point {
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
}
