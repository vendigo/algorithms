package coursera.alg2.burrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringWriter;

import org.junit.Test;

import coursera.TestFilePath;

public class BurrowsWheelerTest {

    private TestFilePath p = new TestFilePath("coursera/alg2/burrows");

    @Test
    public void transform() throws Exception {
        setInputFromFile("abra.txt");
        BurrowsWheeler.transform();
        StringWriter stringWriter = new StringWriter();
    }

    private void setInputFromFile(String fileName) throws FileNotFoundException {
        FileInputStream is = new FileInputStream(new File(p.file(fileName)));
        System.setIn(is);
    }
}
