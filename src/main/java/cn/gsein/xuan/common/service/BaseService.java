package cn.gsein.xuan.common.service;

import cn.gsein.xuan.common.dao.BaseDao;
import cn.gsein.xuan.common.entity.BaseEntity;

/**
 * 业务层的父级接口
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public interface BaseService<T extends BaseEntity> {

    T save(T t);


}
