import java.util.Arrays;

public class BasicSort {

    // Insertionsort:
    // Sortiere das Teilfeld von array beginnend mit Index links bis einschlie�lich Index rechts

    /**
     * To reverse: end > 1 && array[end - 1] < currentValue
     *
     * @param array      The array.
     * @param offsetLeft The offset / index left.
     * @param endRight   The rifht index.
     */
    public static void insertionsort(int[] array, int offsetLeft, int endRight) {
        for (int currentIndex = 1 + offsetLeft; currentIndex < endRight + 1; currentIndex++) {
            int currentValue = array[currentIndex];

            int end = currentIndex;

            while (end > 1 && array[end - 1] > currentValue) {
                array[end] = array[end - 1];
                end = end - 1;
            }

            array[end] = currentValue;
        }
    }

    // Selectionsort:
    // Sortiere das Teilfeld von array beginnend mit Index links bis einschlie�lich Index rechts

    /**
     * To reverse: Change min search to mac search and selection.
     *
     * @param array      The array.
     * @param offsetLeft The first index / offset.
     * @param endRight   The right index.
     */
    public static void selectionsort(int[] array, int offsetLeft, int endRight) {
        for (int currentIndex = offsetLeft; currentIndex < endRight + 1; currentIndex++) {
            int min = currentIndex;

            for (int currentSelection = currentIndex + 1; currentSelection < endRight + 1; currentSelection++) {
                if (array[currentSelection] < array[min]) {
                    min = currentSelection;
                }
            }

            int swap = array[min];
            array[min] = array[currentIndex];

            array[currentIndex] = swap;
        }
    }

    // Bubblesort:
    // Sortiere das Teilfeld von array beginnend mit Index links bis einschlie�lich Index rechts

    /**
     * To reverse, outer loop: for (int i = size - 1; i >= 0; i--)
     *
     * @param array      The array.
     * @param offsetLeft The offset / index left.
     * @param endRight   The right index.
     */
    public static void bubblesort(int[] array, int offsetLeft, int endRight) {
        boolean changed = true;

        int temp;

        while (changed) {
            changed = false;
            for (int i = offsetLeft; i < endRight; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    changed = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = generateArray();

        System.out.println("============= INSERTION SORT ============= ");

        System.out.println(Arrays.toString(array));
        insertionsort(array, 4, 14);
        System.out.println(Arrays.toString(array));

        System.out.println("============= SELECTION SORT ============= ");

        array = generateArray();
        System.out.println(Arrays.toString(array));
        selectionsort(array, 3, 11);
        System.out.println(Arrays.toString(array));

        System.out.println("============= BUBBLE SORT ============= ");

        array = generateArray();
        System.out.println(Arrays.toString(array));
        bubblesort(array, 2, 12);
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