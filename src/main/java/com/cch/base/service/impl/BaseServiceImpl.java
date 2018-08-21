package com.cch.base.service.impl;

import com.cch.base.dao.BaseMapper;
import com.cch.base.entity.BaseEntity;
import com.cch.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 */
public class BaseServiceImpl<M extends BaseEntity,PK> implements BaseService<M,PK> {
    @Autowired
    BaseMapper<M ,PK> baseMapper;

    public void save(M m) {
         baseMapper.save(m);
    }

    public void delete(PK pk) {
        baseMapper.delete(getNewInstance(),pk);
    }

    public List<M> findAll() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<M> clazz = (Class<M>) pt.getActualTypeArguments()[0];
        return baseMapper.findAll(getNewInstance());
    }

    public M findOne(PK pk)  {

        M m =baseMapper.findOne(getNewInstance(),pk);

        return m;
    }

    public Long count(M m) {
        return baseMapper.count(m);
    }


    public void update(M m) {
        baseMapper.update(m);
    }

    public List<M> findByEntity(M m) {
        return baseMapper.findByEntity(m);
    }

    M getNewInstance(){
        M m = null;
        try {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<M> clazz = (Class<M>) pt.getActualTypeArguments()[0];
            m = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return m;
    }

}
