package cn.gsein.xuan.modules.system.entity;

import cn.gsein.xuan.common.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
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

    /**
     * 头像地址
     */
    @Column
    private String avatar;

    /**
     * 真实姓名
     */
    @Column
    private String name;

    /**
     * 个人介绍
     */
    @Column
    private String introduction;

    @ManyToMany
    @JoinTable(name = "xuan_user_role")
    private List<Role> roles;
}
