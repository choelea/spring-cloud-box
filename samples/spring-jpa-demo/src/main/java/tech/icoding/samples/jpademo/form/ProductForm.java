package tech.icoding.samples.jpademo.form;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import tech.icoding.samples.jpademo.entity.ProductTag;

@Data
public class ProductForm implements Serializable {
  private static final long serialVersionUID = 737313003817236736l;

  @NotNull
  private String name;

  private String desc;

  @NotNull
  private Long category;

  private List<ProductTag> tags;
}
