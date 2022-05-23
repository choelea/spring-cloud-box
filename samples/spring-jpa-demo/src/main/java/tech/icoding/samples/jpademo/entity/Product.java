package tech.icoding.samples.jpademo.entity;

import lombok.Data;
import tech.icoding.scb.core.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : Joe
 * @date : 2022/5/18
 */
@Entity
@Data
public class Product extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(length = 64,nullable = false)
    @NotNull
    private String name;

    @Column(length = 255)
    private String desc;

    @ManyToOne(optional = false)
    @NotNull
    private Category category;

    private List<ProductTag> tags;
}
