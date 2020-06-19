package cn.gsein.xuan.common.service;

import cn.gsein.xuan.common.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

/**
 * 业务层的父级接口
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public interface BaseService<T extends BaseEntity> {

    T save(T t);

    List<T> findAllById(List<Long> ids);

    Optional<T> findById(Long id);

    List<T> findAll();
}
