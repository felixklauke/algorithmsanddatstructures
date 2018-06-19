import java.util.Arrays;

public class HeapSort {

    public static void versickere(int[] array, int zuversickern, int left, int right) {
        int largest = zuversickern;
        int leftChild = (largest - left) * 2 + left + 1;
        int rightChild = (largest - left) * 2 + left + 2;

        if (leftChild <= right && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        if (rightChild <= right && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        if (largest != zuversickern) {
            swapArrayElement(array, zuversickern, largest);
            versickere(array, largest, left, right);
        }
    }

    public static void heapsort(int[] array, int left, int right) {
        HeapSort.createHeap(array, left, right);

        System.out.println("====== PHASE I: HEAP CREATION ======");
        System.out.println(Arrays.toString(array));

        System.out.println("====== PHASE II: HEAPIFY");
        for (int i = right; i >= left + 1; i--) {
            swapArrayElement(array, left, i);
            versickere(array, left, left, i - 1);
        }
    }

    private static void createHeap(int[] array, int left, int right) {
        for (int i = right / 2 + 1; i >= left; i--) {
            versickere(array, i, left, right);
        }
    }

    private static void swapArrayElement(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }

    public static void main(String[] args) {
        int[] array = {Integer.MAX_VALUE, Integer.MAX_VALUE, 77, 7, 58, 88, 60, 41, 82, 76, 49, 86, -1, -1};

        heapsort(array, 2, 11);
        System.out.println(Arrays.toString(array));
    }
}