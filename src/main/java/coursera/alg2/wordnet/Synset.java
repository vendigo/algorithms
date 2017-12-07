package coursera.alg2.wordnet;

import java.util.Set;

public class Synset {
    private int id;
    private Set<String> nouns;

    public Synset(int id, Set<String> nouns) {
        this.id = id;
        this.nouns = nouns;
    }

    public int getId() {
        return id;
    }

    public Set<String> getNouns() {
        return nouns;
    }

    @Override
    public String toString() {
        return "Synset{" +
                "id=" + id +
                ", nouns=" + nouns +
                '}';
    }
}
