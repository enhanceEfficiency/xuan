package cn.gsein.xuan.common.service.impl;

import cn.gsein.xuan.common.dao.BaseDao;
import cn.gsein.xuan.common.entity.BaseEntity;
import cn.gsein.xuan.common.service.BaseService;
import cn.gsein.xuan.common.util.DateUtil;
import cn.gsein.xuan.core.util.Constant;
import cn.gsein.xuan.core.util.ShiroUtil;
import cn.gsein.xuan.modules.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public class BaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected D dao;

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

    @Override
    public List<T> findAllById(List<Long> ids) {
        return dao.findAllById(ids);
    }

    @Override
    public Optional<T> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }
}
