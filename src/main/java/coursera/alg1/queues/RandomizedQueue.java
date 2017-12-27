package coursera.alg1.queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] array = (Item[]) new Object[4];

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        System.out.println(array.length);
        return size;
    }

    public void enqueue(Item item) {
        notNull(item);
        ensureCapacity();
        array[size++] = item;
    }

    public Item dequeue() {
        hasElements();
        shrink();

        int i = randomIndex();
        Item elem = array[i];
        array[i] = null;
        size--;

        return elem;
    }

    public Item sample() {
        hasElements();
        return array[randomIndex()];
    }

    public Iterator<Item> iterator() {
        return new RandomIterator<>(array, size);
    }

    private void ensureCapacity() {
        if (size >= array.length) {
            array = reBalanceArray(array, array.length * 2);
        }
    }

    private int randomIndex() {
        int startInd = StdRandom.uniform(size);
        int ind = startInd;
        while (array[ind] == null && ind < size) ind++; //Looking for not null element --> To the right

        if (array[ind] == null) {
            ind = startInd;
            while (array[ind] == null && ind > 0) ind--; // And to the left <--
        }

        return ind;
    }


    private void shrink() {
        if (size * 4 < array.length) {
            array = reBalanceArray(array, array.length / 2); //Re balance and shrink
        } else if (size * 2 < array.length) {
            array = reBalanceArray(array, array.length); // Just re balance
        }
    }

    private static <Item> Item[] reBalanceArray(Object[] array, int newSize) {
        Object[] newArray = new Object[newSize];
        int j = 0;
        for (Object i : array) {
            if (i != null) {
                newArray[j++] = i;
            }
        }
        return (Item[]) newArray;
    }

    private void notNull(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }

    private void hasElements() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    private static class RandomIterator<Item> implements Iterator<Item> {
        private final int size;
        private final Item[] shuffledArray;
        private int i = 0;

        public RandomIterator(Item[] array, int size) {
            this.shuffledArray = reBalanceArray(array, size);
            this.size = size;
            for (int j = 0; j < size * 2; j++) {
                int left = StdRandom.uniform(size);
                int right = StdRandom.uniform(size);
                Item t = shuffledArray[left];
                shuffledArray[left] = shuffledArray[right];
                shuffledArray[right] = t;
            }
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return shuffledArray[i++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        for (Integer i : queue) {
            System.out.println(i);
        }
    }
}