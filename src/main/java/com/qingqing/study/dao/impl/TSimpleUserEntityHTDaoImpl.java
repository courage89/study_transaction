package com.qingqing.study.dao.impl;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.study.dao.TSimpleUserEntityDao;
import com.qingqing.study.domain.HibernateOperateType;
import com.qingqing.study.domain.TSimpleUserEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 *
 */
public class TSimpleUserEntityHTDaoImpl implements TSimpleUserEntityDao {

    @Autowired
    private HibernateOperateType hot;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<TSimpleUserEntity> findAll() {
        return hibernateTemplate.find("from TSimpleUserEntity user");
    }

    public TSimpleUserEntity findById(long id) {
        List<TSimpleUserEntity> list = null;
        switch (hot){
            case HQL:
                list = hibernateTemplate.find("from TSimpleUserEntity where id = " + id);
                break;
            case QBC:
                DetachedCriteria dc = DetachedCriteria.forClass(TSimpleUserEntity.class)
                        .add(Restrictions.eq(TSimpleUserEntity.ID, id));
                list = hibernateTemplate.findByCriteria(dc);
                break;
            case SQL:
                return null;
            default:
               throw new QingQingRuntimeException("");
        }

        if(CollectionsUtil.isNullOrEmpty(list)){
            return null;
        }else {
            return list.get(0);
        }
    }

    public List<TSimpleUserEntity> findByNameAndAge(String name, int age) {
        return null;
    }

    public void update(TSimpleUserEntity entity) {

    }

    public void deletedById(long id) {

    }

    public void deletedByNameAndAge(String name, int age) {

    }


}
