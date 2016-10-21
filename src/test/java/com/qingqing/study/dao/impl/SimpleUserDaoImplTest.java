package com.qingqing.study.dao.impl;

import com.qingqing.common.util.JsonUtil;
import com.qingqing.study.dao.TSimpleUserEntityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
})
public class SimpleUserDaoImplTest{

//    @Resource(name = "simpleUserEntitySFDao")
    @Resource(name = "simpleUserEntityHTDao")
    private TSimpleUserEntityDao simpleUserDao;

    @Test
    public void testDao(){
//        System.out.println(JsonUtil.getJsonFromObject(simpleUserDao.findAll()));

        System.out.println(JsonUtil.getJsonFromObject(simpleUserDao.findByNameAndAge("name1", 1)));
    }
}
