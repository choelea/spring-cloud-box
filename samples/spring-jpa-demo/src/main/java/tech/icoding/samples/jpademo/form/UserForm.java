package tech.icoding.samples.jpademo.form;

import java.io.Serializable;
import java.lang.String;
import lombok.Data;

@Data
public class UserForm implements Serializable {
  private String name;

  private String email;
}
