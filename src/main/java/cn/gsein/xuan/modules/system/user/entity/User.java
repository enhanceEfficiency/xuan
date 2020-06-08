package cn.gsein.xuan.modules.system.user.entity;

import cn.gsein.xuan.modules.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码加密的盐值
     */
    private String salt;
}
