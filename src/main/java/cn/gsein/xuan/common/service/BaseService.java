package cn.gsein.xuan.common.service;

import cn.gsein.xuan.common.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 所有业务层的父级接口
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 新增或个性数据
     *
     * @param t 要新增/修改的数据
     * @return 新增/修改后的数据，与原数据是同一个对象
     */
    T save(T t);

    /**
     * 根据ID列表获取数据
     *
     * @param ids ID列表
     * @return 数据列表
     */
    List<T> findAllById(List<Long> ids);

    /**
     * 根据ID查询数据
     *
     * @param id ID
     * @return 0或1条数据，使用optional表示
     */
    Optional<T> findById(Long id);

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    List<T> findAll();

    /**
     * 获取分页数据
     *
     * @param t        查询条件
     * @param pageable 分页信息
     * @return 分页数据
     */
    Page<T> page(T t, Pageable pageable);

    /**
     * 获取数据列表
     *
     * @param t 查询条件
     * @return 数据列表明
     */
    List<T> list(T t);

    /**
     * 根据ID列表删除数据
     *
     * @param ids  ID列表
     * @param soft 是否为软删除
     */
    void delete(List<Long> ids, boolean soft);
}
