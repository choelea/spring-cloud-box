package tech.icoding.samples.jpademo.form;

import java.io.Serializable;
import java.lang.String;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CompanyForm implements Serializable {
  private String name;

  private LocalDate foundDate;
}
