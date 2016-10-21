package com.qingqing.study.dao;

import com.qingqing.study.domain.TSimpleUserEntity;

import java.util.List;

/**
 *
 */
public interface TSimpleUserEntityDao {

    List<TSimpleUserEntity> findAll();

    TSimpleUserEntity findById(long id);

    List<TSimpleUserEntity> findByNameAndAge(String name, int age);

    void updateDetached(TSimpleUserEntity entity);

    void updateTransient(TSimpleUserEntity entity, TSimpleUserEntity entity2);

    void deletedById(long id);

    void deletedByNameAndAge(String name, int age);
}
