package hackerrank.algo.search;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class IceCreamParlorTest {
    @Test
    public void case0() throws Exception {
        String answer = IceCreamParlor.solution(4, Arrays.asList(1, 4, 5, 3, 2));
        assertThat(answer, equalTo("1 4"));
    }

    @Test
    public void case1() throws Exception {
        String answer = IceCreamParlor.solution(4, Arrays.asList(2, 2, 4, 3));
        assertThat(answer, equalTo("1 2"));
    }
}
