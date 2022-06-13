import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuadrupletsJavaChallenge {

    public static void main(String[] args) {

        for (int[] ints : new CombinationIterable(5, 3)) {
            System.out.println("ints = " + Arrays.toString(ints));
        }

        System.out.println(Arrays.deepToString(quadrupletsV1(new int[]{7, 6, 4, -1, 1, 2}, 16)));
        System.out.println(Arrays.deepToString(quadrupletsV2(new int[]{1, 2, 3, 4, 5, 6, 7}, 10)));
        System.out.println(Arrays.deepToString(quadrupletsV2(new int[]{5, -5, -2, 2, 3, -3}, 0)));
        System.out.println(Arrays.deepToString(quadrupletsV2(new int[]{-2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 4)));
    }

    // Simplest solution
    public static int[][] quadrupletsV1(int[] array, int targetSum) {

        if (array.length < 4) {
            return new int[0][];
        }

        List<int[]> list = new ArrayList<>(array.length);

        for (int first = 0; first < array.length - 3; first++) {
            for (int second = first + 1; second < array.length - 2; second++) {
                for (int third = second + 1; third < array.length - 1; third++) {
                    for (int fourth = third + 1; fourth < array.length; fourth++) {
                        if (array[first] + array[second] + array[third] + array[fourth] == targetSum) {
                            list.add(new int[]{array[first], array[second], array[third], array[fourth]});
                        }
                    }
                }
            }
        }

        return list.toArray(new int[list.size()][]);
    }

    public static int[][] quadrupletsV2(final int[] array, final int targetSum) {

        List<int[]> quadruplets = new ArrayList<>();

        final var groupSize = 4;

        for (int[] combination : new CombinationIterable(array.length, groupSize)) {
            var sum = 0;

            for (int index : combination) {
                sum += array[index];
            }

            if (sum == targetSum) {
                final var numbers = new int[groupSize];
                for (int i = 0; i < groupSize; i++) {
                    numbers[i] = array[combination[i]];
                }
                quadruplets.add(numbers);
            }
        }

        return quadruplets.toArray(new int[quadruplets.size()][]);
    }
}
