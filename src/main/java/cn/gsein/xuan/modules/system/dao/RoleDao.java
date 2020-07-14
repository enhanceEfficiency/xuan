package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.common.dao.BaseDao;
import cn.gsein.xuan.modules.system.entity.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 角色的持久层
 *
 * @author G. Seinfeld
 * @since 2020/06/13
 */
public interface RoleDao extends BaseDao<Role> {

    Optional<Role> findByNameAndDeletedIsFalse(String name);

    @Override
    default Specification<Role> getSpecification(Role role) {
        List<Predicate> predicates = new ArrayList<>();
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (role != null) {
                // 是否有查询条件
                if (!StringUtils.isEmpty(role.getName())) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + role.getName() + "%"));
                }
            }
            predicates.add(criteriaBuilder.equal(root.get("deleted"), 0));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
