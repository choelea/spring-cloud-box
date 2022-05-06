package tech.icoding.scb.examples.jpa.demo.entity;

import tech.icoding.scb.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author : Joe
 * @date : 2022/4/26
 */
@Entity
public class CourseEntity extends BaseEntity<Long> {

    @Column(length = 64)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String summary;
}
