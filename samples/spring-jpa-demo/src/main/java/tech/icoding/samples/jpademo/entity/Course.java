package tech.icoding.samples.jpademo.entity;

import lombok.Data;
import tech.icoding.scb.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author : Joe
 * @date : 2022/4/26
 */
@Entity
@Data
public class Course extends BaseEntity<Long> {

    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String summary;
}
