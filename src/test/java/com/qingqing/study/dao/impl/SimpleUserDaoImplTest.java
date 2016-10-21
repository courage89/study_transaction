package com.qingqing.study.dao.impl;

import com.qingqing.common.util.JsonUtil;
import com.qingqing.study.TestBase;
import com.qingqing.study.dao.TSimpleUserEntityDao;
import org.junit.Test;

import javax.annotation.Resource;

/**
 *
 */

public class SimpleUserDaoImplTest extends TestBase {

//    @Resource(name = "simpleUserEntitySFDao")
    @Resource(name = "simpleUserEntityHTDao")
    private TSimpleUserEntityDao simpleUserDao;

    @Test
    public void testDao(){
//        System.out.println(JsonUtil.getJsonFromObject(simpleUserDao.findAll()));

        int id = 1;
        System.out.println(JsonUtil.getJsonFromObject(simpleUserDao.findById(id)));
    }


}
