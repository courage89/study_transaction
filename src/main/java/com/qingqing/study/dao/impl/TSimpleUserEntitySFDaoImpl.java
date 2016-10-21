package com.qingqing.study.dao.impl;

import com.qingqing.study.dao.TSimpleUserEntityDao;
import com.qingqing.study.domain.TSimpleUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * 1、qbc方式
 * 2、原生SQL方式
 * 3、hql方式
 */
public class TSimpleUserEntitySFDaoImpl implements TSimpleUserEntityDao {

    private SessionFactory sessionFactory;

    public List<TSimpleUserEntity> findAll() {
//        Session session = sessionFactory.getCurrentSession();//使用hibernate4可以直接getCurrentSession， hibernate3只能先openSession
        Session session = sessionFactory.openSession();
        session.beginTransaction(); // 看成一个事务，进行操作
        List<TSimpleUserEntity> list = session.createQuery("from TSimpleUserEntity user").list();
        session.getTransaction().commit(); // 提交对数据的操作
        session.close();
        return list;
    }

    public TSimpleUserEntity findById(long id) {
        return null;
    }

    public List<TSimpleUserEntity> findByNameAndAge(String name, int age) {
        return null;
    }

    public void updateDetached(TSimpleUserEntity entity) {

    }

    public void updateTransient(TSimpleUserEntity entity, TSimpleUserEntity entity2) {

    }

    public void deletedById(long id) {

    }

    public void deletedByNameAndAge(String name, int age) {

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
