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

    void update(TSimpleUserEntity entity);

    void deletedById(long id);

    void deletedByNameAndAge(String name, int age);
}
