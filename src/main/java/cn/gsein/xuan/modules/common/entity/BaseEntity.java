package cn.gsein.xuan.modules.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 所有实体类的基类
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private Long createdUserId;

    @Column
    private LocalDateTime createdTime;

    @Column
    private Long updatedUserId;

    @Column
    private LocalDateTime updatedTime;

    @Column
    private String remark;

    @Column
    private boolean deleted;


}
