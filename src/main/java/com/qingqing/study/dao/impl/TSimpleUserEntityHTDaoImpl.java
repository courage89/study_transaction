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
        switch (operateType){
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
                    list = session.createSQLQuery(sql).setParameter("id", id).list();
                }finally {
                    if(session != null){
                        session.close();
                    }
                }
            default:
               throw new QingQingRuntimeException("");
        }

        if(CollectionsUtil.isNullOrEmpty(list)){
            return null;
        }else {
            return (TSimpleUserEntity)list.get(0);
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
                    list = session.createSQLQuery(sql).setParameter("name", name).setParameter("age", age).list();
                }finally {
                    if(session != null){
                        session.close();
                    }
                }
                break;
            default:
                throw new QingQingRuntimeException("");
        }
        return (List<TSimpleUserEntity>) list;
    }

    public void update(TSimpleUserEntity entity) {

    }

    public void deletedById(long id) {

    }

    public void deletedByNameAndAge(String name, int age) {

    }


}
