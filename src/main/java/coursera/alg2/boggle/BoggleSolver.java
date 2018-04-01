package coursera.alg2.boggle;

import edu.princeton.cs.algs4.TST;

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {

    private final TST<Integer> words;

    public BoggleSolver(String[] dictionary) {
        words = new TST<>();
        for (String word : dictionary) {
            int score = computeScore(word);
            if (score > 0) {
                words.put(word, score);
            }
        }
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Set<String> foundWords = new HashSet<>();

        for (int startY = 0; startY < board.rows(); startY++) {
            for (int startX = 0; startX < board.cols(); startX++) {
                boolean[][] visited = new boolean[board.rows()][board.cols()];
                String startPrefix = getLetter(board, startY, startX);
                if (hasPrefix(startPrefix)) {
                    dfs(foundWords, board, startPrefix, startX, startY, visited);
                }
            }
        }

        return foundWords;
    }

    private void dfs(Set<String> foundWords, BoggleBoard board, String currentPrefix, int x, int y,
                     boolean[][] visited) {
        visited[y][x] = true;
        Integer score = words.get(currentPrefix);
        if (score != null && score > 0 && !foundWords.contains(currentPrefix)) {
            foundWords.add(currentPrefix);
        }

        visitIfNeed(foundWords, currentPrefix, board, x - 1, y - 1, visited);
        visitIfNeed(foundWords, currentPrefix, board, x, y - 1, visited);
        visitIfNeed(foundWords, currentPrefix, board, x + 1, y - 1, visited);
        visitIfNeed(foundWords, currentPrefix, board, x + 1, y, visited);
        visitIfNeed(foundWords, currentPrefix, board, x + 1, y + 1, visited);
        visitIfNeed(foundWords, currentPrefix, board, x, y + 1, visited);
        visitIfNeed(foundWords, currentPrefix, board, x - 1, y + 1, visited);
        visitIfNeed(foundWords, currentPrefix, board, x - 1, y, visited);

        visited[y][x] = false;
    }

    private void visitIfNeed(Set<String> foundWords, String currentPrefix, BoggleBoard board, int nextX, int nextY,
                             boolean[][] visited) {
        if (onBoard(board.rows(), board.cols(), nextX, nextY)) {
            String nextPrefix = currentPrefix + getLetter(board, nextY, nextX);
            if (hasPrefix(nextPrefix) && !visited[nextY][nextX]) {
                dfs(foundWords, board, nextPrefix, nextX, nextY, visited);
            }
        }
    }

    private boolean hasPrefix(String prefix) {
        return words.keysWithPrefix(prefix).iterator().hasNext();
    }

    private String getLetter(BoggleBoard boggleBoard, int row, int col) {
        char letter = boggleBoard.getLetter(row, col);
        String suffix = letter == 'Q' ? "U" : "";
        return letter + suffix;
    }

    private boolean onBoard(int rows, int cols, int x, int y) {
        return x >= 0 && x < cols && y >= 0 && y < rows;
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
