package coursera.alg2.burrows;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import coursera.TestFilePath;
import lombok.SneakyThrows;

public class BurrowsWheelerTest {

    private TestFilePath p = new TestFilePath("coursera/alg2/burrows");

    @Test
    public void transformAbra() {
        testTransform("abra.txt", "abra.txt.bwt");
    }

    @Test
    public void inverseTransformAbra() {
        testInverseTransform("abra.txt", "abra.txt.bwt");
    }

    @Test
    public void transformAesop() {
        testTransform("aesop.txt", "aesop.txt.bwt");
    }

    @Test
    public void inverseTransformAesop() {
        testInverseTransform("aesop.txt", "aesop.txt.bwt");
    }

    @Test
    public void transformCababra() throws Exception {
        setInputFromFile("cadabra.txt");
        ByteArrayOutputStream out = getOutputStream();
        BurrowsWheeler.transform();
        String result = toHex(out.toString());
        assertThat(result, equalTo("00 00 00 08 41 52 44 21 52 43 41 41 41 41 42 42 "));
    }

    @SneakyThrows
    private void testTransform(String directFile, String reverseFile) {
        setInputFromFile(directFile);
        ByteArrayOutputStream out = getOutputStream();
        BurrowsWheeler.transform();
        String result = out.toString();
        String expectedResult = readFromFile(reverseFile);
        assertThat(result, equalTo(expectedResult));
    }

    @SneakyThrows
    private void testInverseTransform(String directFile, String reverseFile) {
        setInputFromFile(reverseFile);
        ByteArrayOutputStream out = getOutputStream();
        BurrowsWheeler.inverseTransform();
        String result = out.toString();
        String expectedResult = readFromFile(directFile);
        assertThat(result, equalTo(expectedResult));
    }

    private ByteArrayOutputStream getOutputStream() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);
        return out;
    }

    @SneakyThrows
    private String readFromFile(String fileName) {
        String fileFullName = p.file(fileName);
        return IOUtils.toString(new FileInputStream(fileFullName), Charset.defaultCharset());
    }

    private void setInputFromFile(String fileName) throws FileNotFoundException {
        FileInputStream is = new FileInputStream(new File(p.file(fileName)));
        System.setIn(is);
    }

    private String toHex(String binary) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < binary.length(); i++) {
            char c = binary.charAt(i);
            builder.append(String.format("%02x ", c & 0xff));
        }
        return builder.toString();
    }
}
