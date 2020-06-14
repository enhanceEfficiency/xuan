package cn.gsein.xuan.modules.system.entity;

import cn.gsein.xuan.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 部门实体
 *
 * @author G. Seinfeld
 * @since 2020/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "xuan_dept")
@Entity
@Data
public class Dept extends BaseEntity {

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Dept parent;
}
