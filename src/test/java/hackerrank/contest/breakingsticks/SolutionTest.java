package hackerrank.contest.breakingsticks;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SolutionTest {
    @Test
    public void baseCase0() throws Exception {
        long res = Solution.solveOne(2, new HashMap<>());
        assertThat(res, equalTo(3L));
    }

    @Test
    public void baseCase1() throws Exception {
        long res = Solution.solveOne(1, new HashMap<>());
        assertThat(res, equalTo(1L));
    }

    @Test
    public void case0() throws Exception {
        long res = Solution.solveOne(24, new HashMap<>());
        assertThat(res, equalTo(46L));
    }

    @Test
    public void case1() throws Exception {
        long res = Solution.solveOne(4, new HashMap<>());
        assertThat(res, equalTo(7L));
    }

    @Test
    public void case2() throws Exception {
        long res = Solution.solveOne(6, new HashMap<>());
        assertThat(res, equalTo(10L));
    }

    @Test
    public void case3() throws Exception {
        long res = Solution.solve(Arrays.asList(1L, 7L, 24L));
        assertThat(res, equalTo(55L));
    }
}
