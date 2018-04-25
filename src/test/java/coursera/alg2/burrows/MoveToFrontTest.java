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

public class MoveToFrontTest {

    private TestFilePath p = new TestFilePath("coursera/alg2/burrows");

    @Test
    public void encodeAbra() {
        testEncode("abra.txt", "abra.txt.mtf");
    }

    @Test
    public void decodeAbra() {
        testDecode("abra.txt", "abra.txt.mtf");
    }

    @SneakyThrows
    private void testEncode(String directFile, String reverseFile) {
        setInputFromFile(directFile);
        ByteArrayOutputStream out = getOutputStream();
        MoveToFront.encode();
        String result = out.toString();
        String expectedResult = readFromFile(reverseFile);
        assertThat(result, equalTo(expectedResult));
    }

    @SneakyThrows
    private void testDecode(String directFile, String reverseFile) {
        setInputFromFile(reverseFile);
        ByteArrayOutputStream out = getOutputStream();
        MoveToFront.decode();
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
}
