package coursera.alg2.baseball;

import coursera.TestFilePath;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BaseballEliminationTest {
    private TestFilePath p = new TestFilePath("coursera/alg2/baseball");

    @Test
    public void teams4Elimination() throws Exception {
        BaseballElimination division = new BaseballElimination(p.file("teams4.txt"));
        assertThat(division.isEliminated("Atlanta"), equalTo(false));
        assertThat(division.isEliminated("New_York"), equalTo(false));
        assertThat(division.isEliminated("Philadelphia"), equalTo(true));
        assertThat(division.isEliminated("Montreal"), equalTo(true));
        assertThat(division.certificateOfElimination("Atlanta"), nullValue());
        assertThat(division.certificateOfElimination("New_York"), nullValue());
    }

    @Test
    public void teams4CertPhil() throws Exception {
        BaseballElimination division = new BaseballElimination(p.file("teams4.txt"));

        Set<String> cert1 = new HashSet<>();
        cert1.add("Atlanta");
        cert1.add("New_York");
        assertThat(division.certificateOfElimination("Philadelphia"), equalTo(cert1));
    }

    @Test
    public void teams4CertMon() throws Exception {
        BaseballElimination division = new BaseballElimination(p.file("teams4.txt"));
        Set<String> cert2 = new HashSet<>();
        cert2.add("Atlanta");
        assertThat(division.certificateOfElimination("Montreal"), equalTo(cert2));
    }

    @Test
    public void teams5Elimination() throws Exception {
        BaseballElimination division = new BaseballElimination(p.file("teams5.txt"));
        assertThat(division.isEliminated("New_York"), equalTo(false));
        assertThat(division.isEliminated("Baltimore"), equalTo(false));
        assertThat(division.isEliminated("Boston"), equalTo(false));
        assertThat(division.isEliminated("Toronto"), equalTo(false));
        assertThat(division.isEliminated("Detroit"), equalTo(true));
        assertThat(division.certificateOfElimination("New_York"), nullValue());
        assertThat(division.certificateOfElimination("Baltimore"), nullValue());
        assertThat(division.certificateOfElimination("Boston"), nullValue());
        assertThat(division.certificateOfElimination("Toronto"), nullValue());
    }

    @Test
    public void teams5Cert() throws Exception {
        BaseballElimination division = new BaseballElimination(p.file("teams5.txt"));
        Set<String> cert1 = new HashSet<>();
        cert1.add("Baltimore");
        cert1.add("Boston");
        cert1.add("Toronto");
        cert1.add("New_York");
        assertThat(division.certificateOfElimination("Detroit"), equalTo(cert1));
    }
}
