package tech.icoding.samples.jpademo.controller;

import java.lang.Long;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.icoding.samples.jpademo.data.CompanyData;
import tech.icoding.samples.jpademo.facade.CompanyFacade;
import tech.icoding.samples.jpademo.form.CompanyForm;
import tech.icoding.scb.core.data.PageData;

@RestController
@RequestMapping("/companies")
public class CompanyController {
  private CompanyFacade companyFacade;

  public CompanyController(CompanyFacade companyFacade) {
    this.companyFacade = companyFacade;
  }

  /**
   * Get by ID
   */
  @GetMapping("/{id}")
  public CompanyData get(@PathVariable final Long id) {
    final CompanyData companyData = companyFacade.get(id);
    return companyData;
  }

  /**
   * Find pageable data
   */
  @GetMapping
  public PageData<CompanyData> find(@RequestParam(defaultValue = "1") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {
    final Page<CompanyData> dataPage = companyFacade.find(pageNumber-1, pageSize);
    return new PageData<>(dataPage);
  }

  /**
   * Create
   */
  @PostMapping
  public CompanyData create(@RequestBody final CompanyForm companyForm) {
    return companyFacade.create(companyForm);
  }

  /**
   * Create
   */
  @PutMapping("/{id}")
  public CompanyData update(@PathVariable final Long id,
      @RequestBody final CompanyForm companyForm) {
    return companyFacade.update(id, companyForm);
  }

  /**
   * Delete by ID
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable final Long id) {
    companyFacade.delete(id);
  }
}
