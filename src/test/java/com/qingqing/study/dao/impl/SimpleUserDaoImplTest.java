package com.qingqing.study.dao.impl;

import com.qingqing.common.util.JsonUtil;
import com.qingqing.study.dao.TSimpleUserEntityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
})
public class SimpleUserDaoImplTest{

    @Autowired
    private TSimpleUserEntityDao simpleUserDao;

    @Test
    public void testDao(){
        System.out.println(JsonUtil.getJsonFromObject(simpleUserDao.findAll()));
    }
}
