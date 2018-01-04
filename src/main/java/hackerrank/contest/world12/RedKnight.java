package hackerrank.contest.world12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class RedKnight {
    private int n;
    private Point start;
    private Point end;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int yStart = scanner.nextInt();
        int xStart = scanner.nextInt();
        int yEnd = scanner.nextInt();
        int xEnd = scanner.nextInt();

        new RedKnight(n, xStart, yStart, xEnd, yEnd).solve();
    }

    RedKnight(int n, int xStart, int yStart, int xEnd, int yEnd) {
        this.n = n;
        this.start = new Point(xStart, yStart);
        this.end = new Point(xEnd, yEnd);
    }

    void solve() {
        Map<Point, Integer> distTo = new HashMap<>();
        distTo.put(start, 0);

        Set<Point> marked = new HashSet<>();
        marked.add(start);

        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);

        Map<Point, Point> paths = new HashMap<>();

        while (!queue.isEmpty()) {
            Point v = queue.poll();
            for (Point w : adj(v)) {
                if (!marked.contains(w)) {
                    marked.add(w);
                    distTo.put(w, distTo.get(v) + 1);
                    paths.put(w, v);
                    queue.add(w);
                }
            }
        }

        if (distTo.containsKey(end)) {
            System.out.println(distTo.get(end));
            System.out.println(buildPath(paths));
        } else {
            System.out.println("Impossible");
        }
    }

    public String buildPath(Map<Point, Point> paths) {
        Point c = end;
        List<String> commands = new ArrayList<>();
        while (c != start) {
            Point next = paths.get(c);
            commands.add(getCommand(next, c)); //As we constructing from end to start
            c = next;
        }
        Collections.reverse(commands);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < commands.size() - 1; i++) {
            result.append(commands.get(i)).append(" ");
        }
        result.append(commands.get(commands.size() - 1));
        return result.toString();
    }

    public String getCommand(Point v, Point w) {
        if (v.y > w.y) {
            if (v.x > w.x) {
                return "UL";
            } else {
                return "UR";
            }
        }

        if (v.y == w.y) {
            if (v.x > w.x) {
                return "L";
            } else {
                return "R";
            }
        }

        if (v.x > w.x) {
            return "LL";
        }

        return "LR";
    }

    public List<Point> adj(Point p) {
        List<Point> points = new ArrayList<>(6);

        if (inField(p.x - 1, p.y - 2)) {
            points.add(new Point(p.x - 1, p.y - 2));
        }

        if (inField(p.x + 1, p.y - 2)) {
            points.add(new Point(p.x + 1, p.y - 2));
        }

        if (inField(p.x + 2, p.y)) {
            points.add(new Point(p.x + 2, p.y));
        }

        if (inField(p.x + 1, p.y + 2)) {
            points.add(new Point(p.x + 1, p.y + 2));
        }

        if (inField(p.x - 1, p.y + 2)) {
            points.add(new Point(p.x - 1, p.y + 2));
        }

        if (inField(p.x - 2, p.y)) {
            points.add(new Point(p.x - 2, p.y));
        }

        return points;
    }

    public boolean inField(int x, int y) {
        return x >= 0 && x < n & y >= 0 && y < n;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ')';
        }
    }
}
