package coursera.alg1.pazzle8;

import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;
import java.util.List;

public class Solver {
    private final boolean solvable;
    private final List<Board> solution;

    public Solver(Board initial) {
        notNull(initial);

        MinPQ<Step> queue = new MinPQ<>();
        Step step = new Step(null, initial, 0);
        queue.insert(step);
        List<Board> solution = new LinkedList<>();

        MinPQ<Step> reversedQueue = new MinPQ<>();
        Step reversedStep = new Step(null, initial.twin(), 0);
        reversedQueue.insert(reversedStep);

        while (!step.current.isGoal() && !reversedStep.current.isGoal()) {
            step = queue.delMin();
            solution.add(step.current);
            processNeighbors(queue, step);
            reversedStep = reversedQueue.delMin();
            processNeighbors(reversedQueue, reversedStep);
        }

        if (!solution.isEmpty()) {
            solution.remove(0);
        }

        this.solvable = step.current.isGoal();
        this.solution = this.solvable ? solution : null;
    }

    private void processNeighbors(MinPQ<Step> queue, Step currentStep) {
        Iterable<Board> neighbors = currentStep.current.neighbors();
        for (Board neighbor : neighbors) {
            if (!neighbor.equals(currentStep.prev)) {
                queue.insert(new Step(currentStep.current, neighbor, currentStep.moves + 1));
            }
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return solvable ? solution.size() : -1;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    private void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }

    private static class Step implements Comparable<Step> {
        Board prev;
        Board current;
        int moves;

        Step(Board prev, Board current, int moves) {
            this.prev = prev;
            this.current = current;
            this.moves = moves;
        }

        public int compareTo(Step o) {
            return Integer.compare(current.manhattan() + moves, o.current.manhattan() + o.moves);
        }
    }
}