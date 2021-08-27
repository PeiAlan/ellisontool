package com.ellison;

import com.ellison.tool.EllisonDateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String s = EllisonDateUtil.formatYMDChinese(new Date());
        System.out.println(s);
    }
}
