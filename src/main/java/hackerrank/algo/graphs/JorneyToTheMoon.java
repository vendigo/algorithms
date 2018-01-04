package hackerrank.algo.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class JorneyToTheMoon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();
        Map<Integer, List<Integer>> graph = readGraph(scanner, vertices, edges);
        List<Integer> components = components(graph, vertices);
        System.out.println(findWay(components, vertices));
    }

    private static long findWay(List<Integer> components, int n) {
        long all = cNBy2(n);
        long oneCountry = 0;
        for (Integer c : components) {
            oneCountry += cNBy2(c);
        }
        return all - oneCountry;
    }

    static long cNBy2(int n) {
        return (((long)n - 1) * n) / 2;
    }

    private static Map<Integer, List<Integer>> readGraph(Scanner scanner, int vertices, int edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < vertices; i++) {
            graph.put(i, new LinkedList<>());
        }

        for (int j = 0; j < edges; j++) {
            int u = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(u).add(w);
            graph.get(w).add(u);
        }

        return graph;
    }

    private static List<Integer> components(Map<Integer, List<Integer>> graph, int vertices) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> components = new LinkedList<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited.contains(i)) {
                int component = 0;
                queue.add(i);

                while (!queue.isEmpty()) {
                    Integer v = queue.poll();
                    if (!visited.contains(v)) {
                        visited.add(v);
                        component++;
                        List<Integer> adj = graph.get(v);
                        for (Integer a : adj) {
                            if (!visited.contains(a)) {
                                queue.add(a);
                            }
                        }
                    }
                }
                components.add(component);
            }
        }
        return components;
    }
}
