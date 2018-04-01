package coursera.alg2.boggle;

public class OptimizedTST<Value> {
    private int n;
    private Node<Value> root;

    private static class Node<Value> {
        private char c;
        private Node<Value> left, mid, right;
        private Value val;
    }

    public int size() {
        return n;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    private Node<Value> get(Node<Value> x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d + 1);
        else return x;
    }

    public void put(String key, Value val) {
        if (!contains(key)) n++;
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<>();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d + 1);
        else x.val = val;
        return x;
    }

    public boolean containsPrefix(String prefix) {
        Node<Value> x = get(root, prefix, 0);
        if (x == null) return false;
        if (x.val != null) return true;
        boolean[] found = new boolean[1];
        checkFurther(x.mid, new StringBuilder(prefix), found);
        return found[0];
    }

    private void checkFurther(Node<Value> x, StringBuilder prefix, boolean[] found) {
        if (x == null || found[0]) return;
        checkFurther(x.left, prefix, found);
        if (x.val != null) {
            found[0] = true;
            return;
        }
        checkFurther(x.mid, prefix.append(x.c), found);
        prefix.deleteCharAt(prefix.length() - 1);
        checkFurther(x.right, prefix, found);
    }
}

