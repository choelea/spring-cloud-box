package tech.icoding.samples.jpademo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import tech.icoding.scb.core.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author : Joe
 * @date : 2022/5/18
 */
@Entity
@Data
public class Company extends BaseEntity<Long> {

    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "4"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String name;
    private LocalDate foundDate;
}
