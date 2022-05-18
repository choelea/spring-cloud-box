package tech.icoding.samples.generator.entity;

import lombok.Data;
import tech.icoding.scb.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author : Joe
 * @date : 2022/5/18
 */
@Entity
@Data
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table-generator")
    @TableGenerator(name = "table-generator",table = "id_generator", pkColumnName = "seq_id", valueColumnName = "seq_value")
    private Long id;
    private String name;
    private String email;
}
