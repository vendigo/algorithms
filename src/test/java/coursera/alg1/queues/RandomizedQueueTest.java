package coursera.alg1.queues;

import org.junit.Test;

import java.util.Random;

public class RandomizedQueueTest {
    @Test
    public void randomLimit() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            double r = random.nextDouble();
            if (i < 1 || r < 0.3) {
                queue.enqueue(i);
            } else if (!queue.isEmpty()) {
                queue.dequeue();
            }
        }
    }

    @Test
    public void dequeue() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }

        queue.size();

        for (int i = 0; i < 999; i++) {
            queue.dequeue();

            if (i == 900) {
                queue.size();
            }
        }

        queue.size();
    }
}
