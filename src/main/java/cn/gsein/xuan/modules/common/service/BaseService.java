package cn.gsein.xuan.modules.common.service;

import cn.gsein.xuan.modules.common.dao.BaseDao;
import cn.gsein.xuan.modules.common.entity.BaseEntity;

/**
 * 业务层的父级接口
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public interface BaseService<T extends BaseEntity, D extends BaseDao<T>> {

    T save(T t);


}
