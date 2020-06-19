package cn.gsein.xuan.modules.system.service;

import cn.gsein.xuan.common.service.BaseService;
import cn.gsein.xuan.modules.system.entity.Menu;
import cn.hutool.core.lang.tree.Tree;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 菜单Service层接口
 *
 * @author G. Seinfeld
 * @since 2020/06/18
 */
public interface MenuService extends BaseService<Menu> {
    @Transactional(readOnly = true, noRollbackFor = Exception.class)
    List<Tree<Optional<Menu>>> getMenuTree();
}
