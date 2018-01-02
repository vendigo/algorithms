package coursera.alg2.baseball;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseballElimination {
    private final int S_INDEX = 0;
    private final int T_INDEX = 1;
    private final Map<String, Stats> statsByName;
    private final Map<Integer, Stats> statsById;
    private final int n;

    public BaseballElimination(String filename) {
        notNull(filename);
        statsByName = readStats(filename);
        statsById = groupById();
        n = statsByName.size();
        analyzeElimination();
    }

    private Map<Integer, Stats> groupById() {
        Map<Integer, Stats> result = new HashMap<>();
        for (Stats stats : statsByName.values()) {
            result.put(stats.id, stats);
        }
        return result;
    }

    public int numberOfTeams() {
        return n;
    }

    public Iterable<String> teams() {
        return statsByName.keySet();
    }

    public int wins(String team) {
        validateTeam(team);
        return statsByName.get(team).wins;
    }

    public int losses(String team) {
        validateTeam(team);
        return statsByName.get(team).losses;
    }

    public int remaining(String team) {
        validateTeam(team);
        return statsByName.get(team).remaining;
    }

    public int against(String team1, String team2) {
        validateTeam(team1);
        validateTeam(team2);
        return statsByName.get(team1).against.get(statsByName.get(team2).id);
    }

    public boolean isEliminated(String team) {
        validateTeam(team);
        return statsByName.get(team).eliminated;
    }

    public Iterable<String> certificateOfElimination(String team) {
        validateTeam(team);
        return statsByName.get(team).certificate;
    }

    private void analyzeElimination() {
        Stats maxStat = findMaxStat();

        for (Stats stat : statsByName.values()) {
            if (!trivialElimination(stat, maxStat)) {
                nonTrivialElimination(stat);
            }
        }
    }

    private void nonTrivialElimination(Stats stat) {
        int v = (n - 1) * (n - 2) / 2 + n + 1;
        FlowNetwork flowNetwork = new FlowNetwork(v);
        Map<String, Integer> verticesMap = createVerticesMap(stat.id);
        addEdges(flowNetwork, verticesMap, stat);
        FordFulkerson ff = new FordFulkerson(flowNetwork, S_INDEX, T_INDEX);

        stat.eliminated = checkElimination(flowNetwork);
        if (stat.eliminated) { //Provide certificate
            stat.certificate = buildCertificate(stat, verticesMap, ff);
        }
    }

    private Iterable<String> buildCertificate(Stats stat, Map<String, Integer> verticesMap, FordFulkerson ff) {
        Set<String> certificate = new HashSet<>();
        for (Stats cStat : statsByName.values()) {
            if (cStat.id != stat.id) {
                int vertexId = verticesMap.get(String.valueOf(cStat.id));
                if (ff.inCut(vertexId)) {
                    certificate.add(cStat.name);
                }
            }
        }
        return certificate;
    }

    private boolean checkElimination(FlowNetwork flowNetwork) {
        for (FlowEdge edge : flowNetwork.adj(S_INDEX)) {
            if (edge.flow() != edge.capacity()) { //Not all edges pointing from s are full
                return true;
            }
        }
        return false;
    }

    private void addEdges(FlowNetwork flowNetwork, Map<String, Integer> verticesMap, Stats current) {
        int currentId = current.id;

        //s -> game -> team edges
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j && i != currentId && j != currentId) {
                    String vKey = i + "-" + j;
                    int gameVertexId = verticesMap.get(vKey);
                    int capacity = statsById.get(i).against.get(j);
                    // s -> game edge
                    flowNetwork.addEdge(new FlowEdge(S_INDEX, gameVertexId, capacity));

                    //game -> team edges
                    int teamIVertexId = verticesMap.get(String.valueOf(i));
                    flowNetwork.addEdge(new FlowEdge(gameVertexId, teamIVertexId, Double.POSITIVE_INFINITY));
                    int teamJVertexId = verticesMap.get(String.valueOf(j));
                    flowNetwork.addEdge(new FlowEdge(gameVertexId, teamJVertexId, Double.POSITIVE_INFINITY));
                }
            }
        }

        //team -> t edges
        for (int i = 0; i < n; i++) {
            if (i != currentId) {
                int teamVertextId = verticesMap.get(String.valueOf(i));
                Stats iTeamStats = statsById.get(i);
                int capacity = current.wins + current.remaining - iTeamStats.wins;
                flowNetwork.addEdge(new FlowEdge(teamVertextId, T_INDEX, capacity));
            }
        }
    }

    private Map<String, Integer> createVerticesMap(int currentId) {
        Map<String, Integer> vertices = new HashMap<>();
        //Team vertices
        int id = 2;
        for (int i = 0; i < n; i++) {
            if (i != currentId) {
                vertices.put(String.valueOf(i), id++);
            }
        }
        //Game vertices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j && i != currentId && j != currentId) {
                    vertices.put(i + "-" + j, id++);
                }
            }
        }
        return vertices;
    }

    private Stats findMaxStat() {
        Stats maxStat = statsByName.values().iterator().next();
        for (Stats stat : statsByName.values()) {
            if (stat.wins > maxStat.wins) {
                maxStat = stat;
            }
        }
        return maxStat;
    }

    private boolean trivialElimination(Stats stat, Stats maxStat) {
        if (stat.wins + stat.remaining < maxStat.wins) {
            stat.eliminated = true;
            HashSet<String> certificate = new HashSet<>();
            certificate.add(maxStat.name);
            stat.certificate = certificate;
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
        if (!statsByName.containsKey(team)) {
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
        Iterable<String> certificate;

        @Override
        public String toString() {
            return "Stats{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", wins=" + wins +
                    ", remaining=" + remaining +
                    ", eliminated=" + eliminated +
                    '}';
        }
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("D:\\learning\\Coursera\\alg2\\baseball\\baseball-testing\\teams5.txt");
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
