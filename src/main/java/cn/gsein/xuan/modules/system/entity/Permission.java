package cn.gsein.xuan.modules.system.entity;

import cn.gsein.xuan.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author G. Seinfeld
 * @since 2020/06/13
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "xuan_permission")
@Data
public class Permission extends BaseEntity {
    /**
     * 权限字符串，如sys:user:add
     */
    @Column
    private String name;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private List<Role> roles;
}
