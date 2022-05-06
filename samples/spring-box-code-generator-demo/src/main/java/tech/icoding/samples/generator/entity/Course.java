package tech.icoding.samples.generator.entity;

import lombok.Data;
import tech.icoding.scb.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author : Joe
 * @date : 2022/4/26
 */
@Entity
@Data
public class Course extends BaseEntity<Long> {

    @Column(length = 64)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String summary;
}
