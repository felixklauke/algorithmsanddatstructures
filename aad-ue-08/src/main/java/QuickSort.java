import java.util.Arrays;

public class QuickSort {

    /**
     * To reverse:
     *
     * <pre>
     * 			while (array[currentLeft] < pivot) {
     * 				currentLeft++;
     *                        }
     *
     * 			while (array[currentRight] > pivot) {
     * 				currentRight--;
     *            }
     * </pre>
     *
     * @param array
     * @param links
     * @param rechts
     */
    public static void quicksort(int[] array, final int links, final int rechts) {
        int currentLeft = links;
        int currentRight = rechts;

        int pivot = array[currentLeft + (currentRight - currentLeft) / 2];

        while (currentLeft <= currentRight) {
            while (array[currentLeft] > pivot) {
                currentLeft++;
            }

            while (array[currentRight] < pivot) {
                currentRight--;
            }

            if (currentLeft <= currentRight) {
                int temp = array[currentLeft];
                array[currentLeft] = array[currentRight];
                array[currentRight] = temp;

                currentLeft++;
                currentRight--;
            }
        }

        if (links < currentRight) {
            quicksort(array, links, currentRight);
        }

        if (rechts > currentLeft) {
            quicksort(array, currentLeft, rechts);
        }
    }

    public static void main(String[] args) {
        int[] array = generateArray();

        System.out.println(Arrays.toString(array));
        quicksort(array, 0, 14);
        System.out.println(Arrays.toString(array));
    }

    private static int[] generateArray() {
        int[] array = new int[15];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 50);
        }

        return array;
    }
}