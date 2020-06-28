package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.common.dao.BaseDao;
import cn.gsein.xuan.modules.system.entity.Menu;
import cn.gsein.xuan.modules.system.entity.Permission;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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


    @Override
    default Specification<Menu> getSpecification(Menu menu) {
        List<Predicate> predicates = new ArrayList<>();
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (menu != null) {
                // 是否有查询条件
                boolean flag = false;
                if (!StringUtils.isEmpty(menu.getName())) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + menu.getName() + "%"));
                    flag = true;
                }
                if (menu.getParent() != null && menu.getParent().getId() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("parent").get("id"), menu.getParent().getId()));
                    flag = true;
                }
                // 没有任何查询条件时，只查最上级菜单
                if (!flag) {
                    predicates.add(criteriaBuilder.isNull(root.get("parent").get("id")));
                }

            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
