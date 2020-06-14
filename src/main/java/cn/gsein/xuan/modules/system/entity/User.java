package cn.gsein.xuan.modules.system.entity;

import cn.gsein.xuan.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "xuan_user", schema = "xuan")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Column
    private String username;
    /**
     * 密码
     */
    @Column
    private String password;
    /**
     * 密码加密的盐值
     */
    @Column
    private String salt;

    @ManyToMany
    @JoinTable(name="xuan_user_role")
    private List<Role> roles;
}
