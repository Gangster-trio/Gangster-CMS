package com.ganster.cms.auth.util;

import com.ganster.cms.auth.Exception.InformationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PInformationUtil.class)
public class PInformationUtilTest {
    private String string = "sxacs:666";

    @Test
    public void test() {
        PInformationUtil pInformationUtil = new PInformationUtil();
        try {
            pInformationUtil.dealInfromation(string);
        } catch (InformationException e) {
            System.out.println("cndsnvcd");
        }

    }
}