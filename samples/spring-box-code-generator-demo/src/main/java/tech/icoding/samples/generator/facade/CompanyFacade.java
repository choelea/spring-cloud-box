package tech.icoding.samples.generator.facade;

import java.lang.Long;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import tech.icoding.samples.generator.data.CompanyData;
import tech.icoding.samples.generator.entity.Company;
import tech.icoding.samples.generator.form.CompanyForm;
import tech.icoding.samples.generator.service.CompanyService;

@Component
public class CompanyFacade {
  private CompanyService companyService;

  public CompanyFacade(CompanyService companyService) {
    this.companyService = companyService;
  }

  /**
   * Get by ID
   */
  public CompanyData get(Long id) {
    final Company company = companyService.find(id);
    final CompanyData companyData = convert(company);
    return companyData;
  }

  /**
   * Find pageable data
   */
  public Page<CompanyData> find(int page, int size) {
    final PageRequest pageRequest = PageRequest.of(page, size);
    final Page<Company> entityPage = companyService.find(pageRequest);
    final List<CompanyData> dataList = entityPage.getContent().stream().map(entity -> {
                return convert(entity);
            }).collect(Collectors.toList());
    final PageImpl<CompanyData> dataPage = new PageImpl<CompanyData>(dataList, entityPage.getPageable(), entityPage.getTotalElements());
    return dataPage;
  }

  /**
   * Create Entity and save to database
   */
  public CompanyData create(CompanyForm companyForm) {
    Company company = new Company();
    convert(companyForm,company);
    company = companyService.save(company);
    return convert(company);
  }

  /**
   * Update Entity  to database
   */
  public CompanyData update(Long id, CompanyForm companyForm) {
    Company company = companyService.find(id);
    convert(companyForm, company);
    company = companyService.update(company);
    return convert(company);
  }

  /**
   * Delete by ID
   */
  public void delete(Long id) {
    companyService.delete(id);
  }

  /**
   * Convert form to entity object
   */
  private void convert(CompanyForm companyForm, Company company) {
    BeanUtils.copyProperties(companyForm, company);
    // TODO Override logic. (Copy properties is not the best solution, but an convenient one, for special logic, add below here )
  }

  /**
   * Convert entity to data object
   */
  private CompanyData convert(Company company) {
    final CompanyData companyData = new CompanyData();
    BeanUtils.copyProperties(company, companyData);
    // TODO Override logic. (Copy properties is not the best solution, but an convenient one, for special logic, add below here )
    return companyData;
  }
}
