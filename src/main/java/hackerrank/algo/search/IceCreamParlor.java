package hackerrank.algo.search;

import java.util.*;

public class IceCreamParlor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            List<Integer> costs = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                costs.add(scanner.nextInt());
            }
            System.out.println(solution(m, costs));
        }

        scanner.close();
    }

    static String solution(int m, List<Integer> costs) {
        Map<Integer, List<Integer>> costToPositions = groupCostsByPosition(costs);

        for (int i = 0; i < costs.size(); i++) {
            int firstPart = costs.get(i);
            int firstIndex = i + 1;
            if (firstPart < m) {
                int secondPart = m - firstPart;
                if (firstPart != secondPart) {
                    if (costToPositions.containsKey(secondPart)) {
                        int secondIndex = costToPositions.get(secondPart).get(0);
                        return formatSolution(firstIndex, secondIndex);
                    }
                } else {
                    List<Integer> positions = costToPositions.get(firstPart);
                    if (positions.size() >= 2) {
                        return formatSolution(positions.get(0), positions.get(1));
                    }
                }
            }
        }

        throw new RuntimeException("Solution was not found");
    }

    private static String formatSolution(int firstIndex, int secondIndex) {
        int smallerIndex = Math.min(firstIndex, secondIndex);
        int biggerIndex = Math.max(firstIndex, secondIndex);
        return smallerIndex + " " + biggerIndex;
    }

    private static Map<Integer, List<Integer>> groupCostsByPosition(List<Integer> costs) {
        Map<Integer, List<Integer>> result = new HashMap<>();

        for (int i = 0; i < costs.size(); i++) {
            int position = i + 1;
            int cost = costs.get(i);
            List<Integer> positions;
            if (!result.containsKey(cost)) {
                positions = new ArrayList<>();
                result.put(cost, positions);
            } else {
                positions = result.get(cost);
            }
            positions.add(position);
        }

        return result;
    }
}
