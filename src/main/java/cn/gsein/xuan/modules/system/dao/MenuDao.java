package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.common.dao.BaseDao;
import cn.gsein.xuan.modules.system.entity.Menu;
import cn.gsein.xuan.modules.system.entity.Permission;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单持久层
 *
 * @author G. Seinfeld
 * @since 2020/06/18
 */
public interface MenuDao extends BaseDao<Menu> {
    default List<Menu> findByPermissions(Set<Permission> permissionSet) {
        Specification<Menu> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.isNull(root.get("permission").get("id"));
            if (!CollectionUtils.isEmpty(permissionSet)) {
                Set<Long> set = permissionSet.stream().map(Permission::getId).collect(Collectors.toSet());
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("permission").get("id"));
                for (Long id : set) {
                    in.value(id);
                }
                predicate = criteriaBuilder.or(predicate, in);
            }
            Predicate isDeleted = criteriaBuilder.equal(root.get("deleted"), 0);
            return criteriaBuilder.and(predicate, isDeleted);
        };
        return findAll(spec);
    }
}
