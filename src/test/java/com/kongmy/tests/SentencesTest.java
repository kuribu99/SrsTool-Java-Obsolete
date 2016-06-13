/*
 */
package com.kongmy.tests;

import com.kongmy.util.Sentences;
import java.util.ArrayList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Kong My
 */
@RunWith(JUnitParamsRunner.class)
public class SentencesTest {

    public Object[] getParamsForTestJoinArray() {
        String[][] arr = new String[][]{
            {"", ""},
            {"a", "a"},
            {"a and b", "a", "b"},
            {"a, b and c", "a", "b", "c"},
            {"a, b, c and d", "a", "b", "c", "d"}
        };

        List<String> list;
        Object[] results = new Object[5];

        for (int i = 0; i < arr.length; i++) {
            list = new ArrayList<>();
            for (int j = 1; j < arr[i].length; j++) {
                list.add(arr[i][j]);
            }
            results[i] = new Object[]{arr[i][0], list};
        }
        return results;
    }

    public Object[] getParamsForTestFormatAsSentence() {
        return new Object[]{
            new String[]{"test run", "Test run."},
            new String[]{"testrun", "Testrun."},
            new String[]{"a b c", "A b c."}
        };
    }

    @Test
    @Parameters(method = "getParamsForTestJoinArray")
    public void TestJoinArray(String afterJoin, List<String> array) {
        assertEquals(afterJoin, Sentences.JoinArray(array));
    }

    @Test
    @Parameters(method = "getParamsForTestFormatAsSentence")
    public void TestFormatAsSentence(String before, String after) {
        assertEquals(after, Sentences.FormatAsSentence(before));
    }

}
