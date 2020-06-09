package cn.gsein.xuan.modules.common.service.impl;

import cn.gsein.xuan.modules.common.dao.BaseDao;
import cn.gsein.xuan.modules.common.entity.BaseEntity;
import cn.gsein.xuan.modules.common.service.BaseService;
import cn.gsein.xuan.modules.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public class BaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T, D> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    D dao;

    @Override
    public T save(T t) {
        if (t.getId() != null) {
            t.setCreatedTime(DateUtil.now());
        }
        // 修改
        t.setUpdatedTime(DateUtil.now());


        return dao.save(t);
    }
}
