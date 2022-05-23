package tech.icoding.samples.jpademo.entity;

import lombok.Data;
import tech.icoding.scb.core.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author : Joe
 * @date : 2022/5/19
 */
@Entity
@Data
public class ProductTag extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 32)
    private String tag;

    @NotNull
    private Boolean system;
}
