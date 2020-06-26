package cn.gsein.xuan.modules.system.entity;

import cn.gsein.xuan.common.entity.BaseEntity;
import cn.gsein.xuan.modules.system.enums.MenuType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author G. Seinfeld
 * @since 2020/06/13
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "xuan_menu")
@Entity
@Getter
@Setter
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
     * 是否存在子节点
     */
    @Column
    private boolean hasChildren;

    /**
     * 父级菜单
     */
    @JoinColumn(name = "parent_id")
    @ManyToOne
    private Menu parent;

    /**
     * 菜单的图标
     */
    @Column
    private String icon;

    /**
     * 菜单类型
     */
    @Column
    private MenuType type;

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
