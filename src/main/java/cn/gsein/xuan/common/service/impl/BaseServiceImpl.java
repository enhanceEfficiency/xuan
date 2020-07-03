package cn.gsein.xuan.common.service.impl;

import cn.gsein.xuan.common.dao.BaseDao;
import cn.gsein.xuan.common.entity.BaseEntity;
import cn.gsein.xuan.common.service.BaseService;
import cn.gsein.xuan.common.util.DateUtil;
import cn.gsein.xuan.core.util.Constant;
import cn.gsein.xuan.core.util.ShiroUtil;
import cn.gsein.xuan.modules.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * 所有业务层的父类
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public class BaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected D dao;

    /**
     * 新增或个性数据
     *
     * @param t 要新增/修改的数据
     * @return 新增/修改后的数据，与原数据是同一个对象
     */
    @Override
    public T save(T t) {
        Optional<User> loginUser = ShiroUtil.getLoginUser();

        // 新增
        if (t.getId() == null) {
            t.setCreatedTime(DateUtil.now());
            if (loginUser.isPresent()) {
                t.setCreatedUserId(loginUser.get().getId());
            } else {
                t.setCreatedUserId(Constant.ADMINISTRATOR_ID);
            }
        }

        // 修改
        t.setUpdatedTime(DateUtil.now());
        if (loginUser.isPresent()) {
            t.setUpdatedUserId(loginUser.get().getId());
        } else {
            t.setUpdatedUserId(Constant.ADMINISTRATOR_ID);
        }

        return dao.save(t);
    }

    /**
     * 根据ID列表获取数据
     *
     * @param ids ID列表
     * @return 数据列表
     */
    @Override
    public List<T> findAllById(List<Long> ids) {
        return dao.findAllById(ids);
    }

    /**
     * 根据ID查询数据
     *
     * @param id ID
     * @return 0或1条数据，使用optional表示
     */
    @Override
    public Optional<T> findById(Long id) {
        return dao.findById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    /**
     * 获取分页数据
     *
     * @param t        查询条件
     * @param pageable 分页信息
     * @return 分页数据
     */
    @Override
    public Page<T> page(T t, Pageable pageable) {
        Specification<T> specification = dao.getSpecification(t);
        return dao.findAll(specification, pageable);
    }

    /**
     * 获取数据列表
     *
     * @param t 查询条件
     * @return 数据列表明
     */
    @Override
    public List<T> list(T t) {
        Specification<T> specification = dao.getSpecification(t);
        return dao.findAll(specification);
    }

    /**
     * 根据ID列表删除数据
     *
     * @param ids  ID列表
     * @param soft 是否为软删除
     */
    @Override
    public void delete(List<Long> ids, boolean soft) {
        if (soft) {
            ids.forEach(id -> dao.softDeleteById(id));
        } else {
            ids.forEach(id -> dao.deleteById(id));
        }
    }
}
