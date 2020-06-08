package cn.gsein.xuan.modules.common.entity;

import cn.gsein.xuan.modules.system.user.entity.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 所有实体类的基类
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@Data
public class BaseEntity implements Serializable {

    @Id
    @Column
    private Long id;

    @Column
    private Long createdUserId;

    @Column
    private LocalDate createdTime;

    @Column
    private Long updatedUserId;

    @Column
    private LocalDate updatedTime;

    @Column
    private String remark;


}
