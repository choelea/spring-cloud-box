package tech.icoding.samples.jpademo.data;

import java.lang.Long;
import java.lang.String;
import lombok.Data;
import tech.icoding.scb.core.data.BaseData;

@Data
public class CourseData extends BaseData<Long> {
  private Long id;

  private String name;

  private String summary;
}
