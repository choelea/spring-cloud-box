package tech.icoding.samples.generator.data;

import java.lang.Long;
import java.lang.String;
import java.time.LocalDate;
import lombok.Data;
import tech.icoding.scb.core.data.BaseData;

@Data
public class CompanyData extends BaseData<Long> {
  private Long id;

  private String name;

  private LocalDate foundDate;
}
