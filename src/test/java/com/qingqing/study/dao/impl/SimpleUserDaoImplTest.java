package com.qingqing.study.dao.impl;

import com.qingqing.study.TestBase;
import com.qingqing.study.dao.TSimpleUserEntityDao;
import com.qingqing.study.domain.TSimpleUserEntity;
import org.junit.Test;

import javax.annotation.Resource;

/**
 *
 */
public class SimpleUserDaoImplTest extends TestBase {

    private int id = 1;

    //    @Resource(name = "simpleUserEntitySFDao")
    @Resource(name = "simpleUserEntityHTDao")
    private TSimpleUserEntityDao simpleUserDao;

    @Test
    public void testDeletedById() {
//        System.out.println(JsonUtil.getJsonFromObject(simpleUserDao.findById(id)));
//        System.out.println(JsonUtil.getJsonFromObject(simpleUserDao.findAll()));
//        System.out.println(JsonUtil.getJsonFromObject(simpleUserDao.findByNameAndAge("name1", 1)));

        printJson(simpleUserDao.findById(id));

        simpleUserDao.deletedById(id);

        printJson(simpleUserDao.findById(id));
    }

    @Test
    public void testUpdateDetached() {
        TSimpleUserEntity entity = simpleUserDao.findById(id);
        entity.setAge(3);
        entity.setName("name3");

        simpleUserDao.updateDetached(entity);

        printJson(simpleUserDao.findById(id));
    }

    @Test
    public void testUpdateTransient() {

        TSimpleUserEntity entity = new TSimpleUserEntity();
        entity.setId(4);
        entity.setAge(4);
        entity.setName("name4");
        entity.setDeleted(false);

        TSimpleUserEntity entity2 = new TSimpleUserEntity();
        entity2.setId(5);
        entity2.setAge(5);
        entity2.setName("name5");
        entity2.setDeleted(false);
        simpleUserDao.updateTransient(entity, entity2);

        printJson(simpleUserDao.findById(4));
        printJson(simpleUserDao.findById(5));
    }
}
