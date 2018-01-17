package coursera.alg1.pazzle8;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {
    private final boolean solvable;
    private final List<Board> solution;

    public Solver(Board initial) {
        notNull(initial);

        MinPQ<Step> queue = new MinPQ<>();
        Step step = new Step(null, initial, 0);
        queue.insert(step);

        MinPQ<Step> reversedQueue = new MinPQ<>();
        Step reversedStep = new Step(null, initial.twin(), 0);
        reversedQueue.insert(reversedStep);

        while (!step.board.isGoal() && !reversedStep.board.isGoal()) {
            step = queue.delMin();
            processNeighbors(queue, step);
            reversedStep = reversedQueue.delMin();
            processNeighbors(reversedQueue, reversedStep);
        }

        this.solvable = step.board.isGoal();
        this.solution = this.solvable ? buildSolution(step) : null;
    }

    private List<Board> buildSolution(Step step) {
        List<Board> res = new ArrayList<>();

        Step c = step;
        while (c != null) { //Building reversed solution
            res.add(c.board);
            c = c.prevStep;
        }

        Collections.reverse(res);
        return res;
    }

    private void processNeighbors(MinPQ<Step> queue, Step currentStep) {
        Iterable<Board> neighbors = currentStep.board.neighbors();
        for (Board neighbor : neighbors) {
            if (!neighbor.equals(currentStep.prevStep != null ? currentStep.prevStep.board : null)) {
                queue.insert(new Step(currentStep, neighbor, currentStep.moves + 1));
            }
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return solvable ? solution.size() - 1 : -1;
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
        Step prevStep;
        Board board;
        int priority;
        int moves;

        Step(Step prevStep, Board board, int moves) {
            this.prevStep = prevStep;
            this.board = board;
            this.moves = moves;
            this.priority = board.manhattan() + moves;
        }

        public int compareTo(Step o) {
            return Integer.compare(priority, o.priority);
        }
    }
}