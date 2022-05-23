package tech.icoding.samples.jpademo.form;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductForm implements Serializable {
  @NotNull
  private String name;

  private String desc;

  @NotNull
  private Long category;
}
