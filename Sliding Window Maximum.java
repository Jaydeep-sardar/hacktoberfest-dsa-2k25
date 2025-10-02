import java.util.*;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new LinkedList<>(); // store indices

        for (int i = 0; i < n; i++) {
            // Remove indices out of window
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove smaller elements from rear
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // Add current index
            dq.offerLast(i);

            // Store result once window is ready
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum solver = new SlidingWindowMaximum();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] result = solver.maxSlidingWindow(nums, k);

        System.out.print("Output: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }
}
