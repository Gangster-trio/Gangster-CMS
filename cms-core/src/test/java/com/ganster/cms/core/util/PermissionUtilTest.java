package com.ganster.cms.core.util;

import com.ganster.cms.core.CoreApplication;
import com.ganster.cms.core.constant.CmsConst;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class PermissionUtilTest {

    @Test
    public void test() {
        List<Integer> list = PermissionUtil.getAllPermittedCategory(1, 1, CmsConst.PERMISSION_WRITE);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
