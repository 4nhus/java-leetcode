import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Intervals {
    public static int[][] insertInterval(int[][] existingIntervals, int[] newInterval) {
        List<int[]> newIntervals = new ArrayList<>();
        int n = existingIntervals.length;
        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        // Add existing intervals if their end times < newStart
        while (newIntervals.size() < existingIntervals.length && existingIntervals[newIntervals.size()][1] < newStart) {
            newIntervals.add(existingIntervals[newIntervals.size()]);
        }

        // Now append
        if (newIntervals.size() == existingIntervals.length) {
            newIntervals.add(newInterval);
        } else { // Or merge
            int[] mergedInterval = existingIntervals[newIntervals.size()];
            mergedInterval[0] = Math.min(mergedInterval[0], newStart);
            mergedInterval[1] = Math.max(mergedInterval[1], newEnd);
            newIntervals.add(mergedInterval);
        }

        // Iterate through remaining intervals and merge if required
        for (int i = newIntervals.size(); i < existingIntervals.length; i++) {
            if (existingIntervals[i][0] <= newIntervals.get(newIntervals.size() - 1)[1]) {
                int[] mergedInterval = newIntervals.get(newIntervals.size() - 1);
                mergedInterval[0] = Math.min(mergedInterval[0], existingIntervals[i][0]);
                mergedInterval[1] = Math.max(mergedInterval[1], existingIntervals[i][1]);
                newIntervals.remove(newIntervals.size() - 1);
                newIntervals.add(mergedInterval);
            } else {
                newIntervals.add(existingIntervals[i]);
            }
        }

        return newIntervals.toArray(new int[0][0]);
    }

    public static int[][] mergeIntervals(int[][] intervals) {
        LinkedList<int[]> mergedIntervals = new LinkedList<>();
        mergedIntervals.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= mergedIntervals.getLast()[1]) {
                int[] mergedInterval = intervals[i];

                while (!mergedIntervals.isEmpty() && mergedInterval[0] <= mergedIntervals.getLast()[1]) {
                    mergedInterval[0] = Math.min(mergedInterval[0], mergedIntervals.getLast()[0]);
                    mergedInterval[1] = Math.max(mergedInterval[1], mergedIntervals.getLast()[1]);
                    mergedIntervals.pollLast();
                }

                mergedIntervals.add(mergedInterval);
            } else {
                mergedIntervals.add(intervals[i]);
            }
        }

        return mergedIntervals.toArray(new int[0][0]);
    }
}
