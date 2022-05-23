package tech.icoding.samples.jpademo.data;

import java.lang.Long;
import java.lang.String;
import lombok.Data;
import tech.icoding.samples.jpademo.entity.Category;
import tech.icoding.scb.core.data.BaseData;

@Data
public class ProductData extends BaseData<Long> {
  private Long id;

  private String name;

  private String desc;

  private Category category;
}
