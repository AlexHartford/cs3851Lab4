import java.util.Arrays;
import java.util.Stack;

/**
 * Iterative Single-Threaded QuickSort
 * Recursive QuickSort was giving me stackoverflow errors so here we are.
 * @author Alex Hartford
 * Course: CS3851-021
 * Assignment: Lab 4 - Parallel Sorting
 * Spring 2017-2018
 * 19 April 2018
 */
public class IQuickSort implements Algorithm {

    @Override
    public long sort(int[] A) {
        long startTime = System.nanoTime();
        iterativeQuicksort(A);
        System.out.println("[Iterative QuickSort] Sorted Array: " + Arrays.toString(A));
        return System.nanoTime() - startTime;
    }

    private void iterativeQuicksort(int[] array) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        stack.push(array.length - 1);
        while(!stack.isEmpty()) {
            int upper = stack.pop();
            int lower = stack.pop();
            if (upper - lower > 0) {
                int pivot = partition(array, lower, upper);

                stack.push(pivot + 1);
                stack.push(upper);

                stack.push(lower);
                stack.push(pivot - 1);
            }
        }
    }

    private int partition(int[] array, int lower, int upper) {

        swap(array, lower + ((upper - lower) / 2), upper);
        int pivot = array[upper];

        int i = lower - 1;
        for(int j = lower; j < upper; j++) {
            if(array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, upper);

        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}