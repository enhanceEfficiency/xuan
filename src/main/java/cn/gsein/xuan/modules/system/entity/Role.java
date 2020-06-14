package cn.gsein.xuan.modules.system.entity;

import cn.gsein.xuan.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * 权限设计思路 用户-角色-权限-资源
 * <p>
 * 角色实体
 *
 * @author G. Seinfeld
 * @since 2020/06/13
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "xuan_role")
@Data
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "xuan_role_permission")
    private List<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
