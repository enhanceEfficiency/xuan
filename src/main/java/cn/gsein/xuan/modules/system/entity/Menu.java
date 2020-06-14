package cn.gsein.xuan.modules.system.entity;

import cn.gsein.xuan.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author G. Seinfeld
 * @since 2020/06/13
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "xuan_menu")
@Data
@Entity
public class Menu extends BaseEntity {
    /**
     * 菜单名称
     */
    @Column
    private String name;

    /**
     * 菜单路径
     */
    @Column
    private String url;

    /**
     * 父级菜单
     */
    @JoinColumn(name = "parent_id")
    @OneToOne
    private Menu parent;

    /**
     * 祖先节点列表，以逗号分隔，用来寻找所有的父/子节点
     */
    private String ancestors;

    /**
     * 菜单所需的权限
     */
    @OneToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
