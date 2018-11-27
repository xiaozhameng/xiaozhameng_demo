package com.xiaozhameng.base;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    List<T> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(T record);
}