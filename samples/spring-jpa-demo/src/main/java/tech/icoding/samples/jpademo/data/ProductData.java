package tech.icoding.samples.jpademo.data;

import java.lang.Long;
import java.lang.String;
import java.util.List;
import lombok.Data;
import tech.icoding.samples.jpademo.entity.Category;
import tech.icoding.samples.jpademo.entity.ProductTag;
import tech.icoding.scb.core.data.BaseData;

@Data
public class ProductData extends BaseData<Long> {
  private static final long serialVersionUID = 667141732513795584l;

  private Long id;

  private String name;

  private String desc;

  private Category category;

  private List<ProductTag> tags;
}
