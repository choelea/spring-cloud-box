package tech.icoding.samples.jpademo.data;

import java.lang.Long;
import java.lang.String;
import lombok.Data;
import tech.icoding.scb.core.data.BaseData;

@Data
public class CategoryData extends BaseData<Long> {
  private static final long serialVersionUID = 514807848978343168l;

  private Long id;

  private String name;
}
