package com.qingqing.study.dao.impl;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.study.dao.TSimpleUserEntityDao;
import com.qingqing.study.domain.HibernateOperateType;
import com.qingqing.study.domain.TSimpleUserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 *
 */
public class TSimpleUserEntityHTDaoImpl implements TSimpleUserEntityDao {

    private HibernateOperateType operateType = HibernateOperateType.SQL;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<TSimpleUserEntity> findAll() {

        return hibernateTemplate.find("from TSimpleUserEntity user");
//        return (List<TSimpleUserEntity>) hibernateTemplate.find("from TSimpleUserEntity user", null);//hibernate4的写法
    }

    public TSimpleUserEntity findById(long id) {
        List<?> list = null;
        switch (operateType) {
            case HQL:
                list = hibernateTemplate.find("from TSimpleUserEntity where id = " + id);
                break;
            case QBC:
                DetachedCriteria dc = DetachedCriteria.forClass(TSimpleUserEntity.class)
                        .add(Restrictions.eq(TSimpleUserEntity.ID, id));
                list = hibernateTemplate.findByCriteria(dc);
                break;
            case SQL:
                Session session = null;
                String sql = "select * from t_simple_user where id = :id";
                SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
                try {
                    session = sessionFactory.openSession();
                    list = session.createSQLQuery(sql).addEntity(TSimpleUserEntity.class).setParameter("id", id).list();
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
                break;
            default:
                throw new QingQingRuntimeException("");
        }

        if (CollectionsUtil.isNullOrEmpty(list)) {
            return null;
        } else {
            return (TSimpleUserEntity) list.get(0);
        }
    }

    public List<TSimpleUserEntity> findByNameAndAge(String name, int age) {

        List<?> list = null;
        switch (operateType) {
            case HQL:
                list = hibernateTemplate.findByNamedParam("from TSimpleUserEntity where name = :name and age = :age", new String[]{"name", "age"}, new Object[]{name, age});
                break;
            case QBC:
                DetachedCriteria dc = DetachedCriteria.forClass(TSimpleUserEntity.class).add(Restrictions.eq(TSimpleUserEntity.NAME, name)).add(Restrictions.eq(TSimpleUserEntity.AGE, age));
                list = hibernateTemplate.findByCriteria(dc);
                break;
            case SQL:
                Session session = null;
                String sql = "select * from t_simple_user where name = :name and age = :age";
                SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
                try {
                    session = sessionFactory.openSession();
                    list = session.createSQLQuery(sql).addEntity(TSimpleUserEntity.class).setParameter("name", name).setParameter("age", age).list();
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
                break;
            default:
                throw new QingQingRuntimeException("");
        }
        return (List<TSimpleUserEntity>) list;
    }

    /**
     * saveOrUpdate操作会先判断对象所处的状态，若为自由态，则直接执行insert的sql操作；若为游离态或者持久态，执行update的sql操作
     */
    public void updateDetached(TSimpleUserEntity entity) {
        System.out.println("updateDetached SQL");
        hibernateTemplate.update(entity);

//        System.out.println("merge SQL");
//        hibernateTemplate.merge(entity);

        System.out.println("saveOrUpdate SQL");
        hibernateTemplate.saveOrUpdate(entity);

//        System.out.println("delete SQL");
//        hibernateTemplate.delete(entity);
    }


    /**
     * 如下两个持久化操作都是针对自由态对象， 若对象为游离态，则会DuplicateKeyException。
     * save 返回主键值， persist不返回主键值。
     */
    public void updateTransient(TSimpleUserEntity entity, TSimpleUserEntity entity2) {
        System.out.println("save SQL");
        System.out.println((hibernateTemplate.save(entity)));// 仅针对自由态的对象，返回主键

        System.out.println("persist SQL");
        hibernateTemplate.persist(entity2); //仅针对自由态对象， 不返回主键
    }

    public void deletedById(long id) {
        TSimpleUserEntity entity = this.findById(id);
        delete(entity);
    }

    private void delete(TSimpleUserEntity persistentEntity) {
        if (persistentEntity != null) {
            persistentEntity.setDeleted(true);
            hibernateTemplate.update(persistentEntity);
        }
    }

    public void deletedByNameAndAge(String name, int age) {
        List<TSimpleUserEntity> entities = this.findByNameAndAge(name, age);
        for (TSimpleUserEntity entity : entities) {
            delete(entity);
        }
    }
}
