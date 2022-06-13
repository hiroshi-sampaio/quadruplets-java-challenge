import java.util.Arrays;
import java.util.Iterator;

/**
 * <p>Iterates over all the combinations of r numbers of a set S of n numbers. The set S will always contain all
 * numbers from 0 to n-1 (S = { x E Z | 0 <= x < n }). This iterator was created specifically to give you all the
 * combinations of Java array indexes.
 * <p>r must be <= n
 */
public class CombinationIterable implements Iterable<int[]> {
    private final int n, r;

    public CombinationIterable(int n, int r) {
        if (1 <= r && r <= n) {
            this.n = n;
            this.r = r;
        } else {
            throw new RuntimeException(); // TODO better exception
        }
    }

    @Override
    public Iterator<int[]> iterator() {
        return new CombinationIterator(n, r);
    }

    private static class CombinationIterator implements Iterator<int[]> {

        private final int n;
        private final int[] walkers;
        private boolean hasNext;

        public CombinationIterator(int n, int r) {
            this.n = n;

            // Initialize walkers
            this.walkers = new int[r];

            // Set initial walkers positions
            for (int i = 0; i < r; i++) {
                this.walkers[i] = i;
            }

            // At least 1 combination will exist
            hasNext = true;
        }

        private void walk() {
            var somebodyWalked = false;

            for (int i = walkers.length - 1; i >= 0; i--) {
                if (walkers[i] < n - walkers.length + i) {
                    walkers[i]++;
                    for (int j = i + 1; j < walkers.length; j++) {
                        walkers[j] = walkers[j - 1] + 1;
                    }
                    somebodyWalked = true;
                    break;
                }
            }

            hasNext = somebodyWalked;
        }

        @Override
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override
        public int[] next() {
            final var next = Arrays.copyOf(walkers, walkers.length);
            walk();
            return next;
        }
    }

}
