/*
 */
package com.kongmy.tests;

import com.kongmy.util.CamelCaseEncoder;
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
public class CamelCaseEncoderTest {

    public Object[] getParams() {
        return new Object[] {
            new String[]{"test run", "testRun"},
            new String[]{"testrun", "testrun"},
            new String[]{"a b c", "aBC"}
        };
    }

    @Test
    @Parameters(method = "getParams")
    public void TestEncode(String beforeEncode, String afterEncode) {
        assertEquals(afterEncode, CamelCaseEncoder.encode(beforeEncode));
    }

    @Test
    @Parameters(method = "getParams")
    public void TestDecode(String beforeEncode, String afterEncode) {
        assertEquals(beforeEncode, CamelCaseEncoder.decode(afterEncode));
    }

}
