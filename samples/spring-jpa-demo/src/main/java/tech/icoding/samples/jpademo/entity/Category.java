package tech.icoding.samples.jpademo.entity;

import lombok.Data;
import tech.icoding.scb.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author : Joe
 * @date : 2022/5/18
 */
@Entity
@Data
public class Category extends BaseEntity<Long> {

    private static final long serialVersionUID = -643946276694734258L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;
}
