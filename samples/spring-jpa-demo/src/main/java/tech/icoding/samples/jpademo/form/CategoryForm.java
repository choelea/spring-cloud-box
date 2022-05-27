package tech.icoding.samples.jpademo.form;

import java.io.Serializable;
import java.lang.String;
import lombok.Data;

@Data
public class CategoryForm implements Serializable {
  private static final long serialVersionUID = 357258196013124864l;

  private String name;
}
