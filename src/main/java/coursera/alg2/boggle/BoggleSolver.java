package coursera.alg2.boggle;

import edu.princeton.cs.algs4.TrieST;

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {

    private final TrieST<Integer> words;

    public BoggleSolver(String[] dictionary) {
        words = new TrieST<>();
        for (String word : dictionary) {
            words.put(word, computeScore(word));
        }
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Set<String> foundWords = new HashSet<>();

        for (int startY = 0; startY < board.rows(); startY++) {
            for (int startX = 0; startX < board.cols(); startX++) {
                String startPrefix = board.getLetter(startY, startX) + "";
                boolean[][] visited = new boolean[board.rows()][board.cols()];
                dfs(foundWords, board, startPrefix, startX, startY, visited);
            }
        }

        return foundWords;
    }

    private void dfs(Set<String> foundWords, BoggleBoard board, String currentPrefix, int x, int y,
                     boolean[][] visited) {
        if (!hasPrefix(currentPrefix)) {
            return;
        }

        visited[y][x] = true;
        Integer score = words.get(currentPrefix);
        if (score != null && score > 0) {
            foundWords.add(currentPrefix);
        }

        //Process neighbours
        if (canVisit(board.rows(), board.cols(), x - 1, y - 1, visited)) {
            dfs(foundWords, board, currentPrefix + board.getLetter(x - 1, y - 1), x - 1, y - 1, visited);
        }
        if (canVisit(board.rows(), board.cols(), x, y - 1, visited)) {
            dfs(foundWords, board, currentPrefix + board.getLetter(x, y - 1), x, y - 1, visited);
        }
        if (canVisit(board.rows(), board.cols(), x + 1, y - 1, visited)) {
            dfs(foundWords, board, currentPrefix + board.getLetter(x + 1, y - 1), x + 1, y - 1, visited);
        }
        if (canVisit(board.rows(), board.cols(), x + 1, y, visited)) {
            dfs(foundWords, board, currentPrefix + board.getLetter(x + 1, y), x + 1, y, visited);
        }
        if (canVisit(board.rows(), board.cols(), x + 1, y + 1, visited)) {
            dfs(foundWords, board, currentPrefix + board.getLetter(x + 1, y + 1), x + 1, y + 1, visited);
        }
        if (canVisit(board.rows(), board.cols(), x, y + 1, visited)) {
            dfs(foundWords, board, currentPrefix + board.getLetter(x, y + 1), x, y + 1, visited);
        }
        if (canVisit(board.rows(), board.cols(), x - 1, y + 1, visited)) {
            dfs(foundWords, board, currentPrefix + board.getLetter(x - 1, y + 1), x - 1, y + 1, visited);
        }
        if (canVisit(board.rows(), board.cols(), x - 1, y, visited)) {
            dfs(foundWords, board, currentPrefix + board.getLetter(x - 1, y), x - 1, y, visited);
        }
    }


    private boolean hasPrefix(String prefix) {
        return words.keysWithPrefix(prefix).iterator().hasNext();
    }

    private boolean canVisit(int rows, int cols, int x, int y, boolean[][] visited) {
        return x >= 0 && x < cols && y >= 0 && y < rows && !visited[y][x];
    }

    public int scoreOf(String word) {
        Integer score = words.get(word);
        return score != null ? score : 0;
    }

    private int computeScore(String word) {
        int len = word.length();
        if (len <= 2) {
            return 0;
        } else if (len <= 4) {
            return 1;
        } else if (len == 5) {
            return 2;
        } else if (len == 6) {
            return 3;
        } else if (len == 7) {
            return 5;
        } else {
            return 11;
        }
    }
}
