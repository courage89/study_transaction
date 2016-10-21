package com.qingqing.study;

import com.qingqing.common.util.JsonUtil;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
})
public class TestBase {
    protected void printJson(Object object){
        System.out.println(JsonUtil.getJsonFromObject(object));
    }

}
