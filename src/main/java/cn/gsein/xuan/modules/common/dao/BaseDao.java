package cn.gsein.xuan.modules.common.dao;

import cn.gsein.xuan.modules.common.entity.BaseEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public interface BaseDao<T extends BaseEntity> extends PagingAndSortingRepository<T, Long> {
}
