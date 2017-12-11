package coursera.alg2.wordnet;

import coursera.TestFilePath;
import org.junit.Test;

public class WordNetTest {
    private TestFilePath p = new TestFilePath("coursera/alg2/wordnet");

    @Test(expected = IllegalArgumentException.class)
    public void notDAG() throws Exception {
        String synsetsFile = p.file("synsets3.txt");
        String hyperFile = p.file("hypernyms3InvalidCycle.txt");
        new WordNet(synsetsFile, hyperFile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notSingleRootedDAG() throws Exception {
        String synsetsFile = p.file("synsets3.txt");
        String hyperFile = p.file("hypernyms3InvalidTwoRoots.txt");
        new WordNet(synsetsFile, hyperFile);
    }

    @Test
    public void valid() throws Exception {
        String synsetsFile = p.file("synsets8.txt");
        String hyperFile = p.file("hypernyms8ManyAncestors.txt");
        new WordNet(synsetsFile, hyperFile);
    }
}
