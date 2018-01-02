package coursera.alg2.baseball;

import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class BaseballElimination {
    private final Map<String, Stats> stats;
    private final int n;

    public BaseballElimination(String filename) {
        notNull(filename);
        stats = readStats(filename);
        n = stats.size();
        analyzeElimination();
    }

    public int numberOfTeams() {
        return n;
    }

    public Iterable<String> teams() {
        return stats.keySet();
    }

    public int wins(String team) {
        validateTeam(team);
        return stats.get(team).wins;
    }

    public int losses(String team) {
        validateTeam(team);
        return stats.get(team).losses;
    }

    public int remaining(String team) {
        validateTeam(team);
        return stats.get(team).remaining;
    }

    public int against(String team1, String team2) {
        validateTeam(team1);
        validateTeam(team2);
        return stats.get(team1).against.get(stats.get(team2).id);
    }

    public boolean isEliminated(String team) {
        validateTeam(team);
        return false;
    }

    public Iterable<String> certificateOfElimination(String team) {
        validateTeam(team);
        return null;
    }

    private void analyzeElimination() {
        Stats maxStat = findMaxStat();

        for (Stats stat : stats.values()) {
            if (!trivialElimination(stat, maxStat)) {
                nonTrivialElimination(stat);
            }
        }
    }

    private void nonTrivialElimination(Stats stat) {
        int v = (n - 1) * (n - 2) / 2 + n + 1;
        int e = 2 * (n - 1) + (n - 1) * (n - 2);
        FlowNetwork flowNetwork = new FlowNetwork(v);

    }

    private Stats findMaxStat() {
        Stats maxStat = stats.values().iterator().next();
        for (Stats stat : stats.values()) {
            if (stat.wins > maxStat.wins) {
                maxStat = stat;
            }
        }
        return maxStat;
    }

    private boolean trivialElimination(Stats stat, Stats maxStat) {
        if (stat.wins + stat.remaining < maxStat.wins) {
            stat.eliminated = true;
            stat.certificate = Collections.singletonList(maxStat.name);
            return true;
        }

        return false;
    }

    private Map<String, Stats> readStats(String filename) {
        Map<String, Stats> stats = new HashMap<>();

        In in = new In(filename);
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            line = removeEmpty(line, n);
            Stats stat = new Stats();
            stat.id = i;
            stat.name = line[0];
            stat.wins = Integer.parseInt(line[1]);
            stat.losses = Integer.parseInt(line[2]);
            stat.remaining = Integer.parseInt(line[3]);
            List<Integer> against = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                against.add(Integer.parseInt(line[4 + j]));
            }
            stat.against = against;
            stats.put(stat.name, stat);
        }
        return stats;
    }

    private String[] removeEmpty(String[] arr, int n) {
        String[] res = new String[n + 4];
        int i = 0;
        for (String el : arr) {
            if (!el.trim().isEmpty()) {
                res[i++] = el.trim();
            }
        }
        return res;
    }

    private void notNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument should be not null");
        }
    }

    private void validateTeam(String team) {
        notNull(team);
        if (!stats.containsKey(team)) {
            throw new IllegalArgumentException("Invalid team");
        }
    }

    private static class Stats {
        int id;
        String name;
        int wins;
        int losses;
        int remaining;
        List<Integer> against;
        boolean eliminated;
        List<String> certificate;
    }

    public static void main(String[] args) {
        BaseballElimination elimination = new BaseballElimination("D:\\learning\\Coursera\\alg2\\baseball\\baseball-testing\\teams4.txt");
    }
}
